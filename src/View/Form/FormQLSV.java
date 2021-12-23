package View.Form;

import Database.Connect;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class FormQLSV {
    private JPanel panelMain;
    private JTable tblData;
    private JTextField txfName;
    private JTextArea txaAddress;
    private JTextField txfGPA;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    private JButton btnClear;
    private JButton btnSortByGPA;
    private JButton btnSortByName;
    private JTextField txfId;
    private JTextField txfAge;

    public FormQLSV() {
        updateTable();
    }

    public JPanel getPanelMain() {
        return panelMain;
    }

    public void updateTable() {
        Connection conn = Connect.getConnect();
        try {
            tblData.removeAll();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT");
            String[] columnName = {"ID", "NAME", "AGE", "ADDRESS", "GPA"};
            DefaultTableModel defTblModel = new DefaultTableModel(columnName, 0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("ID"));
                v.add(rs.getString("NAME"));
                v.add(rs.getByte("AGE"));
                v.add(rs.getString("ADDRESS"));
                v.add(rs.getDouble("GPA"));

                defTblModel.addRow(v);
            }

            tblData.setModel(defTblModel);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Connect.closeConnect(conn);
        }
    }
}
