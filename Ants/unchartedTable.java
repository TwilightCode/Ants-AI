public class unchartedTable {
	private int[][] uncharted;
	updateTable update;
	int mapHeight;
	int mapWidth;

	/**
	 * constructor
	 * 
	 * @param vision
	 *            of the ant in a square format
	 * @param gameField
	 *            the game table
	 */
	public unchartedTable(int vision, Ilk[][] gameField) {
		update = new updateTable(vision);
		initializeUncharted(gameField);
		mapHeight = gameField.length;
		mapWidth = gameField[0].length;
	}

	/**
	 * 
	 * @return uncharted contains the information about how many turns it has
	 *         been when the tile was last visible
	 */
	public int[][] getUncharted() {
		return uncharted;
	}

	/**
	 * initializes uncharted
	 * 
	 * @param gameField
	 *            is the map of current game
	 */
	public void initializeUncharted(Ilk[][] gameField) {
		uncharted = new int[gameField.length][gameField[0].length];
		for (int i = 0; i < gameField.length; i++) {
			for (int j = 0; j < gameField[0].length; j++) {
				if (gameField[i][j] != Ilk.WATER) {
					uncharted[i][j] = 30;
				} else {
					uncharted[i][j] = -1;
				}
			}
		}
	}

	/**
	 * tells if tile is visible or not
	 * 
	 * @param row
	 *            vertical location of a tile
	 * @param col
	 *            horizontal location of a tile
	 * @return true if tile is visible false if invisible
	 */
	public boolean Visible(int row, int col, Ants game) {
		Tile brick = new Tile(row, col);
		return game.isVisible(brick);
	}

	/**
	 * establishes new view area
	 * 
	 * @param row
	 *            x line
	 * @param col
	 *            y line
	 */
	public void setViewArea(int row, int col) {
		int[][] easternLine = update.getEastTable();
		int[][] westernLine = update.getWestTable();
		int width = 0;
		int height = 0;
		for (int i = 0; i < westernLine.length; i++) {
			height = westernLine[i][1] + col;
			for (int j = westernLine[i][0] + 1; j < easternLine[i][0]; j++) {
				width = j + row;
				width = isWidthOverBoard(width);
				height = isHeightOverBoard(height);
				if (uncharted[height][width] != -1) {
					uncharted[height][width] = 0;
				}
			}
		}
	}

	/**
	 * goes through uncharted and raises the value of areas that are unseen by
	 * own ants
	 */
	public void raiseUncharted(Ants game) {
		for (int i = 0; i < uncharted.length; i++) {
			for (int j = 0; j < uncharted[0].length; j++) {
				if ((uncharted[i][j] < 30 && uncharted[i][j] > -1) && !Visible(i, j, game)) {
					uncharted[i][j] += 1;
				}
			}
		}
	}

	/**
	 * checks if value of vertical coordinate goes over the map and returns
	 * value that is within the border
	 * 
	 * @param value
	 *            vertical coordinate
	 * @return value contains either given value if within borders or the amount
	 *         that it goes over the top
	 */
	public int isHeightOverBoard(int value) {
		if (value >= mapHeight) {
			return value - mapHeight;
		} else if (value < 0) {
			return mapHeight + value;
		} else {
			return value;
		}
	}

	/**
	 * checks if value of horizontal coordinate goes over the map and returns
	 * value that is within the border
	 * 
	 * @param value
	 *            horizontal coordinate
	 * @return value contains either given value if within borders or the amount
	 *         that it goes over the top
	 */
	public int isWidthOverBoard(int value) {
		if (value >= mapWidth) {
			return value - mapWidth;
		} else if (value < 0) {
			return mapWidth + value;
		} else {
			return value;
		}
	}

	/**
	 * counts how much ant would gain from moving to the direction given in
	 * table
	 * 
	 * @param row
	 *            x line
	 * @param col
	 *            y line
	 * 
	 * @param direction
	 *            where ant would move
	 * @return value is the amount gain from moving to this direction
	 */
	public int getMoveValue(int row, int col, Aim direction) {
		int width = 0;
		int height = 0;
		int value = 0;
		int[][] table = update.getUpdateTable(direction);
		for (int i = 0; i < table.length; i++) {
			width = row + table[i][0];
			height = col + table[i][1];
			width = isWidthOverBoard(width);
			height = isHeightOverBoard(height);
			value += uncharted[height][width];
		}
		return value;
	}

	/**
	 * sets the new area covered by the view radius to zero.
	 * 
	 * @param row
	 *            x line
	 * @param col
	 *            y line
	 * @param direction
	 *            where ant moves
	 */
	public void setTable(int row, int col, Aim direction) {
		int width = 0;
		int height = 0;
		int[][] table = update.getUpdateTable(direction);
		for (int i = 0; i < table.length; i++) {
			width = row + table[i][0];
			height = col + table[i][1];
			width = isWidthOverBoard(width);
			height = isHeightOverBoard(height);
			if (uncharted[height][width] != -1) {
				uncharted[height][width] = 0;
			}
		}
	}
}
