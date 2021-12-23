package Database;

import Entity.Student;
import View.Form.FormQLSV;

import java.sql.*;

public class StudentDB {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement preStmt;

    public static void addStudent(Student student) {
        conn = Connect.getConnect();
        try {
            preStmt = conn.prepareStatement("INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?)");
            preStmt.setString(1, student.getId());
            preStmt.setString(2, student.getName());
            preStmt.setByte(3, student.getAge());
            preStmt.setString(4, student.getAddress());
            preStmt.setDouble(5, student.getGPA());
            preStmt.executeUpdate();

            new FormQLSV().updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }
    }

    public static void editStudent(Student student) {
        conn = Connect.getConnect();
        try {
            preStmt = conn.prepareStatement("UPDATE STUDENT SET NAME='?', AGE='?', ADDRESS='?', GPA='?' WHERE ID='?'");
            preStmt.setString(1, student.getName());
            preStmt.setByte(2, student.getAge());
            preStmt.setString(3, student.getAddress());
            preStmt.setDouble(4, student.getGPA());
            preStmt.executeUpdate();

            new FormQLSV().updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }
    }
}
