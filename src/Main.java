import com.google.gson.*; //import for Gson capabilities
import java.util.*; //import util for Scanner, Array List, and Sorting functions
import java.io.*; //import File class

/**
 * A class used for creating the main application
 */
public class Main {
    public static void main (String args[]) {
        //VARIABLES
        String path = "C:\\Users\\jayde\\Desktop\\SampleMovieFile.json";
        String line = "";
        String jsonString1 = "";

        try {
            //Create new file variable from input file
            File movieData = new File(path);

            //Create new scanner to iterate through file
            Scanner scanLine = new Scanner(movieData);

            //Read each line and concatenate to one nested json string
            while (scanLine.hasNextLine()) {
                line = scanLine.nextLine(); //Get line
                jsonString1.concat(line); //Then, add line to string
                System.out.printf(line);
            }


            //GSON CODE
            // Create a Gson object that will provide interface to convert strings to objects and objects to JSON strings
            Gson gson = new Gson();

            Movie[] movieList;  //A java primitive array of SimpleMovie
            ArrayList<Movie> testArrayList = new ArrayList<Movie>();

            movieList = gson.fromJson(jsonString1, Movie[].class);
            Collections.addAll(testArrayList, movieList); //Version 1 method of adding

            //Print out the titles of movies in the array list
            for (Movie testMovie : testArrayList) {
                System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
            }
            System.out.printf(String.valueOf(testArrayList.size()));

            scanLine.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Sorry, this file could not be opened");
            return;
        }
    }
}
