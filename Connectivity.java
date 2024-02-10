import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity{

    private static final String url = "jdbc:mysql://localhost:3306/practice";
    private static final String username = "root";
    private static final String password = "alone_Hacker/1258";

    static Connection connection = null;

    public static Connection connectDB(){
        try{
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Connection established......");
        return connection;
    }

}