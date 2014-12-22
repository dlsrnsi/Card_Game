package Game.Game_Data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HandEvaluatorTest extends HandEvaluator{

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testDefineHand() {
		int[] arr1 = {8, 9, 10, 11, 23, 34, 35};
		int[] arr2 = {2, 14, 26, 3, 6, 9, 33};
		int rank1 = defineHand(arr1);
		int rank2 = defineHand(arr2);
		System.out.println(rank1 + "°ú " + rank2);
		boolean rank = rank1<rank2;
	}

}
