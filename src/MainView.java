package MainMVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {

    JTextField searchField = new JTextField("Enter Movie Name"); //figureout how to erase text on click in field
    //so can search without having to delete default text, or just make label (see accountmenu/login)
    JButton buttonSearch = new JButton("Search");
    JSeparator spacer = new JSeparator(); //Temporary Solution (maybe?) for separating header buttons
    JButton buttonFilter = new JButton("Filter");//JCheckBox allows multiselect, JRadioButton allows single
    JButton buttonCollections = new JButton("Collections");
    JButton buttonAccount = new JButton("Account");

    public MainView(String title) {
        //Homepage Header Attributes (added from above)

        super.setTitle(title);

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

        //TODO: Double check all of this code to determine which pieces should stay in the view and which pieces belong in the controller.

        JButton getMoreMovies = new JButton("Show More");
        getMoreMovies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent button_pressed) {
                /*
                if (movieListEnd == 0) {

                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieGridUpdater(homeFrame, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);
                }
                */
            }
        });

        JButton goBack = new JButton ("Go Back");
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                if (movieCounter > 15) {
                    movieGrid.removeAll();
                    SwingUtilities.updateComponentTreeUI(homeFrame);
                    movieCounter = movieCounter - 16 + movieEnd;
                    movieGridUpdater(homeFrame, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);
                    movieEnd = 0;
                    movieListEnd = 0;
                }

                 */
            }
        });

        forwardAndBackButtons.add(goBack);
        forwardAndBackButtons.add(getMoreMovies);

        //collectionMenu(buttonCollections); // Calls the collectionMenu function and attaches it to the "Collections" button (buttonCollections)
        //filterMenu(buttonFilter);

        this.setLayout(new BorderLayout()); // Sets the homepage frame to a border layout (5 sections: north, south, east, west, and center)

        //movieGridUpdater(this, forwardAndBackButtons, CompleteMovieArrayList, movieGrid);

        //Positioning homepage frame elements
        this.add(header, BorderLayout.PAGE_START);
        this.add(forwardAndBackButtons, BorderLayout.PAGE_END);
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
