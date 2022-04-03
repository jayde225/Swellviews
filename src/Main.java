import com.google.gson.*; //import for Gson capabilities
import java.util.*; //import util for Scanner, Array List, and Sorting functions
//import java.io.*; //import File class

/**
 * A class used for creating the main application
 */
public class Main {
    public static void main(String args[]) {
        //GSON TEST CODE
        String jsonString = "[{\"Title\":\"Avengers: Endgame\",\"Year\":\"2019\",\"imdbID\":\"tt4154796\",\"Genre\":\"action\"}, {\"Title\":\"Avengers: Infinity War\",\"Year\":\"2017\",\"imdbID\":\"tt4154796\",\"Type\":\"drama\"}]";
        Gson gson = new Gson();
        Movie[] movieList;  // a java primitive array of SimpleMovie
        ArrayList<Movie> testArrayList = new ArrayList<Movie>();    // an array list to hold a collection of movies
        movieList = gson.fromJson(jsonString, Movie[].class);
        Collections.addAll(testArrayList, movieList);

        for (Movie testMovie : testArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
        }
    }
}
