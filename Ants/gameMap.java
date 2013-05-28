public class gameMap {
	private Ilk[][] gameMap;
	Ants game;

	public gameMap(int height, int withd, Ants game) {
		this.game = game;
		gameMap = new Ilk[height][withd];
		initializeMap(height, withd);
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

	public boolean Visible(int row, int col) {
		Tile tile = new Tile(row, col);
		return game.isVisible(tile);
	}

	public boolean Visible(Tile tile) {
		return game.isVisible(tile);
	}

	public void clearUnseen(int row, int col, int[][] table) {
		int height;
		int withd;
		for (int i = 0; i < table.length; i++) {
			height = row + table[i][1];
			withd = col + table[i][0];
			if (!Visible(height, withd)) {
				if (getMapTile(height, withd) != Ilk.WATER) {
					gameMap[height][withd] = Ilk.LAND;
				}
			}
		}
	}

}
