package othelloGUI;

import javax.swing.*;

public class Driver
{
   public static void main(String[] args) {
      JFrame frame = new JFrame("Othello");
      Othello screen = new Othello();
      frame.setContentPane(screen);
      frame.setSize(900, 900);
      frame.setLocation(700, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}

//todo need to update the turns, flip the clicked spots, and check the spot to see if it is valid
//add a counter and text
//check to see if the move is valid