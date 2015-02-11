/*
 * This class is the main window that contains the game.
 * It gives the users the option of Playing the game, Getting a Hint, Exiting the game or Replaying
 * It takes 2 params:  - an Array of words that we want the program to pick from
 *                     - a HashMap containing their description to use for hint
 */

import javax.swing.Timer;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MainWindow extends JFrame {

  private int remainingGuesses; // Represents the number of guesses remaining
  private String wrongGuesses; // Represents all the wrong guesses been made so far
  private String word; // Represents the word the user is guessing
  private String visible; // Represents the string shown to the user
  private String description;
  private boolean move = true;
  Timer timer;

  public MainWindow(String[] wordList, HashMap<String,String> dictionary){

    Random ranNum = new Random();
    int n = ranNum.nextInt(wordList.length);

    remainingGuesses = 10;
    wrongGuesses = "";
    word = wordList[n];
    description = dictionary.get(word);

    visible = "";

    for(int i = 0; i < word.length(); ++i){
      visible += "_ ";
    }


    JPanel corePanel = new JPanel(); //corePanel is a new JPanel added to the center part of the JFrame
    corePanel.setLayout(new BorderLayout());

    JLabel hintStatus = new JLabel("", SwingConstants.CENTER);
    JLabel status = new JLabel("You have " + remainingGuesses + " guess(es) remaining", SwingConstants.CENTER);
    JLabel wrong = new JLabel("Wrong guesses so far: " + wrongGuesses);
    JLabel visibleLabel = new JLabel(visible, SwingConstants.CENTER);
    JTextField input = new JTextField(); // Create a text field to get user's input

    JButton hintButton = new JButton("hint");
    JButton exitButton = new JButton("Exit");
    JButton replayButton = new JButton("Replay");


    JPanel southPanel = new JPanel(new GridLayout(8,1)); // Represents a layout with 7 rows and 1 column, equally sized
    southPanel.add(status);
    southPanel.add(visibleLabel);
    southPanel.add(input);
    southPanel.add(wrong);
    southPanel.add(hintButton);
    southPanel.add(hintStatus);
    southPanel.add(exitButton);
    southPanel.add(replayButton);

    corePanel.add(southPanel, BorderLayout.SOUTH);

    HangmanFigure hf = new HangmanFigure();
    corePanel.add(hf, BorderLayout.CENTER);

    hintButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        hintStatus.setText(description);
      }
    });

    exitButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        System.exit(0);
      }
    });

    replayButton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        new MainWindow(wordList, dictionary);
      }
    });

    input.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String text = input.getText();
        if(text.length() == 1 && text.matches("[a-z]")){

          boolean guessFound = false;
          boolean alreadyGuessed = false;

          //Check if the letter already in the wrong guess list
          for(int k = 0; k < wrongGuesses.length(); k++) {
            if(text.charAt(0) == wrongGuesses.charAt(k)){
              alreadyGuessed = true;
            }
          }

          if(alreadyGuessed) {
            status.setText("You already guessed that letter.");
          }

          for(int i = 0; i < word.length(); ++i){
            if(text.charAt(0) == word.charAt(i)){
              guessFound = true;

              String newVisible = "";
              for(int j = 0; j < visible.length(); ++j){
                if(j == (i*2)){
                  newVisible += word.charAt(i);
                }else{
                  newVisible += visible.charAt(j);
                }
              }
              visible = newVisible;
              visibleLabel.setText(visible);
            }
          }

          //If it's the wrong guess
          if(!guessFound && !alreadyGuessed){
            remainingGuesses--;
            wrongGuesses += text + " ";
            wrong.setText("Wrong guesses so far: " + wrongGuesses);
            if(remainingGuesses > 0){
              status.setText("You have " + remainingGuesses + " guesses remaining");
            }else{
              status.setText("You lost: the word was " + word);
              input.setEnabled(false); //Disable the text box
            }
            hf.set(); //a method in HangmanFigure to draw Hangman
          }else{
            String actualVisible = "";
            for(int i = 0; i < visible.length(); i+=2){
              actualVisible += visible.charAt(i);
            }

            if(actualVisible.equals(word)){
              timer = new Timer(40, new ActionListener(){
                public void actionPerformed(ActionEvent e){
                  hf.drawWinningFigure(move);
                  move = !move;
                }
              });
              timer.start();
              status.setText("Congratulations, you have won!");
              input.setEnabled(false);
            }
          }

        }else{
          System.out.println("Invalid input! Please enter a lowercase letter");
        }
        input.setText("");
      }
    });

    this.add(corePanel, BorderLayout.CENTER);

    this.setTitle("Hangman");
    this.pack(); // make its size big enough to fit all the components
    this.setLocationRelativeTo(null); // make the window appear in the center of the screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the application close completely when the user clicks the X
    this.setVisible(true); //make the window visible
  }
}
