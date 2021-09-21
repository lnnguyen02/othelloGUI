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
		setLayout(new BorderLayout());

		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(8, 8));
		add(grid, BorderLayout.CENTER);
		board = new int[8][8];
		buttons = new JButton[8][8];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(100, 100));
				grid.add(buttons[i][j]);
				buttons[i][j].addActionListener(new Clicked(i, j));
			}
		}

		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;

		buttons[3][3].setIcon(new ImageIcon("white2.png"));
		buttons[3][4].setIcon(new ImageIcon("black2.png"));
		buttons[4][3].setIcon(new ImageIcon("black2.png"));
		buttons[4][4].setIcon(new ImageIcon("white2.png"));
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x >= 0) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x - 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x <= 7) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x + 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (y <= 7) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				y = y + 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (y >= 0) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				y = y - 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x >= 0 && y <= 7) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x - 1;
				y = y + 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x >= 0 && y >= 0) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x - 1;
				y = y - 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x <= 7 && y <= 7) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x + 1;
				y = y + 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
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
		Stack<Integer> xTemp = new Stack<>();
		Stack<Integer> yTemp = new Stack<>();

		while (x <= 7 && y >= 0) {
			if (board[x][y] != turn) {
				xTemp.push(x);
				yTemp.push(y);
				x = x + 1;
				y = y - 1;
			} else {
				while (!xTemp.isEmpty()) {
					xIndexes.push(xTemp.pop());
					yIndexes.push(yTemp.pop());
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 */
	public void updateBoard(int r, int c) {
		if (board[r][c] == 0) {
			board[r][c] = turn;
			if (board[r][c] == 1) {
				buttons[r][c].setIcon(new ImageIcon("black2.png"));
			} else {
				buttons[r][c].setIcon(new ImageIcon("white2.png"));
			}
		} else if (board[r][c] == 1) {
			board[r][c] = 2;
			buttons[r][c].setIcon(new ImageIcon("white2.png"));
		} else {
			board[r][c] = 1;
			buttons[r][c].setIcon(new ImageIcon("black2.png"));
		}
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

				flipButtons();

				if (turn == 1) {
					turn = 2;
				} else {
					turn = 1;
				}
			}
		}

	}
}