import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.UIManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanFigure extends JPanel{

  private int guesses;
  private int arm_Y;
  private boolean move = true;

  public HangmanFigure(){
    super(); // calls the constructor of its parents (JPanel) to retain the functionalities of our class
    guesses = 0;
    setPreferredSize(new Dimension(300, 300)); // Set the size of the panel
    setOpaque(true); // make everything in the background visible
  }

  public void paintComponent(Graphics g){
    Color color = UIManager.getColor ( "Panel.background" );
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
    if(guesses > 6 && guesses <= 9 ){
      g.drawLine(150, 150, 110, 140);
    }

    //Right arm
    if(guesses > 7 && guesses <= 9 ){
      g.drawLine(150, 150, 190, 140);
    }

    //Left leg
    if(guesses > 8 && guesses <= 9 ){
      g.drawLine(150, 200, 120, 250);
    }

    if(guesses > 9){

      //Erase the old draw for the arms and leg
      g.setColor(color);
      g.drawLine(150, 150, 110, 140); //left arm
      g.drawLine(150, 150, 190, 140); //right arm
      g.drawLine(150, 200, 120, 250); //left leg

      g.setColor(Color.BLACK);
      g.drawLine(150, 150, 110, 180); //Left arm
      g.drawLine(150, 150, 190, 180); //Right arm
      g.drawLine(150, 200, 120, 280); //Left leg
      g.drawLine(150, 200, 180, 280); //Right leg

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

  public void paintNewFigure(Graphics g, boolean move){

    Color color = UIManager.getColor ( "Panel.background" );
    //Erase all the old draw
    g.setColor(color);
    g.drawLine(150, 150, 110, 140); //left arm
    g.drawLine(150, 150, 190, 140); //right arm
    g.drawLine(150, 200, 120, 250); //left leg
    g.drawOval(125, 70, 50, 50); //face
    g.drawLine(150, 120, 150, 200); //body
    g.drawLine(180, 170, 135, arm_Y);//left arm
    g.drawLine(180, 170, 225, arm_Y);//right arm

    if(move){
      arm_Y = 150;
    }else{
      arm_Y = 200;
    }
    //Draw new figure
    g.setColor(Color.BLACK);
    g.drawLine(180, 150, 180, 230);//Body
    g.drawLine(150, 299, 180, 230);//Left leg
    g.drawLine(180, 230, 210, 299);//Right leg
    g.drawOval(155, 100, 50, 50); //face
    g.drawLine(180, 170, 135, arm_Y);//left arm
    g.drawLine(180, 170, 225, arm_Y);//right arm
    g.fillOval(165, 115, 10, 10); //eyes
    g.fillOval(185, 115, 10, 10); //eyes
    g.drawArc(170, 130, 20, 10, 180,180); //smile
  }

  public void set(){
    guesses++;
    paintComponent(this.getGraphics());
  }

  public void drawWinningFigure(boolean move){
    paintNewFigure(this.getGraphics(), move);
  }
}
