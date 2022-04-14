import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.io.*;

import com.google.gson.*; //import for Gson capabilities


public class Home extends JFrame{

    private static int movieCounter = 0;
    private static int movieEnd = 0;
    private static int movieListEnd = 0;

    private static int darkMode = 0;

    public static void main(String[] args) {

        //GSON IMPLEMENTATION CODE--------------------------------------------------------------------------------------
        String jsonString = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader("C:\\Users\\gmcop\\IdeaProjects\\Swellviews_Local\\src\\SampleMovieFile.json"));
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
        //--------------END GSON IMPLEMENTATION-------------------------------------------------------------------------

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

        JPanel forwardAndBackButtons = new JPanel();

        String displayName = "Suggestions"; //Contains homepage grid name for display. Will be changed to show where movies are coming from (Collections, Search Results, etc.)

        //MovieDisplay grid layout on homepage:
        JPanel movieGrid = new JPanel();
        movieGrid.setLayout(new GridLayout(2,4));
        movieGrid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), displayName)); //Etched border to display type of content being presented (set by string displayName above)

        JButton getMoreMovies = new JButton("Show More");
        getMoreMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                if (movieListEnd == 0) {
                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieGridUpdater(homeFrame, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);
                }
            }
        });

        JButton goBack = new JButton ("Go Back");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movieCounter > 15) {
                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieCounter = movieCounter - 16 + movieEnd;
                    movieGridUpdater(homeFrame, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);
                    movieEnd = 0;
                    movieListEnd = 0;
                }
            }
        });

        forwardAndBackButtons.add(goBack);
        forwardAndBackButtons.add(getMoreMovies);

        accountMenu(buttonAccount, loggedIn); // Calls the accountMenu function and attaches it to the "Account" button (buttonAccount)
        collectionMenu(buttonCollections); // Calls the collectionMenu function and attaches it to the "Collections" button (buttonCollections)

        homeFrame.setLayout(new BorderLayout()); // Sets the homepage frame to a border layout (5 sections: north, south, east, west, and center)

        movieGridUpdater(homeFrame, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);

        //Positioning homepage frame elements
        homeFrame.add(header, BorderLayout.PAGE_START);
        homeFrame.add(forwardAndBackButtons, BorderLayout.PAGE_END);
        homeFrame.setSize(1500, 1000);
        homeFrame.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void movieGridUpdater(JFrame home, JPanel forwardAndBackButtons, ArrayList<Movie> movieArrayList, JPanel movieGrid){

        if (darkMode == 0){
            movieGrid.setBackground(null);
            forwardAndBackButtons.setBackground(null);
        }
        else {
            movieGrid.setBackground(Color.darkGray);
            forwardAndBackButtons.setBackground(Color.darkGray);
        }

        Iterator e = movieArrayList.iterator();
        int iteratorCounter = 0;

        while(movieCounter > iteratorCounter) {
            e.next();
            iteratorCounter++;
        }

        for (int counter = 0; counter < 8; counter++) {
            if (e.hasNext()) {
                MovieDisplay movie1 = new MovieDisplay(movieArrayList.get(movieCounter).getTitle(), movieArrayList.get(movieCounter).getPosterLink(), darkMode, 1);
                movieCounter++;
                movieGrid.add(movie1);
                movieSelection(movie1, movieArrayList);
                e.next();
            }
            else {
                movieEnd = 8 - counter;
                movieListEnd = 1;
                break;
            }
        }
        home.add(movieGrid, BorderLayout.CENTER); //Adds MovieDisplay test to center of page
    }

    public static void movieSelection(MovieDisplay movieSelected, ArrayList<Movie> movieArrayList){

        JFrame movieDetailsFrame = new JFrame(movieArrayList.get(movieCounter - 1).getTitle());
        JPanel movieDetailsRightPanel = new JPanel();
        movieDetailsRightPanel.setLayout(new GridLayout(11,1));

        JLabel movieTitle = new JLabel("Title: " + movieArrayList.get(movieCounter - 1).getTitle());
        JLabel movieYear = new JLabel("Year: " + movieArrayList.get(movieCounter - 1).getYear().toString());
        JLabel movieGenre = new JLabel("Genre: " + movieArrayList.get(movieCounter - 1).getGenres());
        JLabel movieAgeRating = new JLabel("Age Rating: " + movieArrayList.get(movieCounter - 1).getMPARating());
        JLabel movieRuntime = new JLabel("Runtime: " + movieArrayList.get(movieCounter - 1).getRunTime());
        JLabel movieDirector = new JLabel("Director: " + movieArrayList.get(movieCounter - 1).getDirector());
        JLabel movieWriter = new JLabel("Writer: " + movieArrayList.get(movieCounter - 1).getWriters());
        JLabel movieActors = new JLabel("Actors: " + movieArrayList.get(movieCounter - 1).getActors());

        JLabel moviePlot = new JLabel ("Plot: " + movieArrayList.get(movieCounter - 1).getPlot());

        JLabel movieLanguage = new JLabel();
        JLabel movieCountry = new JLabel();
        JLabel movieAwards = new JLabel("Awards: " + movieArrayList.get(movieCounter - 1).getAwards());
        JLabel movieRatings = new JLabel();
        JLabel movieRating = new JLabel();

        JPanel rateMovieButtons = new JPanel();
        JButton dislikeMovie = new JButton("Dislike");
        JButton likeMovie = new JButton("Like");
        rateMovieButtons.add(dislikeMovie);
        rateMovieButtons.add(likeMovie);

        movieDetailsRightPanel.add(movieTitle);
        movieDetailsRightPanel.add(movieGenre);
        movieDetailsRightPanel.add(movieYear);
        movieDetailsRightPanel.add(movieAgeRating);
        movieDetailsRightPanel.add(movieRuntime);
        movieDetailsRightPanel.add(movieDirector);
        movieDetailsRightPanel.add(movieWriter);
        movieDetailsRightPanel.add(movieActors);
        movieDetailsRightPanel.add(movieAwards);
        movieDetailsRightPanel.add(moviePlot);
        movieDetailsRightPanel.add(rateMovieButtons);

        movieDetailsFrame.setLayout(new GridLayout(1,2));
        movieDetailsFrame.setSize(700, 550);
        movieDetailsFrame.setLocationRelativeTo(null);

        MovieDisplay movieSelectionDisplay = new MovieDisplay(movieArrayList.get(movieCounter-1).getTitle(), movieArrayList.get(movieCounter-1).getPosterLink(), darkMode, 1);

        movieSelected.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                movieDetailsFrame.add(movieSelectionDisplay);
                movieDetailsFrame.add(movieDetailsRightPanel);
                movieDetailsFrame.setVisible(true);
            }

            //These shouldn't be needed:
            @Override
            public void mousePressed(MouseEvent e) {

            }
            @Override
            public void mouseReleased(MouseEvent e) {

            }
            @Override
            public void mouseEntered(MouseEvent e) {

            }
            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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

        JMenuItem darkModeToggle = new JMenuItem("Toggle Dark Mode");
        accountmenu.add(darkModeToggle);

        darkModeToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (darkMode == 0){darkMode = 1;}
                else {darkMode = 0;}

            }
        });

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

   //public static void darkModeUpdator (JFrame home, JPanel movieGrid );

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