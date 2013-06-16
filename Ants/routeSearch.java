

public class routeSearch {

	private Tile[] searchTable;
	Ants game;
	public int[][] pathTable;

	public routeSearch(Ants game) {
		this.game = game;
	}

	public void buildSearchTable(int size) {
		Tile tile;
		searchTable = new Tile[size * 4];
		for (int i = (0 - size); i < 0; i++) {
			tile = new Tile(size + i, i);
			searchTable[i] = tile;
		}
		tile = new Tile(0, size);
		for (int i = 0; i < (searchTable.length / 2); i++) {
			tile = new Tile(0 - searchTable[i].getRow(), searchTable[i].getCol());
			searchTable[i] = tile;
		}
	}

	public int getSurroundingWalls(Tile tile) {
		Aim[] table = { Aim.NORTH, Aim.EAST, Aim.SOUTH, Aim.WEST };
		int walls = 0;
		for (int i = 0; i < table.length; i++) {
			if (game.getIlk(tile, table[i]) == Ilk.WATER) {
				walls++;
			}
		}
		return walls;
	}

	public int[][] getPathTable() {
		return pathTable;
	}

	public omaArrayList<Tile> getTilesOnOpenArea(Tile tile) {
		Tile newTile;
		omaArrayList<Tile> promising = new omaArrayList<Tile>();
		for (int i = 0; i < searchTable.length; i++) {
			int x = searchTable[i].getCol();
			int y = searchTable[i].getRow();
			newTile = setCoordinates(tile, x, y);
			if (getSurroundingWalls(newTile) > 1 || game.getIlk(newTile) == Ilk.WATER) {
				break;
			} else {
				promising.add(newTile);
			}
		}
		return promising;
	}

	/**
	 * places the given value to be within borders
	 * 
	 * @param value
	 *            given value
	 * @param max
	 *            is the limit of the given area
	 * @return value that is within given borders
	 */
	public int isOverBoard(int value, int max) {
		if (value >= max) {
			return value - max;
		} else if (value < 0) {
			return max + value;
		} else {
			return value;
		}
	}

	/**
	 * sets new coordinates so that they are within the limits of the table
	 * 
	 * @param tile
	 *            current location of the ant
	 * @param borderX
	 *            horizontal border of ants vision
	 * @param borderY
	 *            vertical border of ants vision
	 * @return tile that contains location just outside the ants vision range
	 */
	public Tile setCoordinates(Tile tile, int borderX, int borderY) {
		int mapHeight = game.getRows();
		int mapWidth = game.getCols();
		return new Tile(isOverBoard((tile.getRow() + borderY), mapHeight), isOverBoard((tile.getCol() + borderX), mapWidth));
	}

	public Aim getOppositeDirection(Aim direction) {
		if (direction == Aim.NORTH) {
			return Aim.SOUTH;
		} else if (direction == Aim.EAST) {
			return Aim.WEST;
		} else if (direction == Aim.SOUTH) {
			return Aim.NORTH;
		} else {
			return Aim.EAST;
		}
	}

	public omaArrayList<Aim> getShortestRoute(Tile endPoint) {
		int distance = pathTable[endPoint.getRow()][endPoint.getCol()];
		omaArrayList<Aim> path = new omaArrayList<Aim>();
		Aim[] table = { Aim.NORTH, Aim.EAST, Aim.SOUTH, Aim.WEST };
		Tile tile = endPoint;
		Tile newTile;
		while (distance > 0) {
			for (int i = 0; i < table.length; i++) {
				newTile = game.getTile(tile, table[i]);
				if (newTile.getRow() > -1 && newTile.getRow() < pathTable.length && newTile.getCol() > -1 && newTile.getCol() < pathTable[0].length) {
					if (pathTable[newTile.getRow()][newTile.getCol()] == (distance - 1)) {
						distance--;
						path.add(getOppositeDirection(table[i]));
						tile = newTile;
					}
				}
			}
		}
		return path;
	}


	public omaArrayList<Aim> initFoodSearch(int[][] pathTable) {
		this.pathTable = pathTable;
		Tile tile = search();
		if (tile == null) {
			return null;
		}
		return getShortestRoute(tile);
		}

	public Tile search() {
		omaArrayList<Tile> layer = new omaArrayList<Tile>();
		omaArrayList<Integer> distance2 = new omaArrayList<Integer>();
		Aim[] table = { Aim.NORTH, Aim.EAST, Aim.SOUTH, Aim.WEST };
		int max = pathTable.length;
		int distance = 1;
		int x;
		int y;
		boolean found = false;
		Tile tile = new Tile(pathTable.length/2, pathTable.length/2);
		Tile newTile;
		layer.add(tile);
		distance2.add(0);
		pathTable[tile.getRow()][tile.getCol()] = 0;
		while (!layer.isEmpty() && found != true) {
			tile = layer.get(0);
			layer.remove(0);
			distance = distance2.get(0);
			distance2.remove(0);
			for (int j = 0; j < table.length; j++) {
				newTile = game.getTile(tile, table[j]);
				x = newTile.getCol();
				y = newTile.getRow();
				if (x >= 0 && x < max && y >= 0 && y < max && pathTable[y][x] <= -2) {
					if (pathTable[y][x] == -2) {
						layer.add(newTile);
						distance2.add(distance + 1);
						pathTable[y][x] = distance + 1;
					} else if (pathTable[y][x] == -3) {
						pathTable[y][x] = distance + 1;
						return new Tile(y, x);
					}
				}
			}
			distance++;
		}
		return null;
	}

}
