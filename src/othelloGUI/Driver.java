package othelloGUI;

import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

public class Driver
{
   public static void main(String[] args) {
      JFrame frame = new JFrame("Othello");
      Othello screen = new Othello();
      frame.setContentPane(screen);
      frame.setSize(1200, 1000);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
   }
}