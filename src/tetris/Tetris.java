package tetris;

public class Tetris {
	public static final int BOARD_X_SIZE = 12;
	public static final int BOARD_Y_SIZE = 22;

	public static char[][] initBoard() {
		char[][] result = new char[BOARD_X_SIZE][BOARD_Y_SIZE];
		for (int i = 0; i < BOARD_Y_SIZE; i++) {
			result[0][i] = 'X';
			result[BOARD_X_SIZE-1][i] = 'X';
		}
		for (int i = 0; i < BOARD_X_SIZE; i++) {
			result[i][0] = 'X';
			result[i][BOARD_Y_SIZE-1] = 'X';
		}
		for (int i = 1; i < BOARD_X_SIZE-1; i++) {
			for (int j = 1; j < BOARD_Y_SIZE-1; j++) {
				result[i][j] = ' ';
			}
		}
		return result;
	}

	public static char[][] addToBoard(char[][] board, Piece piece) {
		char[][] result = new char[BOARD_X_SIZE][BOARD_Y_SIZE];
		for (int i = 0; i < BOARD_X_SIZE; i++) {
			System.arraycopy(board[i], 0, result[i], 0, BOARD_Y_SIZE);
		}

		for (int i = 0; i < piece.getShape().length; i++) {
			for (int j = 0; j < piece.getShape()[i].length; j++) {
				if (piece.getShape()[i][j] != ' ' && result[i+piece.getX()][j+piece.getY()] == ' ')
					result[i+piece.getX()][j+piece.getY()] = piece.getShape()[i][j];
			}
		}
		return result;
	}

	public static boolean tick(char[][] board, Piece piece) {
		for (int i = 0; i < piece.getShape().length; i++) {
			for (int j = 0; j < piece.getShape()[i].length; j++) {
				if (piece.getShape()[i][j] != ' ' && board[i+piece.getX()][j+piece.getY()+1] != ' ') {
					return true;
				}
			}
		}
		piece.moveDown();
		return false;
	}

	public static int clearRows(char[][] board) {
		int rows = 0;
		int[] delete_rows = new int[BOARD_Y_SIZE-2];
		for (int i = 1; i < BOARD_Y_SIZE-1; i++) {
			int temp = 0;
			for (int j = 1; j < BOARD_X_SIZE-1; j++) {
				if (board[j][i] == ' ') break;
				else if (j == BOARD_X_SIZE-2) delete_rows[temp++] = i;
			}
		}
		for (int curr_index : delete_rows) {
			if (curr_index == 0) return rows;
			rows++;
			for (int k = curr_index; k > 1; k--) {
				for (int j = 1; j < BOARD_X_SIZE - 1; j++) {
					board[j][k] = board[j][k - 1];
				}
			}
		}
		return rows;
	}
	public static void moveLeft(char[][] board, Piece piece) {
		for (int i = 0; i < piece.getShape().length; i++) {
			for (int j = 0; j < piece.getShape()[i].length; j++) {
				if (piece.getShape()[i][j] != ' ' && board[i+piece.getX()-1][j+piece.getY()] != ' ') {
					return;
				}
			}
		}
		piece.moveLeft();
	}

	public static void moveRight(char[][] board, Piece piece) {
		for (int i = 0; i < piece.getShape().length; i++) {
			for (int j = 0; j < piece.getShape()[i].length; j++) {
				if (piece.getShape()[i][j] != ' ' && board[i+piece.getX()+1][j+piece.getY()] != ' ') {
					return;
				}
			}
		}
		piece.moveRight();
	}

	public static void rotate(char[][] board, Piece piece) {
		Piece temp = new Piece(piece.getType(), piece.getRotation()+1, piece.getX(), piece.getY());
		for (int i = 0; i < temp.getShape().length; i++) {
			for (int j = 0; j < temp.getShape()[i].length; j++) {
				if (temp.getShape()[i][j] != ' ' && board[i+temp.getX()][j+temp.getY()] != ' ') return;
				if (temp.getShape()[i][j] != ' ' && board[i+temp.getX()][j+temp.getY()+1] != ' ') return;
			}
		}
		piece.rotate();
	}
}