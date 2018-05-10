package it.techgap.challenge.java.senior;

import java.util.Arrays;
import java.util.stream.Stream;

public class Flattener {
	public static Object[] makeFlat(Object[] a) {
		return flatten(a).toArray();
	}
	
	private static Stream<Object> flatten(Object[] a) {
		return Arrays.stream(a).flatMap(o -> o instanceof Object[]? flatten((Object[])o): Stream.of(o));
	}
}
