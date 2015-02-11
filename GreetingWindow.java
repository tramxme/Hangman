/*
 * This class is the initial window that greets the users and gives them the option of playing or exiting the game.
 * It takes 2 params:  - an Array of words that we want the program to pick from
 *                     - a HashMap containing their description to use for hint
 */

import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class GreetingWindow{
	public GreetingWindow(String[] wordList, HashMap<String, String> dictionary) {

    JFrame frame1 = new JFrame();

    JPanel corePanel = new JPanel(new GridLayout(6,1)); //Represents a layout with 6 rows and 1 column, equally sized

    JLabel status = new JLabel("What do you feel like doing?", SwingConstants.CENTER);
    JButton startGameButton = new JButton("Do you want to play a game???");
    JButton exitGameButton = new JButton("Too tired to play anything!!!");

    startGameButton.setPreferredSize(new Dimension(200,50));
    exitGameButton.setPreferredSize(new Dimension(200,50));

    corePanel.add(status);
    corePanel.add(startGameButton);
    corePanel.add(exitGameButton);

    frame1.add(corePanel);

    frame1.setTitle("Hangman");
    frame1.setSize(400,400);
    frame1.setLocationRelativeTo(null); // make the window appear in the center of the screen
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the application close completely when the user clicks the X
    frame1.setVisible(true); //make the window visible

    JFrame frame2 = new JFrame();
    JLabel exitStatus = new JLabel("Come back when you feel like playing. BYE!!!!", SwingConstants.CENTER);
    frame2.setTitle("Hangman");
    frame2.setSize(400,400);
    frame2.setLocationRelativeTo(null); // make the window appear in the center of the screen
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the application close completely when the user clicks the X
    frame2.add(exitStatus);

    exitGameButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame1.dispose();
        frame2.setVisible(true);

        Timer timer;
        timer = new Timer(2000, new ActionListener(){
          public void actionPerformed(ActionEvent e){
            System.exit(0);
          }
        });

        timer.start();
      }
    });

    startGameButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        frame1.dispose();
        new MainWindow(wordList, dictionary);
      }
    });

  }
}
