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
	 * executes bots turn   
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
	 * checks the directions in which ant can move.
	 * 
	 * @param ants contains the tools from the game.
	 * @param myAnt contains the coordinates of the tile that's surroundings are to be evaluated.
	 * @return table contains all the directions that the ant can move to.  
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
	 *Picks a random direction
	 *
	 *@param table contains the directions from which one is to be chosen.
	 *@return direction random direction chosen from the given table.  
	 */

	private Aim getRandomDirection(ArrayList<Aim> table) {
		Random rand = new Random();
		return table.get(rand.nextInt(table.size()));
	}
}
