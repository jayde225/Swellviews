import com.google.gson.*; //import for Gson capabilities
import java.util.*; //import util for Scanner, Array List, and Sorting functions
import java.io.*; //import File class

/**
 * A class used for creating the main application
 */
public class Main {
    public static void main(String args[]) {
        //GSON IMPLEMENTATION CODE
        String jsonString = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader("c:\\Users\\jayde\\IdeaProjects\\Swellviews\\src\\SampleMovieFile.json"));
        } catch (FileNotFoundException fe) {
            System.out.println("The file could not be opened.");
            System.exit(0);
        }

        // Build the jsonString object line by line
        while (inFile.hasNextLine()) {
            jsonString = jsonString + inFile.nextLine();
        }

        Gson gson = new Gson();
        Movie[] movieList;  //A java primitive of Movie class
        ArrayList<Movie> CompleteMovieArrayList = new ArrayList<Movie>(); // An array list to hold a collection of movies
        movieList = gson.fromJson(jsonString, Movie[].class);
        Collections.addAll(CompleteMovieArrayList, movieList);

        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
        }
    }
}
