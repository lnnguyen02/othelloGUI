package othelloGUI;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
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
	private int turn;
	private JButton[][] buttons;
	private Stack<Point> points = new Stack<>();
	private ArrayList<Integer> zeros = new ArrayList<>();
	private JLabel counter;
	private int whiteCounter;
	private int blackCounter;
	private JPanel bottom;
	// private

	/**
	 * No arg constructor. 
	 */
	public Othello() {
		setLayout(new BorderLayout());

		JPanel grid = new JPanel();
		grid.setLayout(new GridLayout(8, 8));
		add(grid, BorderLayout.CENTER);
		board = new int[8][8];
		buttons = new JButton[8][8];
		int index = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = 0;
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(100, 100));
				grid.add(buttons[i][j]);
				buttons[i][j].addActionListener(new Clicked(i, j));
				zeros.add(index);
				index++;
			}
		}
		
		//Board Set up
		turn = 1;
		board[3][3] = 2;
		board[3][4] = 1;
		board[4][3] = 1;
		board[4][4] = 2;
		//

		// row*8 + column
		zeros.remove(zeros.indexOf(3 * 8 + 3));
		zeros.remove(zeros.indexOf(3 * 8 + 4));
		zeros.remove(zeros.indexOf(4 * 8 + 3));
		zeros.remove(zeros.indexOf(4 * 8 + 4));

		buttons[3][3].setIcon(new ImageIcon("white2.png"));
		buttons[3][4].setIcon(new ImageIcon("black2.png"));
		buttons[4][3].setIcon(new ImageIcon("black2.png"));
		buttons[4][4].setIcon(new ImageIcon("white2.png"));

		blackCounter = 2;
		whiteCounter = 2;

		counter = new JLabel("Black: " + blackCounter + "            White: " + whiteCounter);

		bottom = new JPanel();
		bottom.add(counter);
		add(bottom, BorderLayout.PAGE_END);
	}

	public boolean checkUp(int r, int c, boolean temp) {
		int x = r - 1;
		int y = c;
		Stack<Point> tempPoints = new Stack<>();

		while (x >= 0) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp) {
					tempPoints.push(new Point(x, y));
				}
				x = x - 1;
			} else {
				if (temp) {
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean checkUp(int r, int c) {
		return checkUp(r, c, true);
	}

	public boolean checkDown(int r, int c, boolean temp) {
		int x = r + 1;
		int y = c;
		Stack<Point> tempPoints = new Stack<>();

		while (x <= 7) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				x = x + 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkDown(int r, int c) {
		return checkDown(r, c, true);
	}

	public boolean checkRight(int r, int c, boolean temp) {
		int x = r;
		int y = c + 1;
		Stack<Point> tempPoints = new Stack<>();

		while (y <= 7) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				y = y + 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkRight(int r, int c) {
		return checkRight(r, c, true);
	}

	public boolean checkLeft(int r, int c, boolean temp) {
		int x = r;
		int y = c - 1;
		Stack<Point> tempPoints = new Stack<>();

		while (y >= 0) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				y = y - 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkLeft(int r, int c) {
		return checkLeft(r, c, true);
	}

	public boolean checkUpRight(int r, int c, boolean temp) {
		int x = r - 1;
		int y = c + 1;
		Stack<Point> tempPoints = new Stack<>();

		while (x >= 0 && y <= 7) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				x = x - 1;
				y = y + 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkUpRight(int r, int c) {
		return checkUpRight(r, c, true);
	}

	public boolean checkUpLeft(int r, int c, boolean temp) {
		int x = r - 1;
		int y = c - 1;
		Stack<Point> tempPoints = new Stack<>();

		while (x >= 0 && y >= 0) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				x = x - 1;
				y = y - 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkUpLeft(int r, int c) {
		return checkUpLeft(r, c, true);
	}

	public boolean checkDownRight(int r, int c, boolean temp) {
		int x = r + 1;
		int y = c + 1;
		Stack<Point> tempPoints = new Stack<>();

		while (x <= 7 && y <= 7) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				x = x + 1;
				y = y + 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkDownRight(int r, int c) {
		return checkDownRight(r, c, true);
	}

	public boolean checkDownLeft(int r, int c, boolean temp) {
		int x = r + 1;
		int y = c - 1;
		Stack<Point> tempPoints = new Stack<>();

		while (x <= 7 && y >= 0) {
			if (board[x][y] == 0) {
				return false;
			} else if (board[x][y] != turn) {
				if (temp)
					tempPoints.push(new Point(x, y));
				x = x + 1;
				y = y - 1;
			} else {
				if (temp)
					while (!tempPoints.isEmpty()) {
						points.push(tempPoints.pop());
					}
				return true;
			}
		}
		return false;
	}

	public boolean checkDownLeft(int r, int c) {
		return checkDownLeft(r, c, true);
	}

	public boolean checkForValidTurns() {
		for (int i = 0; i < zeros.size(); i++) {
			int x = i / 8;
			int y = i % 8;
			if (checkUp(x, y, false) || checkDown(x, y, false) || checkLeft(x, y, false) || checkRight(x, y, false)
					|| checkUpLeft(x, y, false) || checkUpRight(x, y, false) || checkDownLeft(x, y, false)
					|| checkDownRight(x, y, false)) {
				return true;
			}
		}
		return false;
	}

	public void updateBoard(int r, int c) {
		if (board[r][c] == 0) {
			board[r][c] = turn;
			zeros.remove(zeros.indexOf(r * 8 + c));
			if (board[r][c] == 1) {
				blackCounter++;
				buttons[r][c].setIcon(new ImageIcon("black2.png"));
			} else {
				whiteCounter++;
				buttons[r][c].setIcon(new ImageIcon("white2.png"));
			}
		} else if (board[r][c] == 1) {
			board[r][c] = 2;
			whiteCounter++;
			blackCounter--;
			buttons[r][c].setIcon(new ImageIcon("white2.png"));
		} else {
			whiteCounter--;
			blackCounter++;
			board[r][c] = 1;
			buttons[r][c].setIcon(new ImageIcon("black2.png"));
		}
	}

	public void flipButtons() {
		while (!points.empty()) {
			Point temp = points.pop();
			updateBoard((int) temp.getX(), (int) temp.getY());
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
				checkDownRight(row, col);
				checkDownLeft(row, col);

				if (!points.isEmpty()) {
					updateBoard(row, col);
					flipButtons();
					if (turn == 1) {
						turn = 2;
					} else {
						turn = 1;
					}
				}
			}

			bottom.remove(counter);
			counter = new JLabel("Black: " + blackCounter + "            White: " + whiteCounter);
			bottom.add(counter);

		}

	}
}