//DESIGN PATTERN: Comparator is a part of the Strategy Pattern, so YearCompare is modeled after the Strategy Pattern

package moviemodel;
import java.util.Comparator;
import java.util.*;

/**
 * This class is used to sort a moviemodel.Movie array list by year, oldest to newest
 */
public class YearCompare implements Comparator<Movie> {
    /**
     * Used by the Comparator class to sort an array list by year
     *
     * @param m1 the first of two objects that will be compared
     * @param m2 the second of two objects that will be compared
     * @return -1 if the first object comes before the second object, and 1 for vice versa. Returns 0 if they are
     * the same
     */
    public int compare(Movie m1, Movie m2) {
        if (m1.getYear() < m2.getYear()) {
            return -1;
        }
        if (m1.getYear() > m2.getYear()) {
            return 1;
        } else return 0;
    }
}
