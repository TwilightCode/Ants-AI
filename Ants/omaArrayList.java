public class omaArrayList {
	int[] table;
	int amount = 0;

	public omaArrayList(int type) {
		table = new int[10];
	}

	public void add(int item) {
		if (amount == table.length) {
			setSize();
		}
		table[amount] = item;
		amount++;

	}

	// this shouldn't be after every removal but due to time limit is forced
	// choice
	public void remove(int place) {
		for (int i = place; i < table.length - 1; i++) {
			table[i] = table[i + 1];
		}
		amount--;
	}

	public void setSize() {
		int[] newTable = new int[(int) Math.pow(table.length, 2)];
		for (int i = 0; i < table.length; i++) {
			newTable[i] = table[i];
		}
		table = newTable;
	}

	public int get(int place) {
		return table[place];
	}

	public int getSize() {
		return amount;
	}
}
