import java.util.*;

/**
 * A class for creating and editing Movie objects
 */
public class Movie {
    String title;
    Integer year;
    String maturityRating;
    String releaseDate;
    String runTime;
    String genre;
    ArrayList<String> movieGenres = new ArrayList<String>(); //Used to store all genres for a movie
    String director;
    String writer;
    ArrayList<String> movieWriters = new ArrayList<String>(); //Used to store all writes for a movie
    String actor;
    ArrayList<String> actorWriters = new ArrayList<String>(); //Used to store all writes for a movie
    String plot;
    String awards;
    String posterLink;
    //MovieRatings criticRating;
}
