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
/**
 * This class is used to sort a Movie array list by year, oldest to newest
 */
class YearCompare implements Comparator<Movie> {
    /**
     * Used by the Comparator class to sort an array list by year
     * @param  m1 the first of two objects that will be compared
     * @param m2 the second of two objects that will be compared
     * @return -1 if the first object comes before the second object, and 1 for vice versa. Returns 0 if they are
     * the same
     */
    public int compare(Movie m1, Movie m2) {
        if (m1.getYear() < m2.getYear()) { return -1; }
        if (m1.getYear() > m2.getYear())  { return 1; }
        else return 0;
    }
}

/**
 * This class is used to sort a Movie array list by name, A to Z
 */
class NameCompare implements Comparator<Movie> {
    /**
     * Used by the Comparator class to sort an array list by name
     * @param  m1 the first of two objects that will be compared
     * @param m2 the second of two objects that will be compared
     * @return -1 if the first object comes before the second object, and 1 for vice versa. Returns 0 if they are
     * the same
     */
    public int compare(Movie m1, Movie m2) {
        String firstTitle = m1.getTitle();
        String secondTitle = m2.getTitle();
        int compareTitle = firstTitle.compareTo(secondTitle);

        if (compareTitle < 0) { return -1; }
        if (compareTitle > 0) { return 1; }
        else return 0;
    }
}

/**
 * This class is used to sort a Movie array list by runtime, shortest to longest
 */
class TimeCompare implements Comparator<Movie> {
    /**
     * Used by the Comparator class to sort an array list by runtime
     *
     * @param m1 the first of two objects that will be compared
     * @param m2 the second of two objects that will be compared
     * @return -1 if the first object comes before the second object, and 1 for vice versa. Returns 0 if they are
     * the same
     */
    public int compare(Movie m1, Movie m2) {
        String firstRuntimeString = m1.getRunTime();
        String secondRuntimeString = m2.getRunTime();
        int runTime1 = 0;
        int runTime2 = 0;

        //Create scanners to search strings for integer representing minutes
        Scanner scanString1 = new Scanner(firstRuntimeString);
        Scanner scanString2 = new Scanner(secondRuntimeString);

        //Get runtimes by searching string for integer value
        runTime1 = scanString1.nextInt();
        runTime2 = scanString2.nextInt();

        //Close scanners
        scanString1.close();
        scanString2.close();

        if (runTime1 < runTime2) {
            return -1;
        }
        if (runTime1 > runTime2) {
            return 1;
        } else return 0;
    }
}
