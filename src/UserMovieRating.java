import java.util.Objects;

public class UserMovieRating {

    private String rating;
    private Movie movie;

    // CONSTRUCTORS
    public UserMovieRating()
    {

    }
    public UserMovieRating(String rating, Movie movie)
    {
        this.rating = rating;
        this.movie = movie;


    }
    public UserMovieRating(Movie movie)
    {
        this.movie = movie;
    }

    // guarantees that equals behaves correctly
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
