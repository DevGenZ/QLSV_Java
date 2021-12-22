package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private Connection conn;
    private String DB_URL = "jdbc:sqlserver://localhost:1433;" + "databaseName=QLSV;" + "integratedSecurity=true;";
    private String USER_NAME = "sa";
    private String PASSWORD = "123456";

    public Connection getConnect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            System.out.println("Connect success!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connect failure!");
        }

        return conn;
    }
}
