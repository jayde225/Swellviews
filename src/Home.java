import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

import com.google.gson.*; //import for Gson capabilities
import java.io.*; //import for File capabilities
import java.util.*; //import for ArrayList

public class Home {
    public static void main(String[] args){
        //Homepage Attribute Declarations
        JFrame frame = new JFrame("Swellviews");
        JTextField searchField = new JTextField("Enter Movie Name");
        JButton buttonSearch = new JButton("Search");
        JSeparator spacer = new JSeparator();
        JButton buttonFilter = new JButton("Filter");
        JButton buttonCollections = new JButton("Collections");
        JLabel labelAccount = new JLabel("Account");

        //MEGAN -- Add a dropdown thingamabob here (some stuff at the bottom for you to work with, but it's very early stage)

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

        //Use GSON to create an ArrayList of movies in JSON file
        Gson gson = new Gson();
        Movie[] movieList;  //A java primitive of Movie class
        ArrayList<Movie> CompleteMovieArrayList = new ArrayList<Movie>(); // An array list to hold a collection of movies
        movieList = gson.fromJson(jsonString, Movie[].class);
        Collections.addAll(CompleteMovieArrayList, movieList);


        //Homepage Header Attributes (added from above)
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1,8));
        header.add(searchField);
        header.add(buttonSearch);
        header.add(spacer);
        header.add(buttonFilter);
        header.add(buttonCollections);
        header.add(labelAccount);

        //Testing MovieDisplay with blank Rectangles named "Movie_"
        MovieDisplay movie1 = new MovieDisplay("Movie1");
        MovieDisplay movie2 = new MovieDisplay("Movie2");
        MovieDisplay movie3 = new MovieDisplay("Movie3");
        MovieDisplay movie4 = new MovieDisplay("Movie4");
        MovieDisplay movie5 = new MovieDisplay("Movie5");
        MovieDisplay movie6 = new MovieDisplay("Movie6");
        MovieDisplay movie7 = new MovieDisplay("Movie7");
        MovieDisplay movie8 = new MovieDisplay("Movie8");

        frame.setLayout(new BorderLayout());

        //Testing grid layout
        movie1.setSize(90,140);
        movie2.setSize(90,140);
        movie3.setSize(90,140);
        movie4.setSize(90,140);
        movie5.setSize(90,140);
        movie6.setSize(90,140);
        movie7.setSize(90,140);
        movie8.setSize(90,140);

        String displayName = "Suggestions"; //Contains homepage grid name for display. Will be changed to show where movies are coming from (Collections, Search Results, etc.)

        JPanel movieGrid = new JPanel();
        movieGrid.setLayout(new GridLayout(2,4));
        movieGrid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), displayName));

        //Testing grid layout
        movieGrid.add(movie1);
        movieGrid.add(movie2);
        movieGrid.add(movie3);
        movieGrid.add(movie4);
        movieGrid.add(movie5);
        movieGrid.add(movie6);
        movieGrid.add(movie7);
        movieGrid.add(movie8);


        //Positioning homepage frame elements
        frame.add(movieGrid,BorderLayout.CENTER); //Adds MovieDisplay test to center of page
        frame.add(header, BorderLayout.PAGE_START);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    //Contains the Filter Dropdown Menu, will be implemented into homepage soon when I've figured out how it works - George
    public void filterDropDown() {
        String[] options = {"Genre", "Age Rating", "Runtime"};

        JComboBox<String> jComboBox = new JComboBox<>(options);
        jComboBox.setBounds(80,50,140,20);
    }
}
