package moviemodel;

import java.util.Comparator;
import java.util.*;

/**
 * This class is used to sort a moviemodel.Movie array list by name, A to Z
 */
public class NameCompare implements Comparator<Movie> {
    /**
     * Used by the Comparator class to sort an array list by name
     *
     * @param m1 the first of two objects that will be compared
     * @param m2 the second of two objects that will be compared
     * @return -1 if the first object comes before the second object, and 1 for vice versa. Returns 0 if they are
     * the same
     */
    public int compare(Movie m1, Movie m2) {
        String firstTitle = m1.getTitle();
        String secondTitle = m2.getTitle();
        int compareTitle = firstTitle.compareTo(secondTitle);

        if (compareTitle < 0) {
            return -1;
        }
        if (compareTitle > 0) {
            return 1;
        } else return 0;
    }
}
