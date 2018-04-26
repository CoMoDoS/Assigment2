package View;

import Model.Admin;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AdminProfileView extends JFrame{
    private JPanel panel1;
    private JTextField idTF;
    private JTextField nameTF;
    private JPasswordField passwordField1;
    private JTextField statTF;
    private JTextField emailTF;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel passLabel;
    private JLabel statLabel;
    private JButton updateButton;
    private JButton deleteButton;

    private Admin admin;

    public AdminProfileView()
    {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setUpdateAdminButton(ActionListener e)
    {
        this.updateButton.addActionListener(e);
    }
    public void setDeleteAdminButton(ActionListener e)
    {
        this.deleteButton.addActionListener(e);
    }

    public JTextField getIdTF() {
        return idTF;
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }

    public JTextField getStatTF() {
        return statTF;
    }

    public JTextField getEmailTF() {
        return emailTF;
    }
}
