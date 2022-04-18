import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.io.*;

import MovieData.Movie;
import com.google.gson.*; //import for Gson capabilities

public class Home extends JFrame{

    private static int movieCounter = 0;
    private static int movieEnd = 0;
    private static int movieListEnd = 0;

    public static void main(String[] args) {

        //GSON IMPLEMENTATION CODE--------------------------------------------------------------------------------------
        String jsonString = "";
        Scanner inFile = null;
        try {
            inFile = new Scanner(new FileReader("src\\SampleMovieFile.json"));
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

        Iterator e = movieArrayList.iterator();
        int iteratorCounter = 0;

        while(movieCounter > iteratorCounter) {
            e.next();
            iteratorCounter++;
        }

        for (int counter = 0; counter < 8; counter++) {
            if (e.hasNext()) {
                MovieDisplay movie1 = new MovieDisplay(movieArrayList.get(movieCounter).getTitle(), movieArrayList.get(movieCounter).getPosterLink(), 0, 1);
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

        MovieDisplay movieSelectionDisplay = new MovieDisplay(movieArrayList.get(movieCounter-1).getTitle(), movieArrayList.get(movieCounter-1).getPosterLink(), 0, 1);

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
                //currentUser.addCollection(collectionNameField.getText());
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
        JCheckBox ratedG = new JCheckBox("G");
        JCheckBox ratedPG = new JCheckBox("PG");
        JCheckBox ratedPG13 = new JCheckBox("PG-13");
        JCheckBox ratedR = new JCheckBox("R");
        JCheckBox ratedUR = new JCheckBox("Unrated");


        JLabel genreLabel = new JLabel("Genre:");            ///I think this is all of them lol
        JCheckBox genreAction = new JCheckBox("Action");
        JCheckBox genreAdventure = new JCheckBox("Adventure");
        JCheckBox genreMystery = new JCheckBox("Mystery");
        JCheckBox genreRomance = new JCheckBox("Romance");
        JCheckBox genreHorror = new JCheckBox("Horror");
        JCheckBox genreComedy = new JCheckBox("Comedy");
        JCheckBox genreDocumentary = new JCheckBox("Documentary");
        JCheckBox genreDrama = new JCheckBox("Drama");
        JCheckBox genreShort = new JCheckBox("Short");
        JCheckBox genreSciFi = new JCheckBox("SciFi");
        JCheckBox genreCrime = new JCheckBox("Crime");
        JCheckBox genreThriller = new JCheckBox("Thriller");
        JCheckBox genreFantasy = new JCheckBox("Fantasy");
        JCheckBox genreAnimation = new JCheckBox("Animation");
        JCheckBox genreFamily = new JCheckBox("Family");
        JCheckBox genreMusical = new JCheckBox("Musical");
        JCheckBox genreBiography = new JCheckBox("Biography");
        JCheckBox genreSport = new JCheckBox("Sport");
        JCheckBox genreHistory= new JCheckBox("History");

        JButton buttonClear = new JButton("Clear Filters");
        JButton buttonSearch = new JButton("Apply Filters");

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

        filterBox.add(buttonSearch);

        buttonFilter.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                filterPopupMenu.show(buttonFilter, buttonFilter.getHorizontalAlignment(),buttonFilter.getHeight());;
            }
        });

        buttonSearch.addActionListener( new ActionListener() { /////MAKE THIS DO SOMETHING*****************************
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                //filterPopupMenu.show(buttonFilter, buttonFilter.getHorizontalAlignment(),buttonFilter.getHeight());;
                filterPopupMenu.setVisible(false);
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


//sort: a-z, year, etc
