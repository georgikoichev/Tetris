package tetris;

import java.util.Random;

public class Piece {
	/*
	 * 1 = square
	 * 2 = line
	 * 3 = L
	 * 4 = reverse L
	 * 5 = s
	 * 6 = z
	 * 7 = T
	 */
	
	private int type;
	private int rotation;
	private int x;
	private int y;

	public Piece() {
		Random r = new Random();
		setType(r.nextInt(7)+1);
		setRotation(1);
		setX(5);
		setY(1);
	}

	public Piece(int type, int rotation, int x, int y) {
		setRotation(rotation);
		setType(type);
		setX(x);
		setY(y);
	}
	
	public void setRotation(int rotation) {
	    if (rotation < 1 || rotation > 4) {
	        this.rotation = 1;
	    } else {
	    	this.rotation = rotation;
	    }
	}
	
	public int getRotation() {
		return rotation;
	}

	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}

	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}

	public char[][] getShape() {
		int type = this.type;
		int rotation = this.rotation;
		
		switch (type) {
		case 1:
			return new char[][]{{'1', '1'},
								{'1', '1'}};
		case 2:
			switch (rotation) {
				case 1, 3:
					return new char[][]{{'2', '2', '2', '2'}};
				case 2, 4:
					return new char[][]{{'2'},
										{'2'},
										{'2'},
										{'2'}};
			}
			
		case 3:
			switch (rotation) {
			case 1:
				return new char[][]{{'3', ' '},
									{'3', ' '},
									{'3', '3'}};
			case 2:
				return new char[][]{{'3', '3', '3'},
									{'3', ' ', ' '}};
			case 3:
				return new char[][]{{'3', '3'},
									{' ', '3'},
									{' ', '3'}};
			case 4:
				return new char[][]{{' ', ' ', '3'},
									{'3', '3', '3'}};

			}

		case 4:
			switch (rotation) {
			case 1:
				return new char[][]{{' ', '4'},
									{' ', '4'},
									{'4', '4'}};
			case 2:
				return new char[][]{{'4', ' ', ' '},
									{'4', '4', '4'}};
			case 3:

				return new char[][]{{'4', '4'},
									{'4', ' '},
									{'4', ' '}};
			case 4:
				return new char[][]{{'4', '4', '4'},
									{' ', ' ', '4'}};
			}

		case 5:
			switch (rotation) {
			case 1, 3:
					return new char[][]{{' ', '5', '5'},
										{'5', '5', ' '}};
			case 2, 4:
					return new char[][]{{'5', ' '},
										{'5', '5'},
										{' ', '5'}};
			}

		case 6:
			switch (rotation) {
			case 1, 3:
					return new char[][]{{'6', '6', ' '},
										{' ', '6', '6'}};
			case 2, 4:
					return new char[][]{{' ', '6'},
										{'6', '6'},
										{'6', ' '}};
			}

		case 7:
			switch (rotation) {
			case 1:
				return new char[][]{{'7', '7', '7'},
									{' ', '7', ' '}};
			case 2:
				return new char[][]{{' ', '7'},
									{'7', '7'},
									{' ', '7'}};
			case 3:
				return new char[][]{{' ', '7', ' '},
									{'7', '7', '7'}};
			case 4:
				return new char[][]{{'7', ' '},
									{'7', '7'},
									{'7', ' '}};
			}
		}
		return new char[][]{{1}};
	}
	
	public void rotate() {
		this.setRotation(this.getRotation() + 1);
	}
	public void moveDown() {
		if (!(y == Tetris.BOARD_Y_SIZE-1)) {
			this.y++;
		}
	}

	public void moveLeft() {
		if (!(x == 0)) {
			this.x--;
		}
	}

	public void moveRight() {
		if (!(x == Tetris.BOARD_X_SIZE-1)) {
			this.x++;
		}
	}
}