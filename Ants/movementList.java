import java.util.ArrayList;

public class movementList {
	private ArrayList<movement> turnMoves;
	Ants game;

	/**
	 * constructor
	 * 
	 * @param game
	 *            current game
	 */
	public movementList(Ants game) {
		this.game = game;
		turnMoves = new ArrayList<movement>();
	}

	/**
	 * adds new move to the order list
	 * 
	 * @param tile
	 *            location of the ant
	 * @param direction
	 *            of ants movement
	 */
	public void addMove(Tile tile, Aim direction) {
		movement move = new movement(tile, game.getTile(tile, direction), direction);
		turnMoves.add(move);
	}

	/**
	 * clears list
	 */
	public void emptyMoveList() {
		turnMoves.clear();
	}

	/**
	 * @return list that contains turns movements
	 */
	public ArrayList<movement> getList() {
		return turnMoves;
	}

	/**
	 * check if some ant has all ready done a move to this tile
	 * 
	 * @param tile
	 *            location ant is about to move
	 * @return true if some ant has all ready occupied the tile false if it is
	 *         free
	 */
	public boolean isToBeOccupied(Tile tile) {
		movement move;
		for (int i = 0; i < turnMoves.size(); i++) {
			move = turnMoves.get(i);
			if (move.getNewTile().getRow() == tile.getRow() && move.getNewTile().getCol() == tile.getCol()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if the ant that is in a given tile is about to move
	 * 
	 * @param tile
	 *            location of the other ant
	 * @return true if ant is about to move false otherwise
	 */
	
	public boolean isAntsLocationFree(Tile tile) {
		movement move;
		for (int i = 0; i < turnMoves.size(); i++) {
			move = turnMoves.get(i);
			if (move.getCurrentTile().getRow() == tile.getRow() && move.getCurrentTile().getCol() == tile.getCol()) {
				return true;
			}
		}
		return false;
	}
}
