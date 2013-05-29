public class gameMap {
	private Ilk[][] gameMap;
	Ants game;
	updateTable update;

	public gameMap(Ants game, updateTable update) {
		this.game = game;
		this.update = update;
		gameMap = new Ilk[game.getRows()][game.getCols()];
		initializeMap(game.getRows(), game.getCols());
	}

	public void initializeMap(int withd, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < withd; j++) {
				gameMap[i][j] = Ilk.LAND;
			}
		}
	}

	public Ilk[][] getMap() {
		return gameMap;
	}

	public Ilk getMapTile(int row, int col) {
		return gameMap[row][col];
	}

	public void setMapTile(int row, int col) {
		Tile tile = new Tile(row, col);
		gameMap[row][col] = game.getIlk(tile);
	}
	
	public void setMapTile(int row, int col, Ilk tile) {
		gameMap[row][col] = tile;
	}
		
	public boolean isSeen(int row, int col) {
		int[][] WestTable = update.getWestTable();
		int[][] EastTable = update.getEastTable();
		int width = 0;
		int height = 0;
		for (int i = 0; i < WestTable.length; i++) {
			height = WestTable[i][1] + row;
			for (int j = WestTable[i][0] + 1; j < EastTable[i][0]; j++) {
				width = j + col;
				width = isWidthOverBoard(width);
				height = isHeightOverBoard(height);
				if (gameMap[height][width] == Ilk.MY_ANT){
				return true;	
				}
			}
		}
		return false;
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
		if (value >= gameMap.length) {
			return value - gameMap.length;
		} else if (value < 0) {
			return gameMap.length + value;
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
		if (value >= gameMap[0].length) {
			return value - gameMap[0].length;
		} else if (value < 0) {
			return gameMap[0].length + value;
		} else {
			return value;
		}
	}

	public void clearUnseen(int row, int col, Aim direction) {
		int height;
		int width;
		int[][] table = update.getUpdateTable(direction);
		for (int i = 0; i < table.length; i++) {
			height = row + table[i][1];
			width = col + table[i][0];
			width = isWidthOverBoard(width);
			height = isHeightOverBoard(height);
			if (!isSeen(height, width)) {
				if (getMapTile(height, width) != Ilk.WATER && getMapTile(height, width) != Ilk.MY_ANT) {
					gameMap[height][width] = Ilk.LAND;
				}
			}
		}
	}

}