import java.util.*;

/**
 * A class for creating and editing Movie objects
 */
public class Movie {
    //VARIABLES
    String Title;
    Integer Year;
    String Rated;
    String Released;
    String Runtime;
    String Genre;
    String Director;
    String Writer;
    String Actors;
    String Plot;
    String Awards;
    String Poster;
    ArrayList<Ratings> Ratings = new ArrayList<Ratings>(); //Used to store all ratings for a movie

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
     * @return the string list of genres of the movie
     */
    public String getGenres() { return Genre;}

    /**
     * Returns the director of the movie object
     * @return the director of the movie
     */
    public String getDirector() { return Director; }

    /**
     * Returns all writers of the movie object in one string
     * @return the string list of writers of the movie
     */
    public String getWriters() { return Writer; }

    /**
     * Returns all actors of the movie object in one string
     * @return the string list of actors of the movie
     */
    public String getActors() { return Actors; }

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
    public ArrayList<Ratings> getRatings() { return Ratings; }
}
