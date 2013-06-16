import java.io.IOException;


import java.io.*;
import java.util.*;

/**
 * Starter bot implementation. Some methods in this class have been added or
 * modified.
 */
public class MyBot extends Bot {
	updateTable update;
	unchartedTable uncharted;
	Ants ants;
	movementList moves;
	routeSearch search;
	foodList eat;
	private int antsNumber = 0;
	private int turn = 0;

	/**
	 * Main method executed by the game engine for starting the bot.
	 * 
	 * @param args
	 *            command line arguments
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static void main(String[] args) throws IOException {
		new MyBot().readSystemInput();
	}

	public void setup(int loadTime, int turnTime, int rows, int cols, int turns, int viewRadius2, int attackRadius2, int spawnRadius2) {
		setAnts(new Ants(loadTime, turnTime, rows, cols, turns, viewRadius2, attackRadius2, spawnRadius2));
		ants = getAnts();
		moves = new movementList(ants);
		update = new updateTable(ants.getViewRadius2());
		uncharted = new unchartedTable(update, ants, moves);
	}

	/**
	 * checks if any of the ants have died during other players turns
	 */
	public void checkCasualties() {
		omaArrayList<movement> table;
		table = moves.getList();
		for (int i = 0; i < table.size(); i++) {
			if (ants.getIlk(table.get(i).getNewTile()) == Ilk.DEAD) {
				antsNumber -= 1;
			}
		}
	}

	/**
	 * makes updates to game state
	 */
	public void updateState() {
		omaArrayList<movement> table;
		if (turn == 2) {
			uncharted.raiseUncharted();
			turn = 0;
		} else {
			turn++;
		}
		table = moves.getList();
		antsNumber = table.size();
		checkCasualties();
		for (int i = 0; i < table.size(); i++) {
			uncharted.updateUnknown(table.get(i).getCurrentTile(), table.get(i).getDirection());
		}
		moves.emptyMoveList();
	}

	public void getFood() {
		omaArrayList<Aim> path = null;
		Tile ant;
		int[][] pathTable;
		for (Tile food : ants.getFoodTiles()) {
			if (!eat.foodIsFetched(food)) {
				pathTable = initializePathTable(food, 6);
				path = search.initFoodSearch(pathTable);
				if (path == null) {
					continue;
				}
				ant = findAnt(food, path);
				path = switchRouteAround(path);
				eat.addPath(ant, food, path);
			}
		}
	}

	public int[][] initializePathTable(Tile current, int area) {
		Random r = new Random();
		r.nextInt();
		Tile tile = uncharted.setCoordinates(current, -area, -area);
		Tile newTile;
		int[][] pathTable;
		int[][] unchartedTable = uncharted.getUncharted();
		pathTable = new int[area * 2 + 1][area * 2 + 1];
		for (int i = 0; i < pathTable.length; i++) {
			for (int j = 0; j < pathTable[0].length; j++) {
				newTile = uncharted.setCoordinates(tile, j, i);
				if (ants.getIlk(newTile) == Ilk.WATER) {
					pathTable[i][j] = -1;
				} else if (unchartedTable[newTile.getRow()][newTile.getCol()] == 31) {
					pathTable[i][j] = -1;
				} else if (ants.getIlk(newTile) == Ilk.MY_ANT && !eat.antHasJob(newTile)) {
					pathTable[i][j] = -3;
				} else {
					pathTable[i][j] = -2;
				}
			}
		}
		return pathTable;
	}

	public Tile findAnt(Tile start, omaArrayList<Aim> path) {
		Tile newTile = start;
		for (int i = path.size() - 1; i >= 0; i--) {
			newTile = ants.getTile(newTile, path.get(i));
		}
		return newTile;
	}

	public omaArrayList<Aim> switchRouteAround(omaArrayList<Aim> path) {
		omaArrayList<Aim> reverse = new omaArrayList<Aim>();
		for (int i = 0; i < path.size(); i++) {
			Aim opposite = search.getOppositeDirection(path.get(i));
			reverse.add(opposite);
		}
		return reverse;
	}

	/**
	 * sets new ants with view area
	 * 
	 * @param tile
	 *            location of the ant
	 * @param count
	 *            tells how many ants all ready have the view area and are not
	 *            dead
	 */
	public void setNewAnt(Tile tile, int count) {
		if (antsNumber < count) {
			uncharted.setViewArea(tile);
		}
	}

	/**
	 * executes bots turn
	 */
	@Override
	public void doTurn() {
		Aim direction = null;
		int count = 0;
		eat = new foodList(ants);
		search = new routeSearch(ants);
		updateState();
		getFood();
		for (Tile myAnt : ants.getMyAnts()) {
			count++;
			setNewAnt(myAnt, count);
			if (eat.antHasJob(myAnt)) {
				direction = eat.moveAnt(myAnt);
			}
			if (direction != null) {
				moves.addMove(myAnt, direction);
				ants.issueOrder(myAnt, direction);
			}
		}

	}

}
