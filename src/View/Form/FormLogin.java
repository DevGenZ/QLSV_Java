package View.Form;

import Database.UserDB;
import Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormLogin extends JFrame {
    private JTextField txfUser;
    private JPasswordField pwfPassword;
    private JButton btnLogin;
    private JPanel panelMain;

    public FormLogin() {
        add(panelMain);
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2 - this.getWidth()/2, dim.height/2 - this.getHeight()/2);
        setVisible(true);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLoginActionPerformed(e);
            }
        });
    }

    private void btnLoginActionPerformed(ActionEvent e) {
        User user = new User();
        user.setUsername(txfUser.getText());
        user.setPassword(String.valueOf(pwfPassword.getPassword()));
        if (UserDB.checkUser(user)) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!", "Thông báo", 1);
            setVisible(false);
            new FormQLSV();
        } else {
            JOptionPane.showMessageDialog(null, "Đăng nhập thất bại", "Thông báo", 1);
        }
    }
}
