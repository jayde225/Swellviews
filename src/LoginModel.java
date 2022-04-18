package LoginMVC;

import UserData.*;

public class LoginModel {
    private User currentUser;
    private UserCollection allUsers;

    public LoginModel() {
        allUsers = UserSerializer.read();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public UserCollection getAllUsers() {
        return allUsers;
    }

    public boolean addUser(User user) {
        boolean userAdded = allUsers.add(user);

        if (userAdded) {
            UserSerializer.write(allUsers);
        }

        return userAdded;
    }
}
