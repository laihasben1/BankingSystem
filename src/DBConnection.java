import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "bankuser";
    private static final String PASSWORD = "Bankpass123!";

    public static void main(String[] args) {
        Connection conn = connect();
        if (conn != null) {
            System.out.println("✅ Connected to the database!");
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("⚠️ Error closing the connection.");
            }
        } else {
            System.out.println("❌ Connection failed!");
        }
    }

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
        }
        return null;
    }
}
