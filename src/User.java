import java.util.ArrayList;
import java.util.Objects;

/**
 * works what the user stores such as collections, where you can add collections and remove collections to your account
 */
public class User {

    // VARIABLES
    private String username;
    private ArrayList<UserMovieCollection> myCollections = new ArrayList<UserMovieCollection>();

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
     * returns the ArrayList of UserMovieColection, myCollecions, of the user object
     * @return
     */
    public ArrayList<UserMovieCollection> getMyCollections()
    {
        return myCollections;
    }


    // COLLECTION FUNCTIONS
    /**
     * checks if there are any collections with the same desired name already existing, and if there is not, it will add it to an array of collections stored by the user
     * @param name
     */
    public void addCollection(String name)
    {
        for (int i = 0; i < myCollections.size(); i++)
        {
            if (myCollections.get(i).getCollectionName() == name)
            {
                System.out.println("There is already a collection with that name");

            }
            else
            {
                myCollections.add(new UserMovieCollection(name));
            }
        }
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

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }


        return true;
    }

}

