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
    private static ArrayList<Movie> arrayListName = new ArrayList<Movie>();

    private static int darkMode = 0;

    static JButton buttonApply = new JButton("Apply Filters");

    static JCheckBox ratedG = new JCheckBox("G");
    static JCheckBox ratedPG = new JCheckBox("PG");
    static JCheckBox ratedPG13 = new JCheckBox("PG-13");
    static JCheckBox ratedR = new JCheckBox("R");
    static JCheckBox ratedUR = new JCheckBox("Unrated");
    static JCheckBox genreAction = new JCheckBox("Action");
    static JCheckBox genreAdventure = new JCheckBox("Adventure");
    static JCheckBox genreMystery = new JCheckBox("Mystery");
    static JCheckBox genreRomance = new JCheckBox("Romance");
    static JCheckBox genreHorror = new JCheckBox("Horror");
    static JCheckBox genreComedy = new JCheckBox("Comedy");
    static JCheckBox genreDocumentary = new JCheckBox("Documentary");
    static JCheckBox genreDrama = new JCheckBox("Drama");
    static JCheckBox genreShort = new JCheckBox("Short");
    static JCheckBox genreSciFi = new JCheckBox("SciFi");
    static JCheckBox genreCrime = new JCheckBox("Crime");
    static JCheckBox genreThriller = new JCheckBox("Thriller");
    static JCheckBox genreFantasy = new JCheckBox("Fantasy");
    static JCheckBox genreAnimation = new JCheckBox("Animation");
    static JCheckBox genreFamily = new JCheckBox("Family");
    static JCheckBox genreMusical = new JCheckBox("Musical");
    static JCheckBox genreBiography = new JCheckBox("Biography");
    static JCheckBox genreSport = new JCheckBox("Sport");
    static JCheckBox genreHistory= new JCheckBox("History");

    public static void main(String[] args) {

        //GSON IMPLEMENTATION CODE--------------------------------------------------------------------------------------
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

        arrayListName = CompleteMovieArrayList;
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

        // DISPLAY MOVIES BY SEARCH------------------------------------------------------------------
        buttonSearch.addActionListener(new ActionListener() {
            ArrayList<Movie> searchedForMovies = new ArrayList<Movie>(); //An array list to hold movies that match search criteria
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                searchedForMovies.removeAll(searchedForMovies); //Reset the array
                //Since there is no way to tell what category the search term is (horror, title, actor, etc.)
                //Each attribute will need to be checked individually and the array of movies that match will
                //need to be checked so it is not put in multiple times
                //SEARCH BY TITLE
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!searchedForMovies.contains(testMovie)) {
                        if (testMovie.Title.contains(searchField.getText())) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }
                //SEARCH BY GENRE
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!searchedForMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains(searchField.getText())) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }
                //SEARCH BY YEAR
                for (Movie testMovie : CompleteMovieArrayList) {
                    String searchedForYear = String.valueOf(testMovie.getYear());
                    if (!searchedForMovies.contains(testMovie)) {
                        if (searchField.getText().contains(searchedForYear)) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }
                //SEARCH BY DIRECTOR
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!searchedForMovies.contains(testMovie)) {
                        if (testMovie.Director.contains(searchField.getText())) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }
                //SEARCH BY ACTORS
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!searchedForMovies.contains(testMovie)) {
                        if (testMovie.Actors.contains(searchField.getText())) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }
                //SEARCH BY WRITER
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!searchedForMovies.contains(testMovie)) {
                        if (testMovie.Writer.contains(searchField.getText())) {
                            searchedForMovies.add(testMovie);
                        }
                    }
                }

                //Display movies that were found in the search
                arrayListName = searchedForMovies;
                movieCounter = 0;
                movieGrid.removeAll();
                SwingUtilities.updateComponentTreeUI(homeFrame);
                movieListEnd = 0;
                movieGridUpdater(homeFrame, forwardAndBackButtons, arrayListName, movieGrid);
            }
        });

        //DISPLAY MOVIES BY USER SELECTED FILTER
        buttonApply.addActionListener( new ActionListener() {
            ArrayList<Movie> filteredMovies = new ArrayList<Movie>(); //An array list to hold movies that match filter criteria
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                filteredMovies.removeAll(filteredMovies); //Reset the Array
                //Go through each criteria and check the entire database for matching movies. Check if the movie has already been
                //added to the list of found movies. If it has, do not add again
                //FILTER BY RUNTIME
                /*for (Movie testMovie : CompleteMovieArrayList) {
                    Integer movieTime;
                    String tempStore;
                    if (!filteredMovies.contains(testMovie)) {
                        //Get runtime of the movie
                        tempStore = testMovie.getRunTime();
                        Scanner scan = new Scanner(tempStore);
                        movieTime = Integer.valueOf(scan.next(tempStore));
                        if (movieTime < 30) {
                            filteredMovies.add(testMovie); //Filter: "< 30 Min"
                        }
                        if (movieTime < 120) {
                            filteredMovies.add(testMovie); //Filter: "< 2 Hours"
                        }
                        scan.close();
                    }
                }*/ //This may have to be scrapped

                //FILTER BY MATURITY RATING
                //Since movies only have one rating, this can be condensed into one for loop
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if ((testMovie.Rated.equals("G") || testMovie.Rated.equals("TV-G")) && ratedG.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Rated G"
                        }
                        if (testMovie.Rated.equals("PG") && ratedPG.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Rated PG"
                        }
                        if (testMovie.Rated.equals("PG-13") && ratedPG13.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Rated PG-13"
                        }
                        if ((testMovie.Rated.equals("R") || testMovie.Rated.equals("TV-MA") || testMovie.Rated.equals("X")) && ratedR.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Rated R"
                        }
                        if (testMovie.getMPARating().equals("Not rated") && ratedUR.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Unrated/Not rated"
                        }
                    }
                }
                //SEARCH BY GENRE
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Action") && genreAction.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Action"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Adventure") && genreAdventure.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Adventure"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Mystery") && genreMystery.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Mystery"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Romance") && genreRomance.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Romance"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Horror") && genreHorror.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Horror"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Comedy") && genreComedy.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Comedy"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Documentary") && genreDocumentary.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Documentary"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Drama") && genreDrama.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Drama"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Short") && genreShort.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Short"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Sci-Fi") && genreSciFi.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Sci-Fi"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Crime") && genreCrime.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Crime"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Thriller") && genreThriller.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Thriller"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Fantasy") && genreFantasy.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Fantasy"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Animation") && genreAnimation.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Animation"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Family") && genreFamily.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Family"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Musical") && genreMusical.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Musical"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Biography") && genreBiography.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Biography"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("Sport") && genreSport.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "Sport"
                        }
                    }
                }
                for (Movie testMovie : CompleteMovieArrayList) {
                    if (!filteredMovies.contains(testMovie)) {
                        if (testMovie.Genre.contains("History") && genreHistory.isSelected()) {
                            filteredMovies.add(testMovie); //Filter: "History"
                        }
                    }
                }

                //Display movies that were found in the search
                arrayListName = filteredMovies;
                movieCounter = 0;
                movieGrid.removeAll();
                SwingUtilities.updateComponentTreeUI(homeFrame);
                movieListEnd = 0;
                movieGridUpdater(homeFrame, forwardAndBackButtons, arrayListName, movieGrid);
            }
        });

        JButton getMoreMovies = new JButton("Show More");
        getMoreMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                if (movieListEnd == 0) {
                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieGridUpdater(homeFrame, forwardAndBackButtons, arrayListName, movieGrid);
                }
            }
        });

        JButton goBack = new JButton ("Go Back");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movieCounter > 15-movieEnd) {
                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieCounter = movieCounter - 16 + movieEnd;
                    movieGridUpdater(homeFrame, forwardAndBackButtons, arrayListName, movieGrid);
                    movieEnd = 0;
                    movieListEnd = 0;
                }
            }
        });

        forwardAndBackButtons.add(goBack);
        forwardAndBackButtons.add(getMoreMovies);

        accountMenu(buttonAccount, loggedIn); // Calls the accountMenu function and attaches it to the "Account" button (buttonAccount)
        collectionMenu(buttonCollections); // Calls the collectionMenu function and attaches it to the "Collections" button (buttonCollections)
        filterMenu(buttonFilter);

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
        movieDetailsRightPanel.setLayout(new GridLayout(13,1));

        JLabel movieTitle = new JLabel("Title: " + movieArrayList.get(movieCounter - 1).getTitle());
        JLabel movieYear = new JLabel("Year: " + movieArrayList.get(movieCounter - 1).getYear().toString());
        JLabel movieGenre = new JLabel("Genre: " + movieArrayList.get(movieCounter - 1).getGenres());
        JLabel movieAgeRating = new JLabel("Age Rating: " + movieArrayList.get(movieCounter - 1).getMPARating());
        JLabel movieRuntime = new JLabel("Runtime: " + movieArrayList.get(movieCounter - 1).getRunTime());
        JLabel movieDirector = new JLabel("Director: " + movieArrayList.get(movieCounter - 1).getDirector());
        JLabel movieWriter = new JLabel("Writer: " + movieArrayList.get(movieCounter - 1).getWriters());
        JLabel movieActors = new JLabel("Actors: " + movieArrayList.get(movieCounter - 1).getActors());

        JLabel moviePlotLabel = new JLabel("Plot:");
        JTextArea moviePlot = new JTextArea (movieArrayList.get(movieCounter - 1).getPlot());
        moviePlot.setEditable(false);
        moviePlot.setWrapStyleWord(true);
        moviePlot.setLineWrap(true);

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

        JButton addToCollection = new JButton("Add to Collection");

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
        movieDetailsRightPanel.add(rateMovieButtons);
        movieDetailsRightPanel.add(addToCollection);

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


    //filter: genre, maturity rating, runtime (under 2 hours etc)
    public static void filterMenu(JButton buttonFilter){
        JPopupMenu filterPopupMenu = new JPopupMenu();
        JPanel filterBox = new JPanel();
        filterBox.setLayout(new BoxLayout(filterBox, BoxLayout.Y_AXIS));

        filterPopupMenu.add(filterBox);

        ButtonGroup filterOptions = new ButtonGroup();

        JLabel runtimeLabel = new JLabel("Runtime:");
        JCheckBox runtimeShort = new JCheckBox("<30 minutes");
        JCheckBox runtimeMid = new JCheckBox("<2 hours");

        JLabel ratingFilter = new JLabel("Maturity Rating:");

        JLabel genreLabel = new JLabel("Genre:");            ///I think this is all of them lol

        JButton buttonClear = new JButton("Clear Filters");

        filterBox.add(buttonClear);

        filterBox.add(runtimeLabel);
        filterBox.add(runtimeShort);
        filterBox.add(runtimeMid);

        filterBox.add(ratingFilter);
        filterBox.add(ratedG);
        filterBox.add(ratedPG);
        filterBox.add(ratedPG13);
        filterBox.add(ratedR);
        filterBox.add(ratedUR);

        filterBox.add(genreLabel);
        filterBox.add(genreAction);
        filterBox.add(genreAdventure);
        filterBox.add(genreMystery);
        filterBox.add(genreRomance);
        filterBox.add(genreHorror);
        filterBox.add(genreComedy);
        filterBox.add(genreDocumentary);
        filterBox.add(genreDrama);
        filterBox.add(genreShort);
        filterBox.add(genreSciFi);
        filterBox.add(genreCrime);
        filterBox.add(genreThriller);
        filterBox.add(genreFantasy);
        filterBox.add(genreAnimation);
        filterBox.add(genreFamily);
        filterBox.add(genreMusical);
        filterBox.add(genreBiography);
        filterBox.add(genreSport);
        filterBox.add(genreHistory);

        filterBox.add(buttonApply);

        buttonFilter.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                filterPopupMenu.show(buttonFilter, buttonFilter.getHorizontalAlignment(),buttonFilter.getHeight());
            }
        });

        buttonClear.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                runtimeShort.setSelected(false);
                runtimeMid.setSelected(false);

                ratedG.setSelected(false);
                ratedPG.setSelected(false);
                ratedPG13.setSelected(false);
                ratedR.setSelected(false);
                ratedUR.setSelected(false);

                genreAction.setSelected(false);
                genreAdventure.setSelected(false);
                genreMystery.setSelected(false);
                genreRomance.setSelected(false);
                genreHorror.setSelected(false);
                genreComedy.setSelected(false);
                genreDocumentary.setSelected(false);
                genreDrama.setSelected(false);
                genreShort.setSelected(false);
                genreSciFi.setSelected(false);
                genreCrime.setSelected(false);
                genreThriller.setSelected(false);
                genreFantasy.setSelected(false);
                genreAnimation.setSelected(false);
                genreFamily.setSelected(false);
                genreMusical.setSelected(false);
                genreBiography.setSelected(false);
                genreSport.setSelected(false);
                genreHistory.setSelected(false);
            }
        });
    }
}