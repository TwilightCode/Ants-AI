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

	public void raiseUncharted() {
		for (int i = 0; i < uncharted.length; i++) {
			for (int j = 0; j < uncharted[0].length; j++) {
				if ((uncharted[i][j] < 30 && uncharted[i][j] > -1) && !Visible(i, j)) {
					uncharted[i][j] += 1;
				}
			}
		}
	}

	public void setTable(Tile MyAnt, int table[][]) {
		int row;
		int col;
		for (int i = 0; i < table.length; i++) {
			row = MyAnt.getRow() + table[i][0];
			col = MyAnt.getCol() + table[i][1];
			uncharted[row][col] = 0;
		}
	}

	public void checkDirection(Aim direction, Tile MyAnt) {

		if (direction == Aim.NORTH) {
			setTable(MyAnt, update.getNorthTable());
		}
		if (direction == Aim.EAST) {
			setTable(MyAnt, update.getEastTable());
		}
		if (direction == Aim.SOUTH) {
			setTable(MyAnt, update.getSouthTable());
		}
		if (direction == Aim.WEST) {
			setTable(MyAnt, update.getWestTable());
		}
	}

}
