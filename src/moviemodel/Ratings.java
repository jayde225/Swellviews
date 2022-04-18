package moviemodel;

/**
 * A class for creating and storing moviemodel.Ratings objects
 */
public class Ratings {
    //VARIABLES
    String Source;
    String Value;

    //GET FUNCTIONS
    /**
     * Returns the name of the Rating object
     * @return the name of the rating source
     */
    public String getSource() {return Source;}

    /**
     * Returns the value of the Rating object
     * @return the value of the rating
     */
    public String getValue() {return Value;}
}
