package LoginMVC;

import javax.swing.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");

    private JTextField userField = new JTextField(50);
    private JTextField passField = new JTextField(50);

    private JButton loginButton = new JButton("Login");
    private JButton createAccountButton = new JButton("Create Account");

    public LoginView() {
        JPanel panel = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(createAccountButton);

        this.add(panel);
    }

    public String getUserName() {
        return userField.getText();
    }

    public String getPassword() {
        return passField.getText();
    }

    void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
   }

    void addCreateAccountListener(ActionListener listener){
        createAccountButton.addActionListener(listener);
   }

    void displayLoginError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
