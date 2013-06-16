public class omaArrayList<E> {
	Object[] table;
	int amount = 0;

	public omaArrayList() {
		table = new Object[10];
	}

	public void add(E item) {
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
		Object[] newTable = new Object[(int) Math.pow(table.length, 2)];
		for (int i = 0; i < table.length; i++) {
			newTable[i] = table[i];
		}
		table = newTable;
	}

	@SuppressWarnings("unchecked")
	public E get(int place) {
		return (E)table[place];
	}

	public int getSize() {
		return amount;
	}
	
	public int size() {
		return getSize();
	}
	
	public boolean isEmpty(){
		if (amount == 0){
			return true;
		}else {
			return false;
		}
	}
	
	public void clear(){
		amount = 0;
		table = new Object[10];
		
	}
}
