public class movement {
	private Tile currentTile;
	private Tile newTile;
	private Aim direction;

	/**
	 * constructor
	 * 
	 * @param currentTile
	 *            location of the tile ant is on before moving
	 * @param newTile
	 *            location of the tile ant is on after moving
	 * @param direction
	 *            movement direction of the ant
	 */
	public movement(Tile currentTile, Tile newTile, Aim direction) {
		this.currentTile = currentTile;
		this.newTile = newTile;
		this.direction = direction;
	}

	/**
	 * @return currentTile locations of the ant
	 */
	public Tile getCurrentTile() {
		return currentTile;
	}

	/**
	 * @return newTile location of the ant after the move
	 */
	public Tile getNewTile() {
		return newTile;
	}

	/**
	 * @return direction of the ants movement
	 */
	public Aim getDirection() {
		return direction;
	}

}
