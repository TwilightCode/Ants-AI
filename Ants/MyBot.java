import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Starter bot implementation. Some methods in this class have been added or
 * modified.
 */
public class MyBot extends Bot {
	updateTable update;
	unchartedTable uncharted;
	Ants ants;
	private int antsSet;
	private int turn;
	private int help;

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

	
	
	public void updateState(){
		help = turn % 5;
		if (help == 0) {
			uncharted.raiseUncharted();
		}
	}
	
	public void setNewAnt(Tile tile){
		uncharted.setViewArea(tile);
		}
	
	/**
	 * executes bots turn
	 */
	@Override
	public void doTurn() {
		int antNumber = 0;
		help = 0;
		Aim direction;
		ants = getAnts();
		update = new updateTable(ants.getViewRadius2());
		uncharted = new unchartedTable(update, ants);
		updateState();
		for (Tile myAnt : ants.getMyAnts()) {
			setNewAnt(myAnt);	
			direction = uncharted.getBestDirection(myAnt);
			if(direction != null){
			ants.issueOrder(myAnt, direction);
			}
		}
	}
	
}
