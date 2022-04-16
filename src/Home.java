import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.io.*;

import com.google.gson.*; //import for Gson capabilities


public class Home extends JFrame{

    private static int movieCounter = 0; // Used by movieGridUpdator to know where in the ArrayList we are
    private static int movieEnd = 0; // Used when the movieGrid has less than 8 MovieDisplays. 8 - number of movies missing = movieEnd which is used in the "Go Back" ActionListener to know how far back to go.
    private static int movieListEnd = 0; // Used by the "Show More" button ActionListener to know whether there are more movies to show or not

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

        ArrayList<Movie> test = new ArrayList<Movie>();
        test = CompleteMovieArrayList;
        //--------------END GSON IMPLEMENTATION-------------------------------------------------------------------------

        boolean loggedIn = false; //Needs to be connected to the user class *************************************************************************************************
        //Homepage Attribute Declarations
        JFrame homeFrame = new JFrame("Swellviews"); // This is the main frame containing the homepage
        JTextField searchField = new JTextField("Enter Movie Name"); //figureout how to erase text on click in field
                                                                    //so can search without having to delete default text, or just make label (see accountmenu/login)
        JButton buttonSearch = new JButton("Search"); // Used to search based on the text in the searchField JTextField
        JSeparator spacer = new JSeparator(); //Temporary Solution (maybe?) for separating header buttons
        JButton buttonFilter = new JButton("Filter");//JCheckBox allows multiselect, JRadioButton allows single
        JButton buttonCollections = new JButton("Collections"); // Shows popup on click with existing collections and option to create a new collection
        JButton buttonAccount = new JButton("Account"); // Shows accountMenu on click with login/logout options

        //Homepage Header Attributes (added from above)
        JMenuBar header = new JMenuBar(); // Stores the top components (search bar, search button, filter button, collections button, account button
        header.setLayout(new GridLayout(1, 8)); // sets the layout of the header to have eight equal spaces for components listed above and below
        //Adding components to the header JMenuBar:
        header.add(searchField);
        header.add(buttonSearch);
        header.add(spacer);
        header.add(buttonFilter);
        header.add(buttonCollections);
        header.add(buttonAccount);

        JPanel forwardAndBackButtons = new JPanel(); //A special panel at the bottom (below movieGrid) for the forward and back buttons controlling the movies shown in the movieGrid.

        String displayName = "Suggestions"; //Contains homepage grid name for display. Will be changed to show where movies are coming from (Collections, Search Results, etc.)

