package Database;

import Entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB {
    public static boolean checkUser(User user) {
        Connection conn = Connect.getConnect();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM [USER]");

            while (rs.next()) {
                if (user.getUsername().equals(rs.getString("USERNAME")) && user.getPassword().equals(rs.getString("PASSWORD"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }

        return false;
    }
}
