package UserData;

import MovieData.*;
import com.google.gson.annotations.Expose;

import java.util.Objects;

/**
 * stores and ensures that each movie rating is compared by each movie
 */
public class UserMovieRating {

    // VARIABLES
    @Expose
    private String rating;
    @Expose
    private Movie movie;

    // CONSTRUCTORS

    /**
     * sets inputed movie and rating to the working values in the class
     * @param rating
     * @param movie
     */
    public UserMovieRating(String rating, Movie movie)
    {
        this.rating = rating;
        this.movie = movie;
    }

    /**
     * sets inputed movie to the working value in the class
     * @param movie
     */
    public UserMovieRating(Movie movie)
    {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    // COMPARISON FUNCTIONS
    /**
     * guarantees that equals behaves correctly. compares movie ratings to movies, returning true if and only if they are the same
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

        final UserMovieRating other = (UserMovieRating) obj;
        if (!Objects.equals(this.movie, other.movie)) {
            return false;
        }

        return true;
    }
}
