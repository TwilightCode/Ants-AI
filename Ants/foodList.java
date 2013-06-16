

public class foodList {
	Ants game;
	private omaArrayList<toDoList> foodSearch;

	public foodList(Ants game) {
		this.game = game;
		foodSearch = new omaArrayList<toDoList>();
	}

	public void addPath(Tile ant, Tile goal, omaArrayList<Aim> path) {
		toDoList route = new toDoList(ant, goal, path);
		foodSearch.add(route);
	}

	public boolean foodIsFetched(Tile food) {
		for (int i = 0; i < foodSearch.size(); i++) {
			if (foodSearch.get(i).getGoal().getRow() == food.getRow() && foodSearch.get(i).getGoal().getCol() == food.getCol()) {
				return true;
			}
		}
		return false;
	}

	public boolean antHasJob(Tile ant) {
		for (int i = 0; i < foodSearch.size(); i++) {
			if (foodSearch.get(i).getAnt().getRow() == ant.getRow() && foodSearch.get(i).getAnt().getCol() == ant.getCol()) {
				return true;
			}
		}
		return false;
	}

	public void jobDone(Tile ant) {
		for (int i = 0; i < foodSearch.size(); i++) {
			if (foodSearch.get(i).getAnt().getRow() == ant.getRow() && foodSearch.get(i).getAnt().getCol() == ant.getCol()) {
				foodSearch.remove(i);
			}
		}
	}

	public Aim moveAnt(Tile ant) {
		Aim direction = null;
		for (int i = 0; i < foodSearch.size(); i++) {
			toDoList food = foodSearch.get(i);
			if (food.getAnt().getRow() == ant.getRow() && food.getAnt().getCol() == ant.getCol()) {
				food.setAnt(game.getTile(food.getAnt(), food.getPath().get(0)));
				direction = food.getPath().get(0);
				food.getPath().remove(0);
				if (food.getPath().isEmpty()) {
					jobDone(ant);
				}
				return direction;

			}
		}
		return direction;
	}
}
