package othelloGUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/*
 *  8 by 8 game
 *  
 *  Black goes first
 *  
 *  1 = black, 2 = white
 *  
 *  Game set up:
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  ~ ~ ~ 2 1 ~ ~ ~
 *  ~ ~ ~ 1 2 ~ ~ ~
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  ~ ~ ~ ~ ~ ~ ~ ~
 *  
 *  @author Leon Nguyen
 */
public class Othello extends JPanel {
	
	private int[][] board;

	public Othello() {
		board = new int[8][8];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
			}
		}

		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;

		setLayout(new GridLayout(8, 8));
	}
}
