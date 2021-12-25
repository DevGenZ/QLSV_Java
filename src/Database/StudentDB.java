package Database;

import Entity.Student;
import View.Form.FormQLSV;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class StudentDB {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement preStmt;

    public static boolean addStudent(Student student) {
        conn = Connect.getConnect();
        try {
            preStmt = conn.prepareStatement("INSERT INTO STUDENT VALUES(?, ?, ?, ?, ?)");
            preStmt.setString(1, student.getId());
            preStmt.setString(2, student.getName());
            preStmt.setByte(3, student.getAge());
            preStmt.setString(4, student.getAddress());
            preStmt.setDouble(5, student.getGPA());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            Connect.closeConnect(conn);
        }

        return true;
    }

    public static boolean checkStudentAlreadyExists(String id) {
        conn = Connect.getConnect();
        try {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery("SELECT * FROM STUDENT WHERE ID='" + id + "'");
            rs.beforeFirst();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }

        return true;
    }

    public static void editStudent(Student student) {
        conn = Connect.getConnect();
        try {
            preStmt = conn.prepareStatement("UPDATE STUDENT SET NAME=?, AGE=?, ADDRESS=?, GPA=? WHERE ID=?");
            preStmt.setString(1, student.getName());
            preStmt.setByte(2, student.getAge());
            preStmt.setString(3, student.getAddress());
            preStmt.setDouble(4, student.getGPA());
            preStmt.setString(5, student.getId());
            preStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }
    }

    public static void deleteStudent(Student student) {
        conn = Connect.getConnect();
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM STUDENT WHERE ID='" + student.getId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }
    }
}
