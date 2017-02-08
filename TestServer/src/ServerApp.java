import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import static cons.Connections.*;
import java.rmi.Naming;
import java.sql.ResultSet;
import java.sql.Statement;
public class ServerApp extends 
        UnicastRemoteObject implements 
        MiddleApp
{
    ServerApp()throws RemoteException
    {
        super();
    }

    @Override
    public int[] getRandomPaper() 
            throws RemoteException 
    {
        int n[]=new int[4];
        try{
        Statement st=getConnections();
        ResultSet rs=st.executeQuery("select count(*) from question_paper");
        rs.next();
        int total=rs.getInt(1);
        for(int i=0;i<4;)
        {
            int q=(int)(Math.ceil(total*Math.random()));
            int j=0;
            for(j=0;j<i;j++){
                if(n[j]==q){
                    break;
                }
            }
            if(i==j)
            {
                n[i]=q;
                i++;
            }
        }
        closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return n;
    }
    public String[] fetchQuestion(int qno)throws RemoteException
    {
        String s[]=new String[5];
        try{
        Statement st=getConnections();
        ResultSet rs=st.executeQuery("select * from question_paper where qno="+qno);
        rs.next();
        s[0]=rs.getString(2);
        s[1]=rs.getString(3);
        s[2]=rs.getString(4);
        s[3]=rs.getString(5);
        s[4]=rs.getString(6);
        closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return s;
    }
    public static void main(String a[])
    {
        try{
        ServerApp ser=new ServerApp();
        Naming.rebind("testengine", ser);
        System.out.println("Server Registered!!!");
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}