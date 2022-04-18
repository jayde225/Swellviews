package moviemodel;

import java.util.Comparator;
import java.util.Scanner;
import java.util.*;

/**
 * This class is used to sort a moviemodel.Movie array list by runtime, shortest to longest
 */
public class TimeCompare implements Comparator<Movie> {
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
