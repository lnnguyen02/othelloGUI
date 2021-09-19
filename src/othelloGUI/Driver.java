package othelloGUI;

import javax.swing.*;

public class Driver
{
   public static void main(String[] args) {
      JFrame frame = new JFrame("Othello");
      Othello screen = new Othello();
      frame.setContentPane(screen);
      frame.setSize(1000, 1000);
      frame.setLocation(0, 0);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}