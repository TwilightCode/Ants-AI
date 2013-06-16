import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class listTest {
	omaArrayList<Integer> testList;

	@Before
	public void initTests() {
		testList = new omaArrayList<Integer>();

	}

	@Test
	public void oneValue() {
		testList.add(2);
		System.out.println(testList.get(0));
	}

	@Test
	public void hundredFirst() {
		for (int i = 0; i < 101; i++) {
			testList.add(i);
		}
		for (int i = 0; i < 101; i++) {
			System.out.println(testList.get(i));
		}
	}
	
	@Test
	public void remove(){
		testList.add(2);
		System.out.println(testList.isEmpty());
		System.out.println(testList.get(0));
		testList.remove(0);
		System.out.println(testList.isEmpty());
	}

	@Test
	public void getSize(){
		testList.add(2);
		System.out.println(testList.getSize());
		testList.add(2);
		System.out.println(testList.getSize());
		testList.add(2);
		System.out.println(testList.getSize());
	}
	
	@Test
	public void arrayCorrectAfterRemove(){
		testList.add(1);
		testList.add(2);
		testList.add(3);
		testList.add(4);
		testList.remove(0);
		testList.remove(0);
		System.out.println("--");
		System.out.println(testList.get(0));
		System.out.println(testList.get(1));
		System.out.println("--");
	}
	
}
