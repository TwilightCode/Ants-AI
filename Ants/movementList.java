import java.util.ArrayList;


public class movementList {
	private ArrayList<movement> turnMoves;
	Ants game;
	public movementList(Ants game){
		this.game = game;
		turnMoves = new ArrayList<movement>();
	}
	
	public void addMove(Tile tile, Aim direction) {
		movement move = new movement(tile, game.getTile(tile, direction), direction);
		turnMoves.add(move);
	}

	public void emptyMoveList(){
		turnMoves.clear();
	}
	
	public ArrayList<movement> getList(){
		return turnMoves;
	}
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
