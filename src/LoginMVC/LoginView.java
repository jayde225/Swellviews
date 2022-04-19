package LoginMVC;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * view logic for login screen
 */
public class LoginView extends JFrame {
    // a pop-up window to log in before accessing swellviews

    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");

    private JTextField userField = new JTextField(50);
    private JTextField passField = new JTextField(50);

    private JButton loginButton = new JButton("Login");
    private JButton createAccountButton = new JButton("Create Account");

    // CONSTRUCTOR
    /**
     * creates the login pop-up
     */
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

    /**
     * returns the username input from the text field
     * @return
     */
    public String getUserName() {
        return userField.getText();
    }

    /**
     * returns the password input from the text field
     * @return
     */
    public String getPassword() {
        return passField.getText();
    }

    /**
     * adds an action lister to the login button
     * @param listener
     */
    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
   }

    /**
     * adds an action lister to the create account button
     * @param listener
     */
    public void addCreateAccountListener(ActionListener listener){
        createAccountButton.addActionListener(listener);
   }

    /**
     * shows a error in a pop-up window
     * @param errorMessage
     */
    public void displayLoginError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
