import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Starter bot implementation.
 * Some methods in this class have been added or modified. 
 */
public class MyBot extends Bot {
	/**
	 * Main method executed by the game engine for starting the bot.
	 * 
	 * @param args
	 *            command line arguments
	 * 
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static void main(String[] args) throws IOException {
		new MyBot().readSystemInput();
	}

	/**
	 * This method has been modified Method checks valid directions, and gives
	 * each ant valid direction to move.
	 */
	@Override
	public void doTurn() {
		Ants ants = getAnts();
		for (Tile myAnt : ants.getMyAnts()) {
			ArrayList<Aim> table = movement(ants, myAnt);
			if (!table.isEmpty()) {
				Aim direction;
				if (table.size() == 1) {
					direction = table.get(0);
				} else {
					direction = getRandomDirection(table);
				}
				ants.issueOrder(myAnt, direction);
			}
		}
	}

	/**
	 * This method is new Method checks all possible directions and returns
	 * arraylist containing all valid directions
	 */
	private ArrayList<Aim> movement(Ants ants, Tile myAnt) {
		ArrayList<Aim> table = new ArrayList<Aim>();
		if (ants.getIlk(myAnt, Aim.NORTH).isPassable()) {
			table.add(Aim.NORTH);
		}
		if (ants.getIlk(myAnt, Aim.EAST).isPassable()) {
			table.add(Aim.EAST);
		}
		if (ants.getIlk(myAnt, Aim.SOUTH).isPassable()) {
			table.add(Aim.SOUTH);
		}
		if (ants.getIlk(myAnt, Aim.WEST).isPassable()) {
			table.add(Aim.WEST);
		}
		return table;
	}

	/**
	 * This method is new Method picks random direction from the given arraylist
	 * and returns it.
	 */

	private Aim getRandomDirection(ArrayList<Aim> table) {
		Random rand = new Random();
		return table.get(rand.nextInt(table.size()));
	}
}
