package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    private JPanel mainPanel;
    private JButton registerButton;
    private JTextField nameTF;
    private JTextField emailTF;
    private JPasswordField passwordField1;
    private JLabel passLabel;
    private JLabel emailLabel;
    private JLabel nameLabel;
    private JPanel rightPanel;
    private JPanel leftPanle;
    private JPanel bottomPanel;

    public RegisterView ()
    {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public void setRegisterButton(ActionListener a1)
    {
        this.registerButton.addActionListener(a1);
    }

    public JTextField getEmailTF() {
        return emailTF;
    }

    public JTextField getNameTF() {
        return nameTF;
    }

    public JPasswordField getPasswordField1() {
        return passwordField1;
    }
}
