import java.util.ArrayList;
import java.util.Objects;

public class User {

    private String username;
    private ArrayList<UserMovieCollection> myCollections = new ArrayList<UserMovieCollection>();

    // GET FUNCTIONS
    public String getUsername()
    {
        return username;
    }

    public ArrayList<UserMovieCollection> getMyCollections()
    {
        return myCollections;
    }


    // COLLECTION FUNCTIONS
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

    // compare Users by username
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

