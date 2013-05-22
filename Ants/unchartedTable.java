public class unchartedTable {
	int[][] uncharted;
	Ants game;
	updateTable update;

	public int[][] getUncharted() {
		return uncharted;
	}

	public void initializeUncharted(int rows, int cols, Ilk[][] gameField) {
		uncharted = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (gameField[i][j] != Ilk.WATER) {
					uncharted[i][j] = 30;
				} else {
					uncharted[i][j] = -1;
				}
			}
		}
	}

	public boolean Visible(int row, int col) {
		Tile brick = new Tile(row, col);
		return game.isVisible(brick);
	}

	public void setViewArea(int vision, Tile MyAnt) {
		int[][] easternLine = update.getEastTable();
		int[][] westernLine = update.getWestTable();
		int row = MyAnt.getRow();
		int col = MyAnt.getCol();
		for (int i = 0; i < easternLine.length; i++) {
			col += easternLine[i][1];
			for (int j = easternLine[i][0] + 1; j < westernLine[i][0]; j++) {
				row += j;
				uncharted[col][row] = 0;
			}
		}
	}

	public void raiseUncharted() {
		for (int i = 0; i < uncharted.length; i++) {
			for (int j = 0; j < uncharted[0].length; j++) {
				if ((uncharted[i][j] < 30 && uncharted[i][j] > -1) && !Visible(i, j)) {
					uncharted[i][j] += 1;
				}
			}
		}
	}

	public int isOverBoard(int value, int max) {
		if (value >= max) {
			return value - max;
		}
		return value;
	}

	public int getMoveValue(Tile MyAnt, int table[][]) {
		int row;
		int col;
		int value = 0;
		for (int i = 0; i < table.length; i++) {
			row = MyAnt.getRow() + table[i][0];
			col = MyAnt.getCol() + table[i][1];
			row = isOverBoard(row, game.getRows());
			col = isOverBoard(col, game.getCols());
			value += uncharted[row][col];
		}
		return value;
	}

	public void setTable(Tile MyAnt, int table[][]) {
		int row;
		int col;
		for (int i = 0; i < table.length; i++) {
			row = MyAnt.getRow() + table[i][0];
			col = MyAnt.getCol() + table[i][1];
			row = isOverBoard(row, game.getRows());
			col = isOverBoard(col, game.getCols());
			uncharted[row][col] = 0;
		}
	}
}
