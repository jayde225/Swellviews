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
            inFile = new Scanner(new FileReader("C:\\Users\\jayde\\IdeaProjects\\Swellviews\\src\\SampleMovieFile.json"));
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

        //TEST - PRINT OUT HORROR MOVIES ONLY
        /*for (Movie testMovie : CompleteMovieArrayList) {
           if (testMovie.Genre.contains("Horror")) {
                System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
            }
        }

        //TEST - PRINT OUT MOVIES MADE IN 2003
        System.out.println();
        System.out.println();
        for (Movie testMovie : CompleteMovieArrayList) {
            if (testMovie.Year.equals(2003)) {
                System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
            }
        }

        //TEST - PRINT OUT MOVIES THAT STAR JOHNNY DEPP
        System.out.println();
        System.out.println();
        for (Movie testMovie : CompleteMovieArrayList) {
            if (testMovie.Actors.contains("Johnny Depp")) {
                System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
            }
        }

        //TEST - PRINT OUT ALL RATING SOURCES OF JOHNNY DEPP MOVIES
        System.out.println();
        System.out.println();
        for (Movie testMovie : CompleteMovieArrayList) {
            if (testMovie.Actors.contains("Johnny Depp")) {
                for (Ratings testRating: testMovie.Ratings) {
                    System.out.printf("This is the name of the rating source: " + testRating.Source + '\n');
                }
            }
        }*/


        //SORTING TEST FUNCTIONS
        //TEST - SORT BY YEAR, EARLIEST TO RECENT
        /*System.out.printf("PRINT BY MOVIE YEAR, EARLIEST TO RECENT \n");
        YearCompare yearCompare = new YearCompare();
        Collections.sort(CompleteMovieArrayList, yearCompare);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + ", " + testMovie.getYear() + '\n');
        }

        //TEST - SORT BY YEAR, EARLIEST TO RECENT
        System.out.printf("PRINT BY MOVIE YEAR, RECENT TO OLDEST \n");
        Collections.reverse(CompleteMovieArrayList);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + ", " + testMovie.getYear() + '\n');
        }*/

        //TEST - SORT BY NAME, A TO Z
        /*System.out.printf("PRINT BY MOVIE NAME, A TO Z \n");
        NameCompare nameCompare = new NameCompare();
        Collections.sort(CompleteMovieArrayList, nameCompare);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
        }

        //TEST - SORT BY NAME, Z TO A
        System.out.printf("PRINT BY MOVIE NAME, Z TO A \n");
        Collections.reverse(CompleteMovieArrayList);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + '\n');
        }*/

        //TEST - SORT BY RUNTIME, SHORTEST TO LONGEST
        System.out.printf("PRINT BY MOVIE RUNTIME, SHORTEST TO LONGEST \n");
        TimeCompare timeCompare = new TimeCompare();
        Collections.sort(CompleteMovieArrayList, timeCompare);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + ", " + testMovie.getRunTime() + '\n');
        }

        //TEST - SORT BY RUNTIME, LONGEST TO SHORTEST
        System.out.printf("PRINT BY MOVIE RUNTIME, LONGEST TO SHORTEST \n");
        Collections.reverse(CompleteMovieArrayList);
        for (Movie testMovie : CompleteMovieArrayList) {
            System.out.printf("This is the name of the movie object: " + testMovie.getTitle() + ", " + testMovie.getRunTime() + '\n');
        }
    }
}
