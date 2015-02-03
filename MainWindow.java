import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

  private int remainingGuesses; // Represents the number of guesses remaining
  private String wrongGuesses; // Represents all the wrong guesses been made so far
  private String word; // Represents the word the user is guessing
  private String visible; // Represents the string shown to the user

  public MainWindow(String toGuess){
    remainingGuesses = 10;
    wrongGuesses = "";
    word = toGuess;

    visible = "";

    for(int i = 0; i < word.length(); ++i){
      visible += "_ ";
    }

    JPanel corePanel = new JPanel(); //corePanel is a new JPanel added to the center part of the JFrame
    corePanel.setLayout(new BorderLayout());

    JLabel status = new JLabel("You have " + remainingGuesses + " guess(es) remaining", SwingConstants.CENTER);
    JLabel wrong = new JLabel("Wrong guesses so far: " + wrongGuesses);
    JLabel visibleLabel = new JLabel(visible, SwingConstants.CENTER);
    JTextField input = new JTextField(); // Create a text field to get user's input

    JPanel southPanel = new JPanel(new GridLayout(4,1)); // Represents a layout with 4 rows and 1 column, equally sized
    southPanel.add(status);
    southPanel.add(visibleLabel);
    southPanel.add(input);
    southPanel.add(wrong);

    corePanel.add(southPanel, BorderLayout.SOUTH);

    HangmanFigure hf = new HangmanFigure();
    corePanel.add(hf, BorderLayout.CENTER);

    input.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){
        String text = input.getText();
        if(text.length() == 1 && text.matches("[a-z]")){
          remainingGuesses--;
          boolean guessFound = false;

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

          if(!guessFound){
            // if the decremented value is greater than 0, show the user the number of guesses remaining
            if(remainingGuesses >= 0){
              status.setText("You have " + remainingGuesses + " guesses remaining");
              wrongGuesses += text + " ";
              wrong.setText("Wrong guesses so far: " + wrongGuesses);
              hf.set(); //a method in HangmanFigure to draw Hangman
            }else{
              status.setText("You lost: the word was " + word);
              input.setEnabled(false); //Disable the text box
            }
          }else{
            String actualVisible = "";
            for(int i = 0; i < visible.length(); i+=2){
              actualVisible += visible.charAt(i);
            }

            if(actualVisible.equals(word)){
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

  public static void main(String[] args){
    new MainWindow("cat");
  }


}
