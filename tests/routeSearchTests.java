import static org.junit.Assert.*;



import org.junit.Before;
import org.junit.Test;

public class routeSearchTests {
	routeSearch route;
	int[][] pathTable = { { -2, -2, -1, -2, -2, -2, -2, -2, -2 }, { -2, -2, -1, -2, -2, -2, -2, -2, -2 }, { -2, -2, -1, -2, -2, -2, -2, -2, -3 },
			{ -2, -2, -1, -2, -2, -2, -2, -2, -2 }, { -2, -2, -1, -2, -2, -2, -2, -2, -2 }, { -2, -1, -1, -2, -2, -2, -2, -2, -2 },
			{ -2, -2, -2, -2, -2, -2, -2, -2, -2 }, { -2, -2, -2, -2, -2, -2, -2, -2, -2 }, { -2, -2, -2, -2, -2, -2, -2, -2, -2 } };

	int[][] pathTable2 ={ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}, {-1,-1,-1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-2}, {   -1,-1,-1,-1,-1,-1,-2,-2,-2,-2,-2,-2,-2},
			{ -1,-1,-1,-1,-1,-2,-2,-2,-2,-2,-2,-2,-2 }, { -1,-1,-1,-1,-2,-2,-2,-2,-1,-1,-1,-2,-2}, { -1,-1,-1,-1,-1,-2,-2,-2,-1,-1,-2,-2,-2 },
			{-1,-1,-1,-2,-2,-2,-2,-2,-2,-1,-2,-2,-2}, { -1,-1,-1,-2,-2,-2,-2,-2,-1,-1,-2,-2,-2 }, {-1,-1,-1,-2,-2,-2,-2,-2,-2,-1,-2,-2,-2}, {  -1,-1,-1,-2,-2,-2,-2,-2,-2,-1,-1,-3,-2 }, {  -1,-1,-1,-1,-1,-2,-2,-2,-2,-1,-1,-1,-2 }, { -1,-1,-1,-1,-1,-2,-2,-2,-1,-1,-1,-1,-1 }, {  -1,-1,-1,-1,-2,-2,-2,-2,-1,-2,-1,-2,-2 } };
		
		
	@Before
	public void setUpTests() {
		Ants game = new Ants(100, 100, 10, 10, 10, 55, 25, 55);
		route = new routeSearch(game);
		route.pathTable = pathTable;
	}

	@Test
	public void search() {
		int[][] testTable = pathTable2;
		route.search();
		for (int i = 0; i < pathTable2.length; i++) {
			for (int j = 0; j < pathTable2[0].length; j++) {
				System.out.print(testTable[i][j]+ " \t ");
			}
		System.out.println();
		}
	}
	
	@Test
	public void searchFoodPicker(){
		int[][] pathTable = pathTable2; 
		omaArrayList<Aim> path = route.initFoodSearch(pathTable);
		for(int i = 0; i < path.size(); i++){
			
		System.out.println(path.get(i));
		}		
	}
	
	@Test
	public void getPath() {
		route.search();
		Tile tile = new Tile(0,0);
		omaArrayList<Aim> path = route.getShortestRoute(tile);
		for (int i = 0; i< path.size();i++){
		//	System.out.println(path.get(i));
		}
	}
	
	@Test
	public void getFoodTile() {
		int[][] testTable = route.getPathTable();
		//System.out.println(tile.getRow() + ":" + tile.getCol());
		for (int i = 0; i < pathTable.length; i++) {
			for (int j = 0; j < pathTable[0].length; j++) {
			//	System.out.print(testTable[i][j]+ " \t ");
			}
			//System.out.println();
		}
	}
}
