public class updateTable {
	public static int[][] NorthTable;
	public static int[][] EastTable;
	public static int[][] SouthTable;
	public static int[][] WestTable;

	/**
	 * 
	 * @return updateTable contains locations of all the tiles that need to be
	 *         updated when ants moves in a relation to the current ants
	 *         standing point.
	 */
	public int[][] getNorthTable() {
		return NorthTable;
	}

	public int[][] getSouthTable() {
		return SouthTable;
	}

	public int[][] getEastTable() {
		return EastTable;
	}

	public int[][] getWestTable() {
		return WestTable;
	}

	/**
	 * initializes a UpdateTable
	 * 
	 * @param vision
	 *            value of ants range of vision in a square format.
	 */
	public void setNorthTable(int vision) {
		int k = 0;
		int i = 0;
		int value = 0;
		int range = (int) Math.log(vision);
		NorthTable = new int[2 * range + 1][2];
		for (int j = range; j > 0; j--) {
			while (value < vision) {
				value = (int) Math.pow(j, 2) + (int) Math.pow(i, 2);
				i++;
			}
			NorthTable[k][0] = i;
			NorthTable[k][1] = j;
			k++;
			i = 0;
		}
		i = k;
		NorthTable[i][0] = range + 1;
		NorthTable[i][1] = 0;
		for (k += -1; k > 0; k--) {
			i++;
			NorthTable[i][0] = -1 * NorthTable[k][0];
			NorthTable[i][1] = NorthTable[k][1];
		}
		setEastTable();
		setSouthTable();
		setWestTable();
	}

	public void setEastTable() {
		EastTable = new int[NorthTable.length][NorthTable[0].length];
		for (int i = 0; i < NorthTable.length; i++) {
			EastTable[i][0] = NorthTable[i][1];
			EastTable[i][1] = NorthTable[i][0];
		}
	}

	public void setSouthTable() {
		SouthTable = new int[NorthTable.length][NorthTable[0].length];
		for (int i = 0; i < NorthTable.length; i++) {
			SouthTable[i][0] = NorthTable[i][0];
			SouthTable[i][1] = -1 * NorthTable[i][1];
		}
	}

	public void setWestTable() {
		WestTable = new int[NorthTable.length][NorthTable[0].length];
		for (int i = 0; i < NorthTable.length; i++) {
			WestTable[i][0] = -1 * NorthTable[i][1];
			WestTable[i][1] = NorthTable[i][0];
		}
	}
}
