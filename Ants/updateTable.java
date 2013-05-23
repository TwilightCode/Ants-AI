public class updateTable {
	public int[][] NorthTable;
	public int[][] EastTable;
	public int[][] SouthTable;
	public int[][] WestTable;

	/**
	 * 
	 * @return NorthTable contains locations of all the tiles that need to be
	 *         updated when ants moves to the North
	 */
	public int[][] getNorthTable() {
		return NorthTable;
	}
	/**
	 * 
	 * @return SouthTable contains locations of all the tiles that need to be
	 *         updated when ants moves to the South
	 */
	public int[][] getSouthTable() {
		return SouthTable;
	}
	/**
	 * 
	 * @return EastTable contains locations of all the tiles that need to be
	 *         updated when ants moves to the East
	 */
	public int[][] getEastTable() {
		return EastTable;
	}
	/**
	 * 
	 * @return WestTable contains locations of all the tiles that need to be
	 *         updated when ants moves to the West
	 */
	public int[][] getWestTable() {
		return WestTable;
	}

	/**
	 * checks ants movement direction and returns the table containing the
	 * locations of tiles that need to be updated.
	 * 
	 * @param direction
	 *            where ant is moving
	 * @return table that matches the movement direction
	 */
	public int[][] getUpdateTable(Aim direction) {
		if (direction == Aim.NORTH) {
			return getNorthTable();
		} else if (direction == Aim.EAST) {
			return getEastTable();
		} else if (direction == Aim.SOUTH) {
			return getSouthTable();
		} else {
			return getWestTable();
		}
	}
	/**
	 * initializes all the update Tables
	 * 
	 * @param vision
	 *            value of ants range of vision in a square format.
	 */
	public void setTables(int vision) {
		int k = -1;
		int i = -1;
		int value = 0;
		int range = (int) Math.sqrt(vision);
		SouthTable = new int[2 * range + 1][2];
		for (int j = range; j > 0; j--) {
			while (value < vision) {
				i++;
				value = (int) Math.pow(j, 2) + (int) Math.pow(i, 2);
				}
			k++;
			SouthTable[k][0] = j;
			SouthTable[k][1] = i;
			i = 0;
			value = 0;
		}
		i = range;
		SouthTable[k+1][0] = 0;
		SouthTable[k+1][1] = range + 1;
		for (;k >= 0; k--) {
			i++;
			SouthTable[i][0] = -1 * SouthTable[k][0];
			SouthTable[i][1] = SouthTable[k][1];
		}
		setEastTable();
		setNorthTable();
		setWestTable();
	}

	/**
	 * initializes the EastTable by using the NorthTable and simply switches the
	 * values to changes it to EastTable.
	 */
	public void setEastTable() {
		EastTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			EastTable[i][0] = SouthTable[i][1];
			EastTable[i][1] = SouthTable[i][0];
		}
	}
	/**
	 * initializes the SouthTable by using the NorthTable and multiplies the y
	 * column with -1 to get SouthTable.
	 */
	public void setNorthTable() {
		NorthTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			NorthTable[i][0] = SouthTable[i][0];
			NorthTable[i][1] = -1 * SouthTable[i][1];
		}
	}
	/**
	 * initializes the WestTable by using the NorthTable and first switches y
	 * and x and then multiplies the x with -1 column to get WestTable.
	 */
	public void setWestTable() {
		WestTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			WestTable[i][0] = -1 * SouthTable[i][1];
			WestTable[i][1] = SouthTable[i][0];
		}
	}
}
