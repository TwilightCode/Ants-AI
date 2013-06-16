import java.util.ArrayList;

public class toDoList {
	private Tile ant;
	private ArrayList<Aim> path;
	private Tile goal;

	public toDoList(Tile ant, Tile goal, ArrayList<Aim> path) {
		this.ant = ant;
		this.goal = goal;
		this.path = path;
	}

	public void setAnt(Tile ant) {
		this.ant = ant;
	}

	public void setPath(ArrayList<Aim> path) {
		this.path = path;
	}

	public void setGoal(Tile goal) {
		this.goal = goal;
	}

	public Tile getAnt() {
		return ant;
	}

	public ArrayList<Aim> getPath() {
		return path;
	}

	public Tile getGoal() {
		return goal;
	}

}
