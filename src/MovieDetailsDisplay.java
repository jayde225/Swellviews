import moviemodel.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * \ Creates a new window for a given Movie object within an ArrayList of Movie objects. Displays details from JSON file as well as the objects related MovieDisplay object.
 */
public class MovieDetailsDisplay extends JFrame {

    /**
     * MovieDetailsDisplay constructor, automatically creates new JFrame using MovieDisplay object given and movie details from Movie.java, and attaches new MouseListener to the MovieDisplay given that shows the frame on click.
     * @param movieSelected The MovieDisplay object used in the movieGrid (used to attach MouseListener)
     * @param movieArrayList ArrayList of Movie objects being viewed
     * @param movieCounter The static int "movieCounter", used to know where in the ArrayList the Movie is
     * @param darkMode The static int "darkMode" (or manual 1 if yes, 0 if no)
     */
    public MovieDetailsDisplay(MovieDisplay movieSelected, ArrayList<Movie> movieArrayList, int movieCounter, int darkMode){

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

        if (darkMode == 1) {
            movieTitle.setForeground(Color.white);
            movieGenre.setForeground(Color.white);
            movieYear.setForeground(Color.white);
            movieAgeRating.setForeground(Color.white);
            movieRuntime.setForeground(Color.white);
            movieDirector.setForeground(Color.white);
            movieWriter.setForeground(Color.white);
            movieActors.setForeground(Color.white);
            movieAwards.setForeground(Color.white);
            moviePlotLabel.setForeground(Color.white);
            movieDetailsFrame.getContentPane().setBackground(Color.darkGray);
            moviePlot.setBackground(Color.darkGray);
            moviePlot.setForeground(Color.white);
            movieDetailsRightPanel.setBackground(Color.darkGray);
            rateMovieButtons.setBackground(Color.darkGray);
        }

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
    }