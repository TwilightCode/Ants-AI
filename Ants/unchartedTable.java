public class unchartedTable {
	private int[][] uncharted;
	updateTable update;
	gameMap area;
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
	public unchartedTable(updateTable update, gameMap map) {
		this.update = update;
		area = map;
		Ilk[][] field = area.getMap();
		initializeUncharted(field);
		mapHeight = field.length;
		mapWidth = field[0].length;
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
				uncharted[i][j] = 31;
			}
		}
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
				width = area.isWidthOverBoard(width);
				height = area.isHeightOverBoard(height);
				if (uncharted[height][width] != -1) {
					uncharted[height][width] = 0;
					area.setMapTile(height, width);
				}
			}
		}
	}

	/**
	 * goes through uncharted and raises the value of areas that are unseen by
	 * own ants
	 * 
	 * @param game
	 *            contains the current game
	 */
	public void raiseUncharted() {
		for (int i = 0; i < uncharted.length; i++) {
			for (int j = 0; j < uncharted[0].length; j++) {
				if ((uncharted[i][j] < 30 && uncharted[i][j] > -1 && !area.isSeen(i,j))) {
					uncharted[i][j] += 1;
				}
			}
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
			width = area.isWidthOverBoard(width);
			height = area.isHeightOverBoard(height);
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
			width = area.isWidthOverBoard(width);
			height = area.isHeightOverBoard(height);
			if (uncharted[height][width] != -1) {
				uncharted[height][width] = 0;
				area.setMapTile(height, width);
			}
		}
	}
}