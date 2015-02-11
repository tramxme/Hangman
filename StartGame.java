/*
 * This class contains the main() method to run the program.
 * It also contains a list of words with their descriptions and passes
 * them to the GreetingWindow class to start the game
 */

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class StartGame{

  public static void main(String[] args){
    String[] wordBank = {"team", "uncle","wrinkle", "meeting", "rhythm", "manufacturing", "umbrella"};

    HashMap<String, String> wordBankNew = new HashMap<String, String>();

    wordBankNew.put("team", "people working together");
    wordBankNew.put("uncle", "a family member");
    wordBankNew.put("wrinkle", "something caused by old age and smiling");
    wordBankNew.put("meeting", "when a bunch of people get in a room and talk");
    wordBankNew.put("rhythm", "a musical term");
    wordBankNew.put("manufacturing", "the making process");
    wordBankNew.put("umbrella", "something to protect from the rain");

    new GreetingWindow(wordBank, wordBankNew);
  }

}
