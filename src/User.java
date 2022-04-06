import javax.management.openmbean.ArrayType;
import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String profile = username + " " + password;
    private ArrayList<String> profiles = new ArrayList<String>();
    private double ratings;
    private ArrayList<MovieCollection> myCollections = new ArrayList<MovieCollection>();
    private ;


    public ArrayList<MovieCollection> getMyCollections()
    {
        return myCollections;
    }

    public boolean addCollection(MovieCollection newCollection)
    {
        return true;
    }
}
