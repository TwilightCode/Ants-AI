public class updateTable {
	private int[][] NorthTable;
	private int[][] EastTable;
	private int[][] SouthTable;
	private int[][] WestTable;

	/**
	 * 
	 * @param viewRange
	 *            value of ants range of vision in a square format.
	 */
	public updateTable(int viewRange) {
		setSouthTable(viewRange);
		setNorthTable();
		setEastTable();
		setWestTable();
	}

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
	 * sets new coordinate values to given updateTable
	 * 
	 * @param x
	 *            horizontal coordinate
	 * @param y
	 *            vertical coordinate
	 * @param table
	 *            is being updated
	 * @param number
	 *            row of table to be updated
	 */
	public void setMemberTable(int x, int y, int[][] table, int number) {
		table[number][0] = x;
		table[number][1] = y;
	}

	/**
	 * calculates coordinate that goes just beyond the view area on the south
	 * side
	 * 
	 * @param viewRange
	 *            given view in square format
	 * @param x
	 *            horizontal coordinate
	 * @return y vertical coordinate
	 */
	public int calculateVisibility(int viewRange, int x) {
		int y = 0;
		int value = 0;
		while (value < viewRange) {
			y++;
			value = ((int) Math.pow(x, 2) + (int) Math.pow(y, 2));
		}
		return y;
	}

	/**
	 * initializes the SouthTable.
	 * 
	 * @param vision
	 *            value of ants range of vision in a square format.
	 */
	public void setSouthTable(int vision) {
		int height = 0;
		int range = (int) Math.sqrt(vision);
		int width = (-1 - range);
		int max = (2 * range + 1);
		SouthTable = new int[max][2];
		for (int i = 0; i < max; i++) {
			width++;
			if (i < range) {
				height = calculateVisibility(vision, width);
			} else if (i == range) {
				height = (range + 1);
			} else {
				height = SouthTable[range - width][1];
			}
			setMemberTable(width, height, SouthTable, i);
		}
	}

	/**
	 * initializes the EastTable by using the SouthTable and simply switches the
	 * values to changes it to EastTable.
	 */
	public void setEastTable() {
		EastTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			setMemberTable(SouthTable[i][1], SouthTable[i][0], EastTable, i);
		}
	}

	/**
	 * initializes the NorthTable by using the SouthTable and multiplies the y
	 * column with -1 to get NorthTable.
	 */
	public void setNorthTable() {
		NorthTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			setMemberTable(SouthTable[i][0], -1 * SouthTable[i][1], NorthTable, i);
		}
	}

	/**
	 * initializes the WestTable by using the SouthTable and first switches y
	 * and x and then multiplies the x with -1 column to get WestTable.
	 */
	public void setWestTable() {
		WestTable = new int[SouthTable.length][SouthTable[0].length];
		for (int i = 0; i < SouthTable.length; i++) {
			setMemberTable(-1 * SouthTable[i][1], SouthTable[i][0], WestTable, i);
		}
	}
}
