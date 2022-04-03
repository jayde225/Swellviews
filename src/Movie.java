import java.util.*;

/**
 * A class for creating and editing Movie objects
 */
public class Movie {
    //VARIABLES
    private String Title;
    private Integer Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private ArrayList<String> movieGenres = new ArrayList<String>(); //Used to store all genres for a movie
    private String Director;
    private String Writer;
    private ArrayList<String> movieWriters = new ArrayList<String>(); //Used to store all writes for a movie
    private String Actors;
    private ArrayList<String> movieActors = new ArrayList<String>(); //Used to store all actors for a movie
    private String Plot;
    private String Awards;
    private String Poster;
    private Ratings Ratings;

    //GET FUNCTIONS
    /**
     * Returns the title of the movie object
     * @return the title of the movie
     */
    public String getTitle() {return Title;}

    /**
     * Returns the year of a movie object
     * @return the year the movie was made
     */
    public Integer getYear() {return Year;}

    /**
     * Returns the MPA (maturity) rating of the movie object
     * @return the MPA (maturity) rating of the movie
     */
    public String getMPARating() {return Rated;}

    /**
     * Returns the release date of the movie object
     * @return the release date of the movie
     */
    public String getReleaseDate() {return Released;}

    /**
     * Returns the runtime of the movie object
     * @return the runtime of the movie
     */
    public String getRunTime() {return Runtime;}

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
    public String getDirector() { return Director; }

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
    public String getPlot() { return Plot; }

    /**
     * Returns the award listing of the movie object
     * @return the award listing of the movie
     */
    public String getAwards() { return Awards; }

    /**
     * Returns the poster link of the movie object
     * @return the poster link of the movie
     */
    public String getPosterLink() { return Poster; }

    /**
     * Returns the ratings of the movie object
     * @return the ratings of the movie
     */
    public Ratings getRatings() { return Ratings; }
}
