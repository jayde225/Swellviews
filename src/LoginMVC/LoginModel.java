package LoginMVC;

import UserData.*;

/**
 * model logic for login screen
 */
public class LoginModel {
    private static User currentUser;
    private UserCollection allUsers;

    // CONSTRUCTOR
    /**
     * reads in all the users
     */
    public LoginModel() {
        allUsers = UserSerializer.read();
    }

    /**
     * returns currentuser
     * @return
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * sets currentuser
     * @param user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * returns the list of users
     * @return
     */
    public UserCollection getAllUsers() {
        return allUsers;
    }

    /**
     * adds user to the list of users and if it was successful, it returns true
     * @param user
     * @return
     */
    public boolean addUser(User user) {
        boolean userAdded = allUsers.add(user);

        if (userAdded) {
            UserSerializer.write(allUsers);
        }

        return userAdded;
    }

    /**
     * remove and re-add current user before saving to make sure we have the latest data
     */
    public void logOut(){
        this.allUsers.remove(currentUser);
        this.allUsers.add(currentUser);

        UserSerializer.write(allUsers);
        setCurrentUser(null);
    }
}
