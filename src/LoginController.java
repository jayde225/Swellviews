package LoginMVC;

import MainMVC.*;
import UserData.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private static final String loginErrorMessage = "Username or password is incorrect.";
    private static final String createAccountErrorMessage = "Username and password are required.";
    private static final String duplicateUserErrorMessage = "Username must be unique.";

    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        this.view.addLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = view.getUserName();
                String password = view.getPassword();

                UserCollection users = model.getAllUsers();

                if (users.contains(userName)) {
                    User user = users.get(userName);

                    if(user.logIn(password)) {
                        model.setCurrentUser(user);

                        // If the login is successful, set up the MVC objects for the main view.
                        MainModel mainModel = new MainModel();
                        MainView mainView = new MainView("Swellviews");
                        MainController mainController = new MainController(mainModel, mainView);

                        // Display the main view.
                        mainView.setVisible(true);

                        // Hide the login view.
                        view.setVisible(false);
                    }
                    else {
                        view.displayLoginError(loginErrorMessage);
                    }
                }
                else {
                    view.displayLoginError(loginErrorMessage);
                }
            }
        });

        this.view.addCreateAccountListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = view.getUserName();
                String password = view.getPassword();

                if(userName == null || userName.trim().equals("") || password == null || password.trim().equals(""))
                {
                    view.displayLoginError(createAccountErrorMessage);
                }
                else {
                    User user = new User(userName.trim(), password.trim());

                    if(!model.addUser(user))  {
                        view.displayLoginError(duplicateUserErrorMessage);
                    };
                }
            }
        });
    }
}
