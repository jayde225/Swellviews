package LoginMVC;

import MainMVC.*;
import UserData.*;
import HomeMVC.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * logic for our controller for log in
 */
public class LoginController {
    private static final String loginErrorMessage = "Username or password is incorrect.";
    private static final String createAccountErrorMessage = "Username and password are required.";
    private static final String duplicateUserErrorMessage = "Username must be unique.";

    private LoginModel model;
    private LoginView view;

    //CONSTRUCTOR
    /**
     * hooking the view with the model
     * @param model
     * @param view
     */
    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        // whenever log in button is pressed, it checks if the username exists and the password is correct
        this.view.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = view.getUserName();   // get username from view
                String password = view.getPassword();   // get password from view

                UserCollection users = model.getAllUsers(); // get all users from model

                if (users.contains(userName)) { // checks to see if username exists
                    User user = users.get(userName);

                    if(user.logIn(password)) {  // checks if the password is correct
                        LoginModel.setCurrentUser(user);

                        Home.main(null); // launches the home screen of Swellviews

                        view.setVisible(false); // Exits the login pop-up
                    }
                    else {
                        view.displayLoginError(loginErrorMessage);  // if the password does not match display an error message
                    }
                }
                else {
                    view.displayLoginError(loginErrorMessage);  // if the username does not exist display an error message
                }
            }
        });

        // whenever create account button is pressed, it checks if the username already exists and sets password
        this.view.addCreateAccountListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = view.getUserName();   // gets username from view
                String password = view.getPassword();   // gets password from view

                if(userName == null || userName.trim().equals("") || password == null || password.trim().equals(""))    // if the username or password field is blank, then error message is displayed
                {
                    view.displayLoginError(createAccountErrorMessage);
                }
                else {
                    User user = new User(userName.trim(), password.trim()); // making a new user with input from text fields

                    if(!model.addUser(user))  {
                        view.displayLoginError(duplicateUserErrorMessage); // if it does not add, then display an error
                    };
                }
            }
        });
    }
}
