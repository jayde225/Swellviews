import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import com.google.gson.*; //import for Gson capabilities

public class Home extends JFrame{
    public static void main(String[] args) {
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


        boolean loggedIn = false; //Needs to be connected to the user class *************************************************************************************************
        //Homepage Attribute Declarations
        JFrame homeFrame = new JFrame("Swellviews");
        JTextField searchField = new JTextField("Enter Movie Name"); //figureout how to erase text on click in field
                                                                     //so can search without having to delete default text, or just make label (see accountmenu/login)
        JButton buttonSearch = new JButton("Search");
        JSeparator spacer = new JSeparator(); //Temporary Solution (maybe?) for separating header buttons
        JButton buttonFilter = new JButton("Filter");//JCheckBox allows multiselect, JRadioButton allows single
        JButton buttonCollections = new JButton("Collections");
        JButton buttonAccount = new JButton("Account");


        //Homepage Header Attributes (added from above)
        JMenuBar header = new JMenuBar();
        header.setLayout(new GridLayout(1, 8));
        header.add(searchField);
        header.add(buttonSearch);
        header.add(spacer);
        header.add(buttonFilter);//***************************************
        header.add(buttonCollections);
        header.add(buttonAccount);

        accountMenu(buttonAccount, loggedIn); // Calls the accountMenu function and attaches it to the "Account" button (buttonAccount)
        collectionMenu(buttonCollections); // Calls the collectionMenu function and attaches it to the "Collections" button (buttonCollections)

        //Testing MovieDisplay with blank movies named "Movie_"
        MovieDisplay movie1 = new MovieDisplay("Movie1");
        MovieDisplay movie2 = new MovieDisplay("Movie2");
        MovieDisplay movie3 = new MovieDisplay("Movie3");
        MovieDisplay movie4 = new MovieDisplay("Movie4");
        MovieDisplay movie5 = new MovieDisplay("Movie5");
        MovieDisplay movie6 = new MovieDisplay("Movie6");
        MovieDisplay movie7 = new MovieDisplay("Movie7");
        MovieDisplay movie8 = new MovieDisplay("Movie8");

        homeFrame.setLayout(new BorderLayout()); // Sets the homepage frame to a border layout (5 sections: north, south, east, west, and center)

        String displayName = "Suggestions"; //Contains homepage grid name for display. Will be changed to show where movies are coming from (Collections, Search Results, etc.)

        //MovieDisplay grid layout on homepage:
        JPanel movieGrid = new JPanel();
        movieGrid.setLayout(new GridLayout(2,4));
        movieGrid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), displayName)); //Etched border to display type of content being presented (set by string displayName above)

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
        homeFrame.add(movieGrid, BorderLayout.CENTER); //Adds MovieDisplay test to center of page
        homeFrame.add(header, BorderLayout.PAGE_START);
        homeFrame.setSize(500, 500);
        homeFrame.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void accountMenu(JButton buttonAccount, boolean loggedIn){ //make popups bigger

        JPopupMenu accountmenu = new JPopupMenu();
        JMenu loginmenu = new JMenu("Log In");
        //final JPopupMenu logoutmenu = new JPopupMenu();

        JButton enterB = new JButton("Enter");          // Confirm login button
        JMenuItem logoutB = new JMenuItem("Log out");   // Logout button

        JLabel userLabel = new JLabel("Username:");     // Username Label
        JLabel passLabel = new JLabel("Password:");     // Password Label

        JTextField userField = new JTextField();            // User enters username
        JTextField passField = new JTextField();            // User enters password

        loginmenu.add(userLabel); //setMenuLoction(int x, int y) for login window
        loginmenu.add(userField);
        loginmenu.add(passLabel);
        loginmenu.add(passField);
        loginmenu.add(enterB);

        if(loggedIn == true) {accountmenu.add(logoutB);}       // If logged in, show logout menu
        else{accountmenu.add(loginmenu);}     // If logged out, show login menu

        buttonAccount.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                accountmenu.show(buttonAccount, buttonAccount.getHorizontalAlignment(), buttonAccount.getHeight());
            }
        });

        enterB.addActionListener( new ActionListener() { /** No functionality Currently */
            public void actionPerformed(ActionEvent button_pressed) {
                //loginmenu.show(loginB, loginB.getWidth(), loginB.getHeight());
                javax.swing.MenuSelectionManager.defaultManager().clearSelectedPath();
                //once closes, initiate login procedure
                //how use enter key OR click to submit
                //need error handling (not close) for incorrect login, create account
                // or to show login successful
            }
        } );

        /*logoutB.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                logoutmenu.show(logoutB, logoutB.getWidth(), logoutB.getHeight());

                //This will need to do the logout procedure, what ever that looks like ***************
                //will also need to close menu and somehow make loggedIn = false; again
                //since menu item, will not use action listener and will need to act
                //from that menu selection
            }
        } );*/ /** Logout (Unfinished) */
    }

    public static void collectionMenu (JButton buttonCollections) {

        JPopupMenu collectionMenu = new JPopupMenu(); // Main popup for list of collections
        JFrame createNewCollection = new JFrame("Create a New Collection"); //"Popup" window for entering the name of a new collection
            JPanel createNewCollectionPanel = new JPanel(); //Needed for BoxLayout
            createNewCollection.add(createNewCollectionPanel);
            createNewCollectionPanel.setLayout(new BoxLayout(createNewCollectionPanel, BoxLayout.Y_AXIS)); // BoxLayout is simple to organize elements in a column
            createNewCollection.setResizable(false); // Makes the window non-resizable

        //Button for creating a new collection:
        JButton createCollection = new JButton("Create a New Collection");

        //Creating items for the collectionMenu list:
        JMenuItem testItem1 = new JMenuItem("Test Item 1");
        JMenuItem testItem2 = new JMenuItem("Test Item 123456789");

        //Adding items to the collectionMenu list:
        collectionMenu.add(testItem1);
        collectionMenu.add(testItem2);
        collectionMenu.add(createCollection); // Button for creating a new collection (should be kept at the bottom)

        //Creating items for the createNewCollection popup:
        JLabel collectionNameLabel = new JLabel("Enter Collection Name:");
        JTextField collectionNameField = new JTextField("New Collection");
            collectionNameField.setSize(100,10);
        JButton collectionCreate = new JButton ("Create");

        //Adding items to the createNewCollection popup:
        createNewCollectionPanel.add(collectionNameLabel);
        createNewCollectionPanel.add(collectionNameField);
        createNewCollectionPanel.add(collectionCreate);

        createNewCollection.setSize(310, 100);
        createNewCollection.setLocationRelativeTo(null);

        //ActionListener for showing collection list menu when "Collections" button pressed
        buttonCollections.addActionListener (new ActionListener() { // When the collections button (buttonCollections) is pressed...
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                collectionMenu.show(buttonCollections, buttonCollections.getHorizontalAlignment(),buttonCollections.getHeight()); //Menu appears below and on the right side of the collections button (buttonCollections)
            }
        });

        //ActionListener for showing new collection creation menu when "Create a New Collection" button pressed
        createCollection.addActionListener (new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewCollection.setVisible(true);
            }
        });

        //ActionListener for adding a new collection when "Create" is pressed within the createNewCollection Frame
        collectionCreate.addActionListener (new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(collectionNameField.getText());
            }
        });


    }
}