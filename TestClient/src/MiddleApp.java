
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MiddleApp 
        extends Remote
{
    int[] getRandomPaper()throws 
            RemoteException;
    String[] fetchQuestion(int qno)
            throws RemoteException;
}
