import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.HashMap;
import javax.swing.Timer;
import javax.swing.JButton;

public class MainWindow extends JFrame {

  private int remainingGuesses; // Represents the number of guesses remaining
  private String wrongGuesses; // Represents all the wrong guesses been made so far
  private String word; // Represents the word the user is guessing
  private String visible; // Represents the string shown to the user
  private boolean move = true;
  Timer timer;

  public MainWindow(String toGuess, String description){
    remainingGuesses = 10;
    wrongGuesses = "";
    word = toGuess;

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
       
    JButton button = new JButton("hint");
    
    
    JPanel southPanel = new JPanel(new GridLayout(6,1)); // Represents a layout with 4 rows and 1 column, equally sized
    southPanel.add(status);
    southPanel.add(visibleLabel);
    southPanel.add(input);
    southPanel.add(wrong);
    southPanel.add(button);
    southPanel.add(hintStatus);
    
    corePanel.add(southPanel, BorderLayout.SOUTH);

    HangmanFigure hf = new HangmanFigure();
    corePanel.add(hf, BorderLayout.CENTER);

    button.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
            hintStatus.setText(description);
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

  public static void main(String[] args){
    String[] wordBank = {"team", "uncle","wrinkle", "meeting", "rhythm"};
    Random ranNum = new Random();
    int n = ranNum.nextInt(wordBank.length);
    HashMap<String, String> wordBankNew = new HashMap<String, String>();
    wordBankNew.put("team", "people working together");
    wordBankNew.put("uncle", "a family member");
    wordBankNew.put("wrinkle", "something caused by old age and smiling");
    wordBankNew.put("meeting", "when a bunch of people get in a room and talk");
    wordBankNew.put("rhythm", "a musical term");

    new MainWindow(wordBank[n], wordBankNew.get(wordBank[n]));
  }


}
