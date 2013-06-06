import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class UnchartedTest {

	unchartedTable uncharted;
	Ants game;
	private int[][] testTable;

	int[][] comparisonTable = { { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, 30, 30, 30, 30, 30, -1, 30 },
			{ 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 },
			{ 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 }, { 30, -1, -1, -1, -1, -1, 30, 30, 30, 30 }, { 30, 30, -1, 30, 30, 30, 30, 30, 30, 30 },
			{ 30, 30, -1, 30, 30, 30, 30, 30, -1, -1 }, { 30, 30, -1, 30, 30, 30, 30, 30, -1, -1 } };

	int[][] comparisonTable2 = { { 0, 0, 0, 0, 31, 31, 31, 31, 31, 0 }, { 0, 0, 0, 0, 31, 31, 31, 31, 31, 0 }, { 0, 0, 0, 0, 31, 31, 31, 31, 31, 0 },
			{ 0, 0, 0, 31, 31, 31, 31, 31, 31, 31 }, { 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 }, { 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 },
			{ 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 }, { 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 }, { 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 },
			{ 0, 0, 0, 31, 31, 31, 31, 31, 31, 31 } };

	@Before
	public void setUpTests() {
		Ants game = new Ants(100, 100, 10, 10, 10, 55, 25, 55);
		updateTable update = new updateTable(6);
		uncharted = new unchartedTable(update, game);
		testTable = uncharted.getUncharted();
	}

	@Test
	public void initilalizeUncharted() {
		for (int i = 0; i < comparisonTable.length; i++) {
			for (int j = 0; j < comparisonTable[0].length; j++) {
				assertEquals(31, testTable[i][j]);
			}
		}
	}

	@Test
	public void markAsSeen() {
		Tile tile = new Tile(1, 1);
		uncharted.setToSeen(tile);
		assertEquals(0, testTable[1][1]);
	}

	@Test
	public void createViewArea() {
		Tile tile = new Tile(1, 1);
		uncharted.setViewArea(tile);
		for (int i = 0; i < comparisonTable2.length; i++) {
			for (int j = 0; j < comparisonTable2[0].length; j++) {
				assertEquals(comparisonTable2[i][j], testTable[i][j]);
			}
		}
	}

	@Test
	public void valueOverTopBorder() {
		assertEquals(0, uncharted.isOverBoard(10, 10));
	}

	@Test
	public void valueOverBottomBorder() {
		assertEquals(9, uncharted.isOverBoard(-1, 10));
	}

	@Test
	public void valueIsWithinBorders() {
		assertEquals(5, uncharted.isOverBoard(5, 10));
	}

	@Test
	public void getsRightMovementValue() {
		Tile tile = new Tile(1, 1);
		assertEquals(155, uncharted.getMoveValue(tile, Aim.NORTH));
	}

	@Test
	public void setCoordinates() {
		Tile tile = new Tile(1, 1);
		tile = uncharted.setCoordinates(tile, 3, 5);
		assertEquals(6, tile.getRow());
		assertEquals(4, tile.getCol());
	}

	@Test
	public void moveViewArea() {
		Tile tile = new Tile(3, 6);
		uncharted.updateUnknown(tile, Aim.NORTH);
		int table[][] = { { 31, 31, 31, 31, 31, 0, 0, 0, 31, 31 }, { 31, 31, 31, 31, 0, 31, 31, 31, 0, 31 }, { 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 },
				{ 31, 31, 31, 31, 31, 31, 31, 31, 31, 31 } };
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				assertEquals(table[i][j], testTable[i][j]);
			}
		}
	}
// doesn't work unless you disable methods check if the tile can be seen
/*	@Test
	public void raiseUncharted() {
		Tile tile;
		for (int i = 0; i < comparisonTable2.length; i++) {
			for (int j = 0; j < comparisonTable2[0].length; j++) {
				tile = new Tile(i, j);
				uncharted.setToSeen(tile);
			}
		}
		uncharted.raiseUncharted();
		for (int i = 0; i < comparisonTable2.length; i++) {
			for (int j = 0; j < comparisonTable2[0].length; j++) {
				assertEquals(5, testTable[i][j]);
			}
			}
	}
	*/
	

	
}
