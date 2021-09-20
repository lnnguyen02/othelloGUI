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
	public boolean checkUp(int r, int c) {
		int x = r - 1;
		int y = c;

		if (x < 0) {
			return false;
		} else if (board[x][y] != turn) {
			return checkUp(x, y);
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
	public boolean checkDown(int r, int c) {
		int x = r + 1;
		int y = c;

		if (x > 7) {
			return false;
		} else if (board[x][y] != turn) {
			return checkDown(x, y);
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
	public boolean checkRight(int r, int c) {
		int x = r;
		int y = c + 1;

		if (y > 7) {
			return false;
		} else if (board[x][y] != turn) {
			return checkRight(x, y);
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
	public boolean checkLeft(int r, int c) {
		int x = r;
		int y = c - 1;

		if (y < 0) {
			return false;
		} else if (board[x][y] != turn) {
			return checkLeft(x, y);
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
	public boolean checkUpRight(int r, int c) {
		int x = r - 1;
		int y = c + 1;

		if (x < 0 || y > 7) {
			return false;
		} else if (board[x][y] != turn) {
			return checkUpRight(x, y);
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
	public boolean checkUpLeft(int r, int c) {
		int x = r - 1;
		int y = c - 1;

		if (x < 0 || y < 0) {
			return false;
		} else if (board[x][y] != turn) {
			return checkUpLeft(x, y);
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
	public boolean checkDownRight(int r, int c) {
		int x = r + 1;
		int y = c + 1;

		if (x > 7 || y > 7) {
			return false;
		} else if (board[x][y] != turn) {
			return checkDownRight(x, y);
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
	public boolean checkDownLeft(int r, int c) {
		int x = r + 1;
		int y = c - 1;

		if (x > 7 || y < 0) {
			return false;
		} else if (board[x][y] != turn) {
			return checkDownLeft(x, y);
		} else {
			return true;
		}
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
		int row, col;

		public Clicked(int a, int b) {
			row = a;
			col = b;
		}

		public void actionPerformed(ActionEvent e) {
			if (board[row][col] == 0) {
				checkUp(row, col);
				checkDown(row, col);
				checkRight(row, col);
				checkLeft(row, col);

				checkUpRight(row, col);
				checkUpLeft(row, col);
				checkUpRight(row, col);
				checkUpLeft(row, col);
			}
		}

	}
}
