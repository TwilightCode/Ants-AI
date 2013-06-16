import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MapTests {

	gameMap map;

	@Before
	public void setTestsUp() {
		Ants game = new Ants(100, 100, 10, 10, 10, 55, 25, 55);
		updateTable update = new updateTable(6);
		map = new gameMap(game, update);
	}

	@Test
	public void createMap() {
		Ilk[][] testMap = map.getMap();
		for (int i = 0; i < testMap.length; i++) {
			for (int j = 0; j < testMap[0].length; j++) {
				assertEquals(Ilk.LAND, testMap[i][j]);
			}
		}
	}
/*
	@Test
	public void changeOne() {
		map.setMapTile(1, 3, Ilk.FOOD);
		assertEquals(Ilk.FOOD, map.getMapTile(1, 3));
	}

	@Test
	public void canBeSeen() {
		map.setMapTile(1, 3, Ilk.MY_ANT);
		assertEquals(true, map.isSeen(9, 2));
		assertEquals(true, map.isSeen(3, 4));
	}

	@Test
	public void cantBeSeen() {
		map.setMapTile(1, 3, Ilk.MY_ANT);
		assertEquals(false, map.isSeen(9, 1));
		assertEquals(false, map.isSeen(3, 5));
	}

	@Test
	public void cleansInvisible() {
		Ilk[][] testMap = map.getMap();
		map.setMapTile(4, 3, Ilk.DEAD);
		map.setMapTile(5, 5, Ilk.DEAD);
		map.setMapTile(4, 7, Ilk.DEAD);
		//map.clearUnseen(2, 5, Aim.SOUTH);
		assertEquals(Ilk.LAND, testMap[4][3]);
		assertEquals(Ilk.LAND, testMap[5][5]);
		assertEquals(Ilk.LAND, testMap[4][7]);
	}

	@Test
	public void tileIsBlocked() {
		map.setMapTile(4, 3, Ilk.WATER);
		assertEquals(true, map.isTileBlocked(4, 3));
	}

	@Test
	public void tileIsNotBlocked() {
		assertEquals(false, map.isTileBlocked(1, 3));
	}

	@Test
	public void getMovableDirection() {
		map.setMapTile(6, 5, Ilk.WATER);
		map.setMapTile(5, 4, Ilk.WATER);
		map.setMapTile(5, 6, Ilk.WATER);
		omaArrayList<Aim> testTable = map.getMovableDirections(5, 5);
		assertEquals(1, testTable.size());
		assertEquals(Aim.NORTH, testTable.get(0));
	}
*/
	@Test
	public void getMovableDirections() {
		map.setMapTile(4, 5, Ilk.WATER);
		omaArrayList<Aim> testTable = map.getMovableDirections(5, 5);
		assertEquals(3, testTable.size());
		assertEquals(Aim.EAST, testTable.get(0));
		assertEquals(Aim.SOUTH, testTable.get(1));
		assertEquals(Aim.WEST, testTable.get(2));
	}
	
	@Test
	public void isStuck() {
		map.setMapTile(4, 5, Ilk.WATER);
		map.setMapTile(6, 5, Ilk.WATER);
		map.setMapTile(5, 4, Ilk.WATER);
		map.setMapTile(5, 6, Ilk.WATER);
		omaArrayList<Aim> testTable = map.getMovableDirections(5, 5);
		assertEquals(0, testTable.size());
		}
}
