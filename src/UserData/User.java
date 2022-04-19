package UserData;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.Objects;

/**
 * works what the user stores such as collections, where you can add collections and remove collections to your account
 */
public class User {

    // VARIABLES
    // uses expose to explicitly decide which field gets serialized
    @Expose
    private String username;

    @Expose
    private String password;

    @Expose
    private ArrayList<UserMovieCollection> myCollections = new ArrayList<UserMovieCollection>();

    private boolean isLoggedIn = false;

    // CONSTRUCTOR

    /**
     * parameterless constructor is required for GSON deserialization
     */
    public User()
    {

    }

    /**
     * constructor that sets username and password
     * @param username
     * @param password
     */
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    // GET FUNCTIONS
    /**
     * returns the username of the user object
     * @return
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * returns whether the user is logged in
     * @return
     */
    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    /**
     * returns the ArrayList of UserMovieColection, myCollecions, of the user object
     * @return
     */
    public ArrayList<UserMovieCollection> getMyCollections()
    {
        return myCollections;
    }

    /**
     * authenticates the user using a password
     * @param password
     * @return
     */
    public boolean logIn(String password)
    {
        isLoggedIn = this.password.equals(password);
        return isLoggedIn;
    }


    // COLLECTION FUNCTIONS
    /**
     * checks if there are any collections with the same desired name already existing, and if there is not, it will add it to an array of collections stored by the user
     * @param name
     */
    public void addCollection(String name)
    {
        for (int i = 0; i < myCollections.size(); i++) {
            if (myCollections.get(i).getCollectionName().equals(name)) {
                return;
            }
        }

        myCollections.add(new UserMovieCollection(name));

    }

    /**
     * checks if there are any collections with that desired name already existing, and if there is, it will remove the colleciton from the array stored by the user
     * @param name
     */
    public void removeCollection(String name)
    {
        for (int i = 0; i < myCollections.size(); i++)
        {
            if (myCollections.get(i).getCollectionName().equals(name))
            {
                myCollections.remove(i);

            }
        }

    }

    // COMPARISON
    /**
     * compares Users to the entered username to ensure it is being stored in the correct place
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {    // user != user
            return false;
        }

        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }


        return true;
    }

}

