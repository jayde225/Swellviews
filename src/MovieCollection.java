import java.util.ArrayList;

public class MovieCollection {

    private ArrayList<Movie> MovieCollection = new ArrayList<Movie>();
    private int collectionLength = 0;
    private String collectionName;

    //Collection Constructor
    public MovieCollection(String name) {
        this.collectionName = name;

    }

    // GET FUNCTIONS

    /**
     * returns the movie collection name as set by user
     *
     * @return String of movie collection name
     */
    public String getCollectionName() {
        return collectionName;
    }

    /**
     * returns the movie collection list
     *
     * @return
     */
    public ArrayList<Movie> getMovieCollection() {
        return MovieCollection;
    }

    /**
     * returns the movie collection length
     *
     * @return
     */
    public int getCollectionLength() {
        return collectionLength;
    }


    // WORKING FUNCTIONS

    /**
     * checks if the movie is already added. if not, it sets the last place of the list as the movie inputed then adds one. returns if adding was successful or not
     *
     * @param newMovie
     */
    public boolean addMovie(Movie newMovie) {
        boolean duplicateMovie;
        for (int i = 0; i < collectionLength; i++) {
            if (newMovie == MovieCollection.get(i)) {
                duplicateMovie = true;
            } else {
                duplicateMovie = false;
            }
        }
        if (false) {
            MovieCollection.set(collectionLength, newMovie);
            collectionLength++;
            return true;
        } else
            return false;
    }

    /**
     * checks if the movie exists in collection. if so, then wherever the movie is found is then removed and collection length decreases.returns if adding was successful or not
     */
    public boolean removeMovie(Movie inputMovie)
    {
        for (int i = 0; i < collectionLength; i++) {
            if (inputMovie == MovieCollection.get(i)) {
                MovieCollection.remove(i);
                collectionLength--;
                return true;
            }
        }
        return false;
    }
}