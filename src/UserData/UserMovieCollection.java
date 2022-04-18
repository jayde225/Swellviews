package UserData;

import java.util.ArrayList;
import java.util.Objects;

/**
 * used to manage collections: adding movies, removing movies, and correctly comparing them
 */
public class UserMovieCollection {

    // VARIABLES
    private ArrayList<UserMovieRating> MovieCollection = new ArrayList<UserMovieRating>();
    private String collectionName;

    // CONSTRUCTOR
    /**
     * ensures we are managing the correct collection
     * @param name
     */
    public UserMovieCollection(String name)
    {
        collectionName = name;
    }

    // GET FUNCTIONS
    /**
     * returns the movie collection list
     * @return
     */
    public ArrayList<UserMovieRating> getMovieCollection()
    {
        return MovieCollection;
    }

    /**
     * returns the movie collection name
     * @return
     */
    public String getCollectionName() {
        return collectionName;
    }


    // MOVIE COLLECTION FUNCTIONS
    /**
     * checks if the movie is already added. if not, it sets the last place of the list as the movie inputed then adds one. returns if adding was successful or not
     * @param newMovie
     */
    public boolean addMovie(UserMovieRating newMovie)
    {
        boolean duplicateMovie = true;
        for (int i = 0; i < MovieCollection.size(); i++) {
            if (newMovie.equals(MovieCollection.get(i))) {
                duplicateMovie = true;
            }
            else
            {
                duplicateMovie = false;
            }
        }
        if (!duplicateMovie)
        {
            MovieCollection.add(newMovie);
            return true;
        }
        else
            return false;
    }

    /**
     * checks if the movie exists in collection. if so, then wherever the movie is found is then removed and collection length decreases.returns if adding was successful or not
     */
    public boolean removeMovie(UserMovieRating inputMovie)
    {
        for (int i = 0; i < MovieCollection.size(); i++) {
            if (inputMovie.equals(MovieCollection.get(i))) {
                MovieCollection.remove(i);
                return true;
            }
        }
        return false;
    }

    //COMPARISON
    /**
     * compares movie collections with the collection name
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

        final UserMovieCollection other = (UserMovieCollection) obj;
        if (!Objects.equals(this.collectionName, other.collectionName)) {
            return false;
        }


        return true;
    }

}