        //MovieDisplay grid layout on homepage:
        JPanel movieGrid = new JPanel(); //Contains movieDisplay objects
        movieGrid.setLayout(new GridLayout(2,4)); // movieGrid will have two rows and four columns (8 movies MAX)
        movieGrid.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), displayName)); //Etched border to display type of content being presented (set by string displayName above)

        JButton getMoreMovies = new JButton("Show More"); // Moves forward 8 items in the movieGrid
        getMoreMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                if (movieListEnd == 0) { //movieListEnd will be 1 if there are no more movies in the ArrayList
                    movieGrid.removeAll(); //removes existing items from the movieGrid to make room for new items
                    SwingUtilities.updateComponentTreeUI(homeFrame); //Refreshes movieGrid to get rid of removed items
                    movieGridUpdater(homeFrame, CompleteMovieArrayList, movieGrid); //Calls movieGridUpdator to show the next 8 items
                }
            }
        });

        JButton goBack = new JButton ("Go Back"); // Moves backwards 8 (or less if on last page) items in the movieGrid
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movieCounter > 15-movieEnd) { //Ensures the movieGrid cannot go past the beginning of the ArrayList
                    movieGrid.removeAll(); //removes existing items from the movieGrid to make room for new items
                    SwingUtilities.updateComponentTreeUI(homeFrame); //Refreshes movieGrid to get rid of removed items
                    movieCounter = movieCounter - 16 + movieEnd; // Subtracts 16 (+movieEnd if the last page shown had less then 8 movies) to go backward one page in the Arraylist
                    movieGridUpdater(homeFrame, CompleteMovieArrayList, movieGrid); // Calls movieGridUpdator to show the last 8 items
                    movieEnd = 0; // Resets movieEnd to default 0 (movieEnd is used when the last page has fewer than 8 movies).
                    movieListEnd = 0; // Resets moveListEnd to default 0 (means there are more movies to be shown) because the movieGrid has gone backwards
                }
            }
        });

        forwardAndBackButtons.add(goBack); //Adds the "Go Back" button to the bottom panel (Left Side)
        forwardAndBackButtons.add(getMoreMovies); //Adds the "Show More" button to the bottom panel (Right side)

        accountMenu(buttonAccount, loggedIn); // Calls the accountMenu function and attaches it to the "Account" button (buttonAccount)
        collectionMenu(buttonCollections); // Calls the collectionMenu function and attaches it to the "Collections" button (buttonCollections)

        homeFrame.setLayout(new BorderLayout()); // Sets the homepage frame to a border layout (5 sections: north, south, east, west, and center)

        movieGridUpdater(homeFrame, CompleteMovieArrayList, movieGrid); // Initial call to MovieGridUpdator, shows the first page of movies on startup

        //Positioning homepage frame elements
        homeFrame.add(header, BorderLayout.PAGE_START);
        homeFrame.add(forwardAndBackButtons, BorderLayout.PAGE_END);
        homeFrame.setSize(1500, 1000); //Initial size of homeFrame on program startup
        homeFrame.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     *Updates the movieGrid with new MovieDisplay objects from an ArrayList of Movie objects. Uses static int movieCounter to know what movies from the ArrayList to display.
     * @param home The JFrame to be used (should always be homeFrame in current version)
     * @param movieArrayList The ArrayList of Movie objects to display
     * @param movieGrid The JPanel destination for the MovieDisplay objects (should always be movieGrid in current version)
     */
    public static void movieGridUpdater(JFrame home, ArrayList<Movie> movieArrayList, JPanel movieGrid){

        Iterator e = movieArrayList.iterator(); // Used to iterate though list, ensuring the next movie exists
        int iteratorCounter = 0; //Used to count the number of iterations being

        // Catches up to where in the ArrayList the movieGrid is by using the static int movieCounter
        while(movieCounter > iteratorCounter) {
            e.next(); //Gets the next item in movieArrayList
            iteratorCounter++; //Increments the iteratorCounter
        }

        for (int counter = 0; counter < 8; counter++) { //Loops 8 times at max (movieGrid can only display 8 items at once)
            if (e.hasNext()) { // Checks if the ArrayList has ended or not
                MovieDisplay movie1 = new MovieDisplay(movieArrayList.get(movieCounter).getTitle(), movieArrayList.get(movieCounter).getPosterLink(), 1); //Creates a new MovieDisplay object for the next movie in the ArrayList
                movieCounter++; //Increments movieCounter
                movieGrid.add(movie1); //Adds the MovieDisplay object to the movieGrid for display
                movieSelection(movie1, movieArrayList); //Creates an ActionListener for the MovieDisplay and prepares the details
                e.next(); //Finds the next movie in the ArrayList
            }
            else { //If the ArrayList is at the end:
                movieEnd = 8 - counter; // Sets the static movieEnd int to the number of movies NOT shown, used in the "Go Back" button ActionListener
                movieListEnd = 1; // Sets static movieListEnd to 1 so that the "Show More" button cannot continue past the end of the ArrayList
                break; //Exits loop if there are no more movies in the ArrayList
            }
        }
        home.add(movieGrid, BorderLayout.CENTER); //Adds updated movieGrid to center of homepage
    }

    /**
     * Creates MouseListeners for each MovieDisplay object currently being shown in the movieGrid. On Click, a new frame is shown with the details and rating buttons of the Movie object connected to the MovieDisplay object
     * @param movieSelected MovieDisplay object sent by the movieGridUpdator (should be called "movie1")
     * @param movieArrayList The ArrayList of Movie objects being displayed
     */
    public static void movieSelection(MovieDisplay movieSelected, ArrayList<Movie> movieArrayList){

        JFrame movieDetailsFrame = new JFrame(movieArrayList.get(movieCounter - 1).getTitle()); //JFrame for displaying Movie object Details. Subtracts 1 from movieCounter because movieCounter is incremented before the method is called.
        JPanel movieDetailsRightPanel = new JPanel(); // JPanel used for containing movie details provided by the Movie class
        movieDetailsRightPanel.setLayout(new GridLayout(12,1)); // Sets JPanel above to have 12 rows for displaying details (1 row for each detail)

        //Creating JLabels for each Movie object detail provided by the Movie class
        JLabel movieTitle = new JLabel("Title: " + movieArrayList.get(movieCounter - 1).getTitle());
        JLabel movieYear = new JLabel("Year: " + movieArrayList.get(movieCounter - 1).getYear().toString());
        JLabel movieGenre = new JLabel("Genre: " + movieArrayList.get(movieCounter - 1).getGenres());
        JLabel movieAgeRating = new JLabel("Age Rating: " + movieArrayList.get(movieCounter - 1).getMPARating());
        JLabel movieRuntime = new JLabel("Runtime: " + movieArrayList.get(movieCounter - 1).getRunTime());
        JLabel movieDirector = new JLabel("Director: " + movieArrayList.get(movieCounter - 1).getDirector());
        JLabel movieWriter = new JLabel("Writer: " + movieArrayList.get(movieCounter - 1).getWriters());
        JLabel movieActors = new JLabel("Actors: " + movieArrayList.get(movieCounter - 1).getActors());
        JLabel movieLanguage = new JLabel();
        JLabel movieCountry = new JLabel();
        JLabel movieAwards = new JLabel("Awards: " + movieArrayList.get(movieCounter - 1).getAwards());
        JLabel movieRatings = new JLabel();
        JLabel movieRating = new JLabel();
        JLabel moviePlotLabel = new JLabel("Plot:");
        JTextArea moviePlot = new JTextArea (movieArrayList.get(movieCounter - 1).getPlot());
        moviePlot.setEditable(false);
        moviePlot.setWrapStyleWord(true);
        moviePlot.setLineWrap(true);

        //Rating Buttons:
        JPanel rateMovieButtons = new JPanel(); //JPanel containing Like and Dislike buttons declared below. Will be in the bottom row of movieDetailsRightPanel.
        JButton dislikeMovie = new JButton("Dislike"); //Dislike Button
        JButton likeMovie = new JButton("Like");       //Like Button
        rateMovieButtons.add(dislikeMovie);                //Adds Dislike button to rateMovieButtons JPanel
        rateMovieButtons.add(likeMovie);                   //Adds Like button to rateMovieButtons JPanel

        //Adding movie details and rateMovieButtons into movieDetailsRightPanel JPanel
        movieDetailsRightPanel.add(movieTitle);
        movieDetailsRightPanel.add(movieGenre);
        movieDetailsRightPanel.add(movieYear);
        movieDetailsRightPanel.add(movieAgeRating);
        movieDetailsRightPanel.add(movieRuntime);
        movieDetailsRightPanel.add(movieDirector);
        movieDetailsRightPanel.add(movieWriter);
        movieDetailsRightPanel.add(movieActors);
        movieDetailsRightPanel.add(movieAwards);
        movieDetailsRightPanel.add(moviePlotLabel);
        movieDetailsRightPanel.add(moviePlot);
        movieDetailsRightPanel.add(rateMovieButtons); //Contains Like and Dislike buttons

        //Setting movieDetailsFrame layout, size, and location:
        movieDetailsFrame.setLayout(new GridLayout(1,2)); //Left column contains MovieDisplay, Right column contains movieDetailsRightPanel
        movieDetailsFrame.setSize(700, 550);
        movieDetailsFrame.setLocationRelativeTo(null);

        MovieDisplay movieSelectionDisplay = new MovieDisplay(movieArrayList.get(movieCounter-1).getTitle(), movieArrayList.get(movieCounter-1).getPosterLink(), 1); //Calls the method to re-ready the ActionListener and movie details JFrame. Otherwise, it is lost.

        movieSelected.addMouseListener(new MouseListener() { //Listens to MovieDisplay object in the movieGrid and displays movieDetailsFrame on Click
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


    /**
     * Creates an ActionListener on a JButton that will open the collectionMenu JPopupMenu (also contained in this method) containing the list of created Collections and an option to create a new collection.
     * @param buttonCollections JButton that will open the collectionMenu
     */
    public static void collectionMenu (JButton buttonCollections) {

        JPopupMenu collectionMenu = new JPopupMenu(); // Main popup for list of collections
        JFrame createNewCollection = new JFrame("Create a New Collection"); //"Popup" window for entering the name of a new collection
        JPanel createNewCollectionPanel = new JPanel(); //Needed for BoxLayout
        createNewCollection.add(createNewCollectionPanel);
        createNewCollectionPanel.setLayout(new BoxLayout(createNewCollectionPanel, BoxLayout.Y_AXIS)); // BoxLayout is simple to organize elements in a column
        createNewCollection.setResizable(false); // Makes the window non-resizable

        //Button for creating a new collection:
        JButton createCollection = new JButton("Create a New Collection"); //Attached to ActionListener below, used to create a new Collection

        //Creating test items for the collectionMenu list:
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

        //Setting size and location of popup menu
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