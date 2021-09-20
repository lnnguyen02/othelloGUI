package othelloGUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Stack;

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
@SuppressWarnings("serial")
public class Othello extends JPanel {

	private int[][] board;
	private int turn = 1;
	private JButton[][] buttons;

	private Stack<Integer> xIndexes = new Stack<Integer>();
	private Stack<Integer> yIndexes = new Stack<Integer>();

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

		updateButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkUp(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkDown(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkRight(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkLeft(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkUpRight(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkUpLeft(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkDownRight(x, y);
		} else {
			flipButtons();
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
			while (!xIndexes.empty()) {
				xIndexes.pop();
				yIndexes.pop();
			}
			return false;
		} else if (board[x][y] != turn) {
			xIndexes.push(x);
			yIndexes.push(y);
			return checkDownLeft(x, y);
		} else {
			flipButtons();
			return true;
		}
	}

	/**
	 * 
	 */
	public void updateButtons() {
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
	 */
	public void updateBoard(int r, int c) {
		if (board[r][c] == 0) {
			board[r][c] = turn;
		} else if (board[r][c] == 1) {
			board[r][c] = 2;
		} else {
			board[r][c] = 1;
		}

		updateButtons();
	}

	/**
	 * 
	 */
	public void flipButtons() {
		while (!xIndexes.empty()) {
			updateBoard(xIndexes.pop(), yIndexes.pop());
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
				updateBoard(row, col);

				checkUp(row, col);
				checkDown(row, col);
				checkRight(row, col);
				checkLeft(row, col);

				checkUpRight(row, col);
				checkUpLeft(row, col);
				checkDownRight(row, col);
				checkDownLeft(row, col);

				if (turn == 1) {
					turn = 2;
				} else {
					turn = 1;
				}
			}
		}

	}
}

//todo need to update the turns, flip the clicked spots, and check the spot to see if it is valid
//also edit the layout if u can
