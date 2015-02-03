import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HangmanFigure extends JPanel{

  private int guesses;

  public HangmanFigure(){
    super(); // calls the constructor of its parents (JPanel) to retain the functionalities of our class
    guesses = 0;
    setPreferredSize(new Dimension(300, 300)); // Set the size of the panel
    setOpaque(true); // make everything in the background visible
  }

  public void paintComponent(Graphics g){
    g.setColor(Color.BLACK);

    //Base
    if(guesses > 0){
      g.drawLine(1, 299, 299, 299);
    }

    //Right wall
    if(guesses > 1){
      g.drawLine(299, 299, 299, 1);
    }

    //Top line
    if(guesses > 2){
      g.drawLine(150, 1, 299, 1);
    }

    //Hanging line
    if(guesses > 3){
      g.drawLine(150, 1, 150, 70);
    }

    //Face
    if(guesses > 4){
      g.drawOval(125, 70, 50, 50);
    }

    //Body
    if(guesses > 5){
      g.drawLine(150, 120, 150, 200);
    }

    //Left arm
    if(guesses > 6){
      g.drawLine(150, 150, 110, 140);
    }

    //Right arm
    if(guesses > 7){
      g.drawLine(150, 150, 190, 140);
    }

    //Left leg
    if(guesses > 8){
      g.drawLine(150, 200, 120, 250);
    }

    if(guesses > 9){

      //Right leg
      g.drawLine(150, 200, 180, 250);

      //draw X X eyes
      g.drawLine(140, 85, 145, 90);
      g.drawLine(155, 85, 160, 90);
      g.drawLine(140, 90, 145, 85);
      g.drawLine(155, 90, 160, 85);

      //Draw tongue
      g.drawLine(140, 105, 160, 105); //Mouth
      Rectangle tongue = new Rectangle(150, 105,10, 20);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(Color.BLACK);
      g2.fill(tongue);
      g2.draw(tongue);
    }
  }

  public void set(){
    guesses++;
    paintComponent(this.getGraphics());
  }
}
