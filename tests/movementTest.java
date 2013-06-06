import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class movementTest {
movementList moves;
	
	@Before
	public void setTestup(){
		Ants game = new Ants(100, 100, 10, 10, 10, 55, 25, 55);
		moves = new movementList(game);
	}
	
	
	@Test
	public void tileIsOccupied() {
		Tile tile = new Tile(1,1);
		Tile newTile;
		moves.addMove(tile, Aim.NORTH);
	    newTile = new Tile(0,1);
		assertEquals(true,moves.isToBeOccupied(newTile));
	}

	@Test
	public void tileIsFree(){
	Tile tile = new Tile(1,1);
	Tile newTile;
	moves.addMove(tile, Aim.NORTH);
    newTile = new Tile(2,1);
	assertEquals(false,moves.isToBeOccupied(newTile));
	}
	
	@Test
	public void tileIsOpening(){
		Tile tile = new Tile(1,1);
		Tile newTile;
		moves.addMove(tile, Aim.NORTH);
	    newTile = new Tile(1,1);
		assertEquals(true,moves.isAntsLocationFree(newTile));
	}
	
	@Test
	public void tileIsStillOccupied(){
		Tile tile = new Tile(5,5);
		Tile newTile;
		moves.addMove(tile, Aim.NORTH);
		newTile = new Tile(1,1);
		assertEquals(false,moves.isAntsLocationFree(newTile));
	}
}
