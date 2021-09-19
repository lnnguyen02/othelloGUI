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
	private int turn = 1;
	private JButton[][] buttons;

	/**
	 * 
	 */
	public Othello() {

		setLayout(new GridLayout(8, 8));
		board = new int[8][8];
		buttons = new JButton[8][8];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(100, 100));
				add(buttons[i][j]);
				buttons[i][j].addActionListener(new Clicked(i, j));
			}
		}

		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;

		updateBoard();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkUp(int x, int y) {
		if (x - 1 < 0) {
			return false;
		} else if (board[x - 1][y] != turn) {
			return checkUp(x - 1, y);
		} else {
			return true;
		}
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkDown(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkLeft(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkRight(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkUpLeft(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkUpRight(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkDownLeft(int x, int y) {
		return true;
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean checkDownRight(int x, int y) {
		return true;
	}

	/**
	 * 
	 */
	public void updateBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 1) {
					buttons[i][j].setIcon(new ImageIcon("black2.png"));
				} else if (board[i][j] == 2) {
					buttons[i][j].setIcon(new ImageIcon("white2.png"));
				} else {

				}
			}
		}
	}

	/**
	 * 
	 * @author Leon
	 *
	 */
	private class Clicked implements ActionListener {
		int r, c;

		public Clicked(int a, int b) {
			r = a;
			c = b;
		}

		public void actionPerformed(ActionEvent e) {
			if (board[r][c] == 0) {
				System.out.println(checkUp(r, c));
				System.out.println(checkDown(r, c));
				System.out.println(checkRight(r, c));
				System.out.println(checkLeft(r, c));

				System.out.println(checkUpRight(r, c));
				System.out.println(checkUpLeft(r, c));
				System.out.println(checkUpRight(r, c));
				System.out.println(checkUpLeft(r, c));
			}
		}

	}
}
