import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class updateTableTest {
	updateTable update = new updateTable();

	@Before
	public void setTests() {
		update.setTables(55);
	}
	 
	@Test
	public void checkNorthTable() {
		int[][] table = { { 7, -3 }, { 6, -5 }, { 5, -6 }, { 4, -7 }, { 3, -7 }, { 2, -8 }, { 1, -8 }, { 0, -8 }, { -1, -8 }, { -2, -8 }, { -3, -7 },
				{ -4, -7 }, { -5, -6 }, { -6, -5 }, { -7, -3 } };
		int[][] testTable = update.getNorthTable();
		for (int i = 0; i < testTable.length; i++) {
			assertEquals(table[i][0], testTable[i][0]);
			assertEquals(table[i][1], testTable[i][1]);
		}
	}

	@Test
	public void checkSouthTable() {
		int[][] table = { { 7, 3 }, { 6, 5 }, { 5, 6 }, { 4, 7 }, { 3, 7 }, { 2, 8 }, { 1, 8 }, { 0, 8 }, { -1, 8 }, { -2, 8 }, { -3, 7 }, { -4, 7 },
				{ -5, 6 }, { -6, 5 }, { -7, 3 } };
		int[][] testTable = update.getSouthTable();
		for (int i = 0; i < testTable.length; i++) {
			assertEquals(table[i][0], testTable[i][0]);
			assertEquals(table[i][1], testTable[i][1]);
		}
	}

	@Test
	public void checkEastTable() {
		int[][] table = { { 3, 7 }, { 5, 6 }, { 6, 5 }, { 7, 4 }, { 7, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, { 8, -1 }, { 8, -2 }, { 7, -3 }, { 7, -4 },
				{ 6, -5 }, { 5, -6 }, { 3, -7 } };
		int[][] testTable = update.getEastTable();
		for (int i = 0; i < testTable.length; i++) {
			assertEquals(table[i][0], testTable[i][0]);
			assertEquals(table[i][1], testTable[i][1]);
		}
	}

	@Test
	public void checkWestTable() {
		int[][] table = { { -3, 7 }, { -5, 6 }, { -6, 5 }, { -7, 4 }, { -7, 3 }, { -8, 2 }, { -8, 1 }, { -8, 0 }, { -8, -1 }, { -8, -2 }, { -7, -3 },
				{ -7, -4 }, { -6, -5 }, { -5, -6 }, { -3, -7 } };
		int[][] testTable = update.getWestTable();
		for (int i = 0; i < testTable.length; i++) {
			assertEquals(table[i][0], testTable[i][0]);
			assertEquals(table[i][1], testTable[i][1]);
		}
	}

	@Test
	public void getRightTable() {
		int[][] table = { { 3, 7 }, { 5, 6 }, { 6, 5 }, { 7, 4 }, { 7, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, { 8, -1 }, { 8, -2 }, { 7, -3 }, { 7, -4 },
				{ 6, -5 }, { 5, -6 }, { 3, -7 } };
		int[][] testTable = update.getUpdateTable(Aim.EAST);
		for (int i = 0; i < testTable.length; i++) {
			assertEquals(table[i][0], testTable[i][0]);
			assertEquals(table[i][1], testTable[i][1]);
		}
	}
}
