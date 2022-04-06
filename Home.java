import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

public class Home {
    public static void main(String[] args) {

        boolean loggedIn = false;
        //Homepage Attribute Declarations
        JFrame homeFrame = new JFrame("Swellviews");
        JTextField searchField = new JTextField("Enter Movie Name"); //figureout how to erase text on click in field
            //so can search without having to delete default text, or just make label (see accountmenu/login)
        JButton buttonSearch = new JButton("Search");
        JSeparator spacer = new JSeparator();
        JButton buttonFilter = new JButton("Filter");//JCheckBox allows multiselect, JRadioButton allows single
        JButton buttonCollections = new JButton("Collections");
        JButton buttonAccount = new JButton("Account");

        //Homepage Header Attributes (added from above)
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1, 8));
        header.add(searchField);
        header.add(buttonSearch);
        header.add(spacer);
        header.add(buttonFilter);//***************************************
        header.add(buttonCollections);
        header.add(buttonAccount);

                accountMenu(buttonAccount, loggedIn);

        //Testing MovieDisplay with blank Rectangles named "Movie_"
        MovieDisplay movie1 = new MovieDisplay("Movie1");
        MovieDisplay movie2 = new MovieDisplay("Movie2");
        MovieDisplay movie3 = new MovieDisplay("Movie3");
        MovieDisplay movie4 = new MovieDisplay("Movie4");
        MovieDisplay movie5 = new MovieDisplay("Movie5");
        MovieDisplay movie6 = new MovieDisplay("Movie6");
        MovieDisplay movie7 = new MovieDisplay("Movie7");
        MovieDisplay movie8 = new MovieDisplay("Movie8");

        homeFrame.setLayout(new BorderLayout());

        //Testing grid layout
        movie1.setSize(90, 140);
        movie2.setSize(90, 140);
        movie3.setSize(90, 140);
        movie4.setSize(90, 140);
        movie5.setSize(90, 140);
        movie6.setSize(90, 140);
        movie7.setSize(90, 140);
        movie8.setSize(90, 140);

        String displayName = "Suggestions"; //Contains homepage grid name for display. Will be changed to show where movies are coming from (Collections, Search Results, etc.)

        JPanel movieGrid = new JPanel();
        movieGrid.setLayout(new GridLayout(2, 4));
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
        homeFrame.add(movieGrid, BorderLayout.CENTER); //Adds MovieDisplay test to center of page
        homeFrame.add(header, BorderLayout.PAGE_START);
        homeFrame.setSize(500, 500);
        homeFrame.setLocationRelativeTo(null);

        //Standard enabling and closing statements
        homeFrame.setVisible(true);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void accountMenu(JButton accountB, boolean loggedIn){ //make popups bigger

        final JPopupMenu accountmenu = new JPopupMenu();
        final JMenu loginmenu = new JMenu("Log In");
        //final JPopupMenu logoutmenu = new JPopupMenu();

        JButton enterB = new JButton("Enter");
        JMenuItem logoutB = new JMenuItem("Log out");

        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");

        JTextField userfield = new JTextField();
        JTextField passfield = new JTextField();

        loginmenu.add(userLabel); //setMenuLoction(int x, int y) for login window
        loginmenu.add(userfield);
        loginmenu.add(passLabel);
        loginmenu.add(passfield);
        loginmenu.add(enterB);

        if(loggedIn == true) {
            accountmenu.add(logoutB);
        }
        else{
            accountmenu.add(loginmenu);
        } //SEE loginB.addActionListener comment!


        accountB.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                accountmenu.show(accountB, accountB.getWidth(), accountB.getHeight());
            }
        } );

        enterB.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
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
        } );*/
    }
}