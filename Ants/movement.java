
public class movement {
private Tile currentTile;
private Tile newTile;
private Aim direction;


public movement(Tile currentTile, Tile newTile, Aim direction){
	this.currentTile = currentTile;
	this.newTile = newTile;
	this.direction = direction;
}

public Tile getCurrentTile(){
	return currentTile;
	}

public Tile getNewTile(){
	return newTile;
	}

public Aim getDirection(){
	return direction;
	}



}
