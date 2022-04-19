import LoginMVC.*;

/**
 * A class used for creating the main application
 */

public class Main {
    public static void main(String args[]) {
        // Set up the MVC objects for logging in.
        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView();
        LoginController loginController = new LoginController(loginModel, loginView);

        // Display the login view.
        loginView.setVisible(true);
    }
}
