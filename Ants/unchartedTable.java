import java.util.ArrayList;
import java.util.Random;

public class unchartedTable {
	private int[][] uncharted;
	updateTable update;
	Ants game;
	private int mapHeight;
	private int mapWidth;

	/**
	 * constructor
	 * 
	 * @param vision
	 *            of the ant in a square format
	 * @param gameField
	 *            the game table
	 */
	public unchartedTable(updateTable update, Ants game) {
		this.game = game;
		this.update = update;
		this.mapHeight = game.getRows();
		this.mapWidth = game.getCols();
		initializeUncharted();
	}

	/**
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
	public void initializeUncharted() {
		uncharted = new int[mapHeight][mapWidth];
		for (int i = 0; i < mapHeight; i++) {
			for (int j = 0; j < mapWidth; j++) {
				uncharted[i][j] = 31;
			}
		}
	}

	public void setUnknownTile(Tile tile) {
		Ilk type = game.getIlk(tile);
		if (type == Ilk.WATER) {
			uncharted[tile.getRow()][tile.getCol()] = -1;
		} else {
			uncharted[tile.getRow()][tile.getCol()] = 0;
		}
	}

	public void setToSeen(Tile tile) {
		uncharted[tile.getRow()][tile.getCol()] = 0;
	}

	/**
	 * establishes new view area
	 * 
	 * @param row
	 *            x line
	 * @param col
	 *            y line
	 */
	public void setViewArea(Tile tile) {
		int[][] easternLine = update.getEastTable();
		int[][] westernLine = update.getWestTable();
		Tile newTile;
		for (int i = 0; i < westernLine.length; i++) {
			for (int j = westernLine[i][0] + 1; j < easternLine[i][0]; j++) {
				newTile = setCoordinates(tile, j, westernLine[i][1]);
				if (uncharted[newTile.getRow()][newTile.getCol()] == 31) {
					setUnknownTile(newTile);
				} else {
					setToSeen(newTile);
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
		Tile tile;
		for (int i = 0; i < uncharted.length; i++) {
			for (int j = 0; j < uncharted[0].length; j++) {
				tile = new Tile(i, j);
				if (((uncharted[i][j] + 5) < 30 && uncharted[i][j] > -1 && game.isVisible(tile))) {
					uncharted[i][j] += 5;
				}
			}
		}
	}

	public Aim getBestDirection(Tile tile) {
		int value;
		int max = 0;
		Aim direction = null;
		ArrayList<Aim> directions = getMovableDirections(tile);
		if (directions.size() == 0) {
			return null;
		}
		for (int i = 0; i < directions.size(); i++) {
			value = getMoveValue(tile, directions.get(i));
			if (max < value) {
				max = value;
				direction = directions.get(i);
			} else if (max == value) {
				direction = getRandomDirection(direction, directions.get(i));
			}
		}
		return direction;
	}

	public Aim getRandomDirection(Aim direction, Aim otherDirection) {
		Random rand = new Random();
		if (rand.nextInt(2) == 0) {
			return direction;
		} else {
			return otherDirection;
		}
	}

	public Tile setCoordinates(Tile tile, int borderX, int borderY) {
		return new Tile(isOverBoard((tile.getRow() + borderY), mapHeight), isOverBoard((tile.getCol() + borderX), mapWidth));
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
	public int getMoveValue(Tile tile, Aim direction) {
		Tile newTile;
		int value = 0;
		int[][] table = update.getUpdateTable(direction);
		for (int i = 0; i < table.length; i++) {
			newTile = setCoordinates(tile, table[i][0], table[i][1]);
			value += uncharted[newTile.getRow()][newTile.getCol()];
		}
		return value;
	}

	public boolean isTileBlocked(Tile tile) {
		if (game.getIlk(tile) == Ilk.LAND || game.getIlk(tile) == Ilk.DEAD) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<Aim> getMovableDirections(Tile tile) {
		Tile newTile;
		ArrayList<Aim> table = new ArrayList<Aim>();
		Aim[] directions = { Aim.NORTH, Aim.EAST, Aim.SOUTH, Aim.WEST };
		for (int i = 0; i < directions.length; i++) {
			newTile = game.getTile(tile, directions[i]);
			if (!isTileBlocked(newTile)) {
				table.add(directions[i]);
			}
		}
		return table;
	}

	public int isOverBoard(int value, int max) {
		if (value >= max) {
			return value - max;
		} else if (value < 0) {
			return max + value;
		} else {
			return value;
		}
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
		public void updateUnknown(Tile tile, Aim direction) {
		Tile newTile;
		int[][] table = update.getUpdateTable(direction);
		for (int i = 0; i < table.length; i++) {
			newTile = setCoordinates(tile, table[i][0], table[i][1]);
			if (uncharted[newTile.getRow()][newTile.getCol()] == 31) {
				setUnknownTile(newTile);
			} else {
				setToSeen(newTile);
			}
		}
	}
		
}