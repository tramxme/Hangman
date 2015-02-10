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

public class GreetingWindow extends JFrame {
	public GreetingWindow(boolean greet) {
		JPanel corePanel = new JPanel(new GridLayout(6,1)); //corePanel is a new JPanel added to the center part of the JFrame
		corePanel.setLayout(new BorderLayout());

		JLabel status = new JLabel("What do you feel like doing?", SwingConstants.CENTER);

		JButton startGameButton = new JButton("Start");
		JButton exitGameButton = new JButton("Exit");
		corePanel.add(status);
		corePanel.add(startGameButton);
		corePanel.add(exitGameButton);
		this.add(corePanel, BorderLayout.CENTER);

		this.setTitle("Hangman");
		this.pack(); // make its size big enough to fit all the components
		this.setLocationRelativeTo(null); // make the window appear in the center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make the application close completely when the user clicks the X
		this.setVisible(true); //make the window visible
	}
}
