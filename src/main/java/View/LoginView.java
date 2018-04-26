package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel bottomPanel;
    private JPanel leftPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JTextField emailTf;

    private JLabel passLabel;
    private JLabel emailLabel;
    private JPasswordField passTF;


    public LoginView() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();


    }

    public void setLoginButton(ActionListener al)
    {
        this.loginButton.addActionListener(al);
    }

    public void setRegisterButton(ActionListener a2)
    {
        this.registerButton.addActionListener(a2);
    }

    public JTextField getEmailTf() {
        return emailTf;
    }

    public JPasswordField getPassTF() {
        return passTF;
    }


}
