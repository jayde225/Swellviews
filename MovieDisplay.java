import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class MovieDisplay extends JPanel {

    String movieName;

    public MovieDisplay(String name) {
        this.movieName = name;
    }

    //@Override
    public void paintMovie(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; //cast it
        g2d.drawRect(0, 0, 30, 40);
        //g2d.clearRect(0,0,getWidth(),getHeight());
        g2d.setColor(Color.red);
        g2d.fillRect(0, 0, 30, 40);

        g2d.setColor(Color.black);
        g2d.setFont(new Font("Comic Sans", Font.PLAIN, 20));
        g2d.drawString(this.movieName, 0, 0);
    }

}