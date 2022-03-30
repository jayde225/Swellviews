import java.util.*;

/**
 * A class for creating and editing Movie objects
 */
public class Movie {
    //VARIABLES
    private String title;
    private Integer year;
    private String MPARating;
    private String releaseDate;
    private String runTime;
    private String genre;
    private ArrayList<String> movieGenres = new ArrayList<String>(); //Used to store all genres for a movie
    private String director;
    private String writer;
    private ArrayList<String> movieWriters = new ArrayList<String>(); //Used to store all writes for a movie
    private String actor;
    private ArrayList<String> movieActors = new ArrayList<String>(); //Used to store all actors for a movie
    private String plot;
    private String awards;
    private String posterLink;
    //MovieRatings criticRating; - this needs to be built first

    //CONSTRUCTORS
    /**
     * Constructs an object of Movie class type with default information
     */
    public Movie() {
        title = "N/A";
        year = 0000;
        MPARating = "N/A";
        releaseDate = "N/A";
        runTime = "N/A";
        genre = "N/A";
        director = "N/A";
        writer = "N/A";
        actor = "N/A";
        plot = "N/A";
        awards = "N/A";
        posterLink = "N/A";
    }

    //GET FUNCTIONS
    /**
     * Returns the title of the movie object
     * @return the title of the movie
     */
    public String getTitle() {return title;}

    /**
     * Returns the year of a movie object
     * @return the year the movie was made
     */
    public Integer getYear() {return year;}

    /**
     * Returns the MPA (maturity) rating of the movie object
     * @return the MPA (maturity) rating of the movie
     */
    public String getMaturityRating() {return MPARating;}

    /**
     * Returns the release date of the movie object
     * @return the release date of the movie
     */
    public String getReleaseDate() {return releaseDate;}

    /**
     * Returns the runtime of the movie object
     * @return the runtime of the movie
     */
    public String getRunTime() {return runTime;}

    /**
     * Returns all genres of the movie object in one string
     */
    public void getGenres() {
        String allGenres = "";
        for (String i : movieGenres) {
            allGenres.concat(i + ","); //Iterate through ArrayList and attach each element to the string
        }
    }

    /**
     * Returns the director of the movie object
     * @return the director of the movie
     */
    public String getDirector() { return director; }

    /**
     * Returns all writers of the movie object in one string
     */
    public void getWriters() {
        String allWriters = "";
        for (String i : movieWriters) {
            allWriters.concat(i + ","); //Iterate through ArrayList and attach each element to the string
        }
    }

    /**
     * Returns all actors of the movie object in one string
     */
    public void getActors() {
        String allActors = "";
        for (String i : movieActors) {
            allActors.concat(i + ","); //Iterate through ArrayList and attach each element to the string
        }
    }

    /**
     * Returns the plot description of the movie object
     * @return the plot description of the movie
     */
    public String getPlot() { return plot; }

    /**
     * Returns the award listing of the movie object
     * @return the award listing of the movie
     */
    public String getAwards() { return awards; }


    /**
     * Returns the poster link of the movie object
     * @return the poster link of the movie
     */
    public String getPosterLink() { return posterLink; }
}
