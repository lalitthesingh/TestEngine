package cons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connections {

    static Connection con;
    static Statement st;

    public static Statement getConnections() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                    "system", "123");
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
        return st;
    }
    public static void closeConnection()
    {
        try{
        st.close();
        con.close();
        }catch(Exception e)
        {}
    }
}
