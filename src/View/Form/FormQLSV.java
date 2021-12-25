package View.Form;

import Database.Connect;
import Database.StudentDB;
import Entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FormQLSV extends JFrame {
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
        add(panelMain);
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
        setVisible(true);

        initComponents();
    }

    private void initComponents() {
        updateTable();

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddActionPerformed(e);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditActionPerformed(e);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeleteActionPerformed(e);
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnClearActionPerformed(e);
            }
        });

        btnSortByGPA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSortByGPAActionPerformed(e);
            }
        });

        btnSortByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSortByNameActionPerformed(e);
            }
        });
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

    private void btnAddActionPerformed(ActionEvent e) {
        Student student = new Student();
        student.setId(txfId.getText());
        student.setName(txfName.getText());
        student.setAge(Byte.parseByte(txfAge.getText()));
        student.setAddress(txaAddress.getText());
        student.setGPA(Double.parseDouble(txfGPA.getText()));

        if (txfId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID không được phép trống!", "Thông báo", 1);
        } else if (StudentDB.checkStudentAlreadyExists(student.getId())) {
            JOptionPane.showMessageDialog(null, "ID đã tồn tại!", "Thông báo", 1);
        } else {
            if (StudentDB.addStudent(student)) {
                JOptionPane.showMessageDialog(null, "Thêm mới thành công!", "Thông báo", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Thêm mới thất bại!", "Thông báo", 1);
            }

            updateTable();
        }
    }

    private void btnEditActionPerformed(ActionEvent e) {
        Student student = new Student();
        student.setId(txfId.getText());
        student.setName(txfName.getText());
        student.setAge(Byte.parseByte(txfAge.getText()));
        student.setAddress(txaAddress.getText());
        student.setGPA(Double.parseDouble(txfGPA.getText()));

        if (StudentDB.checkStudentAlreadyExists(student.getId())) {
            StudentDB.editStudent(student);
            updateTable();
            JOptionPane.showMessageDialog(null, "Chỉnh sửa Student ID - " + student.getId() + " thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Không tồn tại ID - " + student.getId() + "!", "Thông báo", 1);
        }
    }

    private void btnDeleteActionPerformed(ActionEvent e) {
        Student student = new Student();
        student.setId(txfId.getText());
        student.setName(txfName.getText());
        student.setAge(Byte.parseByte(txfAge.getText()));
        student.setAddress(txaAddress.getText());
        student.setGPA(Double.parseDouble(txfGPA.getText()));

        if (StudentDB.checkStudentAlreadyExists(student.getId())) {
            StudentDB.deleteStudent(student);
            updateTable();
            JOptionPane.showMessageDialog(null, "Xóa Student ID - " + student.getId() + " thành công!", "Thông báo", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Không tồn tại ID - " + student.getId() + "!", "Thông báo", 1);
        }
    }

    private void btnClearActionPerformed(ActionEvent e) {
        txfId.setText("");
        txfName.setText("");
        txfAge.setText("0");
        txaAddress.setText("");
        txfGPA.setText("0");
    }

    private TableRowSorter<TableModel> sorter;

    private void btnSortByGPAActionPerformed(ActionEvent e) {
        sorter = new TableRowSorter<>(tblData.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        tblData.setRowSorter(sorter);
    }

    private void btnSortByNameActionPerformed(ActionEvent e) {
        sorter = new TableRowSorter<>(tblData.getModel());
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);
        tblData.setRowSorter(sorter);
    }
}