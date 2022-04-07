import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class MovieDisplay extends JPanel {

    private String movieName;

    public MovieDisplay(String name) {
        this.movieName = name;
    }

    //Static Size Version:
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g; //cast it
        g2d.drawRect(0, 0, 240, 320);
        g2d.setColor(Color.gray);
        g2d.fillRect(0, 0, 240, 320);

        g2d.setColor(Color.darkGray);
        g2d.setFont(new Font("Movie Title", Font.PLAIN, 30));
        g2d.drawString(this.movieName, 70, 350);
        g2d.setFont(new Font("Missing Image Questionmark", Font.BOLD, 250));
        g2d.drawString("?",43,240);
    }

    //Dynamic Size Version (BROKEN):
    /**
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; //cast it
        g2d.drawRect(0, 0, (getWidth())-50, (getHeight())-50);
        g2d.setColor(Color.gray);
        g2d.fillRect(0, 0, (getWidth())-50, (getHeight())-50);

        g2d.setColor(Color.darkGray);
        g2d.setFont(new Font("Movie Title", Font.PLAIN, 50));
        g2d.drawString(this.movieName, 35, 370);
        g2d.setFont(new Font("Missing Image Questionmark", Font.BOLD, 250));
        g2d.drawString("?",43,240);
    }
*/
}