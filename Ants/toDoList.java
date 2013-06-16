

public class toDoList {
	private Tile ant;
	private omaArrayList<Aim> path;
	private Tile goal;

	public toDoList(Tile ant, Tile goal, omaArrayList<Aim> path) {
		this.ant = ant;
		this.goal = goal;
		this.path = path;
	}

	public void setAnt(Tile ant) {
		this.ant = ant;
	}

	public void setPath(omaArrayList<Aim> path) {
		this.path = path;
	}

	public void setGoal(Tile goal) {
		this.goal = goal;
	}

	public Tile getAnt() {
		return ant;
	}

	public omaArrayList<Aim> getPath() {
		return path;
	}

	public Tile getGoal() {
		return goal;
	}

}
