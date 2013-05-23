import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UnchartedTest {
	Ilk[][] gameMap = { { Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.WATER, Ilk.WATER, Ilk.WATER, Ilk.WATER, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND },
			{ Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.WATER },
			{ Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.LAND, Ilk.WATER, Ilk.WATER } };;
	unchartedTable uncharted;

	int[][] comparisonTable = { { 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, 30, 30, 30, 30, 30, -1, 30 },
			{ 30, 30, 30, 30, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 },
			{ 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 }, { 30, -1, -1, -1, -1, -1, 30, 30, 30, 30 }, { 30, 30, -1, 30, 30, 30, 30, 30, 30, 30 },
			{ 30, 30, -1, 30, 30, 30, 30, 30, -1, -1 }, { 30, 30, -1, 30, 30, 30, 30, 30, -1, -1 } };

	int[][] comparisonTable2 = { { 0, 0, 0, 0, 30, 30, 30, 30, 30, 0 }, { 0, 0, 0, 0, 30, 30, 30, 30, -1, 0 }, { 0, 0, 0, 0, 30, 30, 30, 30, 30, 0 },
			{ 0, 0, 0, -1, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 }, { 30, 30, 30, -1, 30, 30, 30, 30, 30, 30 },
			{ 30, -1, -1, -1, -1, -1, 30, 30, 30, 30 }, { 30, 30, -1, 30, 30, 30, 30, 30, 30, 30 }, { 30, 30, -1, 30, 30, 30, 30, 30, -1, -1 },
			{ 0, 0, -1, 30, 30, 30, 30, 30, -1, -1 } };

	@Before
	public void setUpTests() {
		uncharted = new unchartedTable(6, gameMap);
	}

	@Test
	public void initilalizeUncharted() {
		int[][] testTable = uncharted.getUncharted();
		for (int i = 0; i < comparisonTable.length; i++) {
			for (int j = 0; j < comparisonTable[0].length; j++) {
				assertEquals(comparisonTable[i][j], testTable[i][j]);
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
		assertEquals(0, uncharted.isHeightOverBoard(10));
		assertEquals(0, uncharted.isWidthOverBoard(10));
	}

	@Test
	public void valueOverBottomBorder() {
		assertEquals(9, uncharted.isHeightOverBoard(-1));
		assertEquals(9, uncharted.isWidthOverBoard(-1));
	}

	@Test
	public void valueIsWithinBorders() {
		assertEquals(5, uncharted.isHeightOverBoard(5));
		assertEquals(5, uncharted.isWidthOverBoard(5));
	}

	@Test
	public void getsRightMovementValue() {
		assertEquals(88, uncharted.getMoveValue(1, 1, Aim.NORTH));
	}

	@Test
	public void getsRightMovementValue2() {
		assertEquals(119, uncharted.getMoveValue(1, 1, Aim.EAST));
	}

	@Test
	public void moveViewArea() {
		int[][] testTable = uncharted.getUncharted();
		uncharted.setTable(6, 3, Aim.NORTH);
		int table[][] = { { 30, 30, 0, 0, 0, 30, 30 }, { 30, 0, 30, 30, 30, -1, 30 }, { 30, 30, 30, 30, 30, 30, 30 }, { -1, 30, 30, 30, 30, 30, 30 } };
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[0].length; j++) {
				assertEquals(table[i][j], testTable[i][j + 3]);
			}

		}
	}
}
