package it.techgap.challenge.java.senior;

import org.junit.Assert;
import org.junit.Test;

public class FlattenerTest {
	@Test
	public void testFlattener() {
		Object[] z = {new Object[] {1, 2, new Object[] {7}},4};
		Object[] expected = {1,2,7,4};
		
		Object[] result = Flattener.makeFlat(z);

		Assert.assertArrayEquals(expected,result);
		
		Object[] z2 = {new Object[] {new Object[] {new Object[] {5}}}};
		Object[] expected2 = {5};
		
		Object[] result2 = Flattener.makeFlat(z2);
		
		Assert.assertArrayEquals(expected2, result2);
		
		Object[] z3 = {};
		Object[] expected3 = {};
		
		Object[] result3 = Flattener.makeFlat(z3);
		
		Assert.assertArrayEquals(expected3, result3);
		
		Object[] z4 = {1,2,3};
		Object[] expected4 = {1,2,3};
		
		Object[] result4 = Flattener.makeFlat(z4);
		
		Assert.assertArrayEquals(expected4, result4);

	}
}
