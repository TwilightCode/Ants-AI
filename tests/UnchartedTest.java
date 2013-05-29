import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnchartedTest {
	gameMap Map;
	unchartedTable uncharted;
	


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
		this.Map = new gameMap(game, update); 
		uncharted = new unchartedTable(update, Map);
	}

	@Test
	public void initilalizeUncharted() {
		int[][] testTable = uncharted.getUncharted();
		for (int i = 0; i < testTable.length; i++) {
			for (int j = 0; j < testTable[0].length; j++) {
				assertEquals(31, testTable[i][j]);
			}
		}
	}

	@Test
	public void createViewArea() {
		uncharted.setViewArea(1, 1);
		int[][] testTable = uncharted.getUncharted();
		for (int i = 0; i < comparisonTable2.length; i++) {
			for (int j = 0; j < comparisonTable2[0].length; j++) {
				assertEquals(comparisonTable2[i][j], testTable[i][j]);
			}
		}
	}

	@Test
	public void valueOverTopBorder() {
		assertEquals(0, Map.isHeightOverBoard(10));
		assertEquals(0, Map.isWidthOverBoard(10));
	}

	@Test
	public void valueOverBottomBorder() {
		assertEquals(9, Map.isHeightOverBoard(-1));
		assertEquals(9, Map.isWidthOverBoard(-1));
	}

	@Test
	public void valueIsWithinBorders() {
		assertEquals(5, Map.isHeightOverBoard(5));
		assertEquals(5, Map.isWidthOverBoard(5));
	}

	@Test
	public void getsRightMovementValue() {
		assertEquals(155, uncharted.getMoveValue(1, 1, Aim.NORTH));
	}
	
	@Test
	public void moveViewArea() {
		int[][] testTable = uncharted.getUncharted();
		uncharted.setTable(6, 3, Aim.NORTH);
		int table[][] = { { 31, 31, 0, 0, 0, 31, 31 }, { 31, 0, 31, 31, 31, 0, 31 }, { 31, 31, 31, 31, 31, 31, 31 }, { 31, 31, 31, 31, 31, 31, 31 } };
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				assertEquals(table[i][j], testTable[i][j + 3]);
			}

		}
	}
}
