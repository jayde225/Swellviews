import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import javax.swing.*;

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

        //Homepage Header Attributes (added from above)
        JPanel header = new JPanel();
        header.setLayout(new GridLayout(1,8));
        header.add(searchField);
        header.add(buttonSearch);
        header.add(spacer);
        header.add(buttonFilter);
        header.add(buttonCollections);
        header.add(labelAccount);
        header.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Home"));

        //Testing MovieDisplay with blank Rectangle named "Movie"
        MovieDisplay movieTest = new MovieDisplay("Movie");

        frame.setLayout(new BorderLayout());
        movieTest.setSize(30,40);

        JPanel movieGrid = new JPanel();
        movieGrid.setLayout(new GridLayout(1,1));

        frame.add(movieGrid,BorderLayout.CENTER); //Adds MovieDisplay test to center of page

        movieGrid.add(movieTest);

        frame.add(header, BorderLayout.PAGE_START);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
