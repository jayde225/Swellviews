import javax.management.openmbean.ArrayType;
import java.util.ArrayList;

public class User {

    //leave as default for GSON/JSON and capitalize spelling
    String Username;
    String Password;
    private String profile = Username + " " + Password;
    private ArrayList<String> profiles = new ArrayList<String>();
    private double ratings;
    private ArrayList<MovieCollection> myCollections = new ArrayList<MovieCollection>();

    public User(String username, String password) {
        this.Username = username;
        this.Password = password;
    }

    public ArrayList<MovieCollection> getMyCollections() { return myCollections; }

    public boolean addCollection(MovieCollection newCollection) { return true;}

    //Log-in and check if valid user
    public boolean logIn(User testUser, ArrayList<User> allUsers) {
        boolean finalValue = false;
        for (User user : allUsers) {
            if (testUser.Username.equals(user.Username)) {
                //This means the username was found, so now check password
                if (testUser.Password.equals(user.Password)) {
                    finalValue = true;
                }
            }
        }
        return finalValue; //This function will only return true if the username and password matches and is in the arraylist
    }

}
