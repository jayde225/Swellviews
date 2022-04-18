import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MovieDisplay extends JPanel{

    private String movieName;
    private String moviePoster;
    private int showMovieTitle;
    private int titleDarkMode;

    public MovieDisplay(String name, String poster, int darkMode, int showTitle) {
        this.movieName = name; this.moviePoster = poster; this.titleDarkMode = darkMode; this.showMovieTitle = showTitle;
    }


    //Static Size Version:
    @Override
    public void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D) g; //cast it

        if (Objects.equals(moviePoster, "N/A")){
            g2d.drawRect(0, 0, 300, 440);
            g2d.setColor(Color.gray);
            g2d.fillRect(0, 0, 300, 440);

            g2d.setColor(Color.darkGray);
            g2d.setFont(new Font("Missing Image Questionmark", Font.BOLD, 350));
            g2d.drawString("?",40,330);
        }
        else {
            URL url = null;
            try {
                url = new URL(moviePoster);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedImage movie_image = null;
            try {
                movie_image = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            g2d.drawImage(movie_image.getScaledInstance(300,440,Image.SCALE_DEFAULT), 0, 0, null);
        }

        if(showMovieTitle == 1) {

            if (titleDarkMode == 1){g2d.setColor(Color.white);}
            else {g2d.setColor(Color.black);}
            g2d.setFont(new Font("moviemodel.Movie Title", Font.PLAIN, 30));

            if (movieName.length() > 25){
                String movieNameCondensed = movieName.substring(0,20) + "...";
                g2d.drawString(movieNameCondensed, 0, 470);
            }
            else {
                g2d.drawString(this.movieName, 0, 470);
            }
        }

    }
}