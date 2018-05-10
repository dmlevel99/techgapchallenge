package it.techgap.challenge.java.senior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Challenge01CollectionMethods {
	
	public static int max(int[] in){
		Arrays.sort(in);
		return in[in.length-1];
	}
	
	public static int min(int[] in){
		Arrays.sort(in);
		return in[0];
	}
	
	public static int[] sortIt(int[] in){
		Arrays.sort(in);
		return in;
	}
	
	public static Boolean[] sortIt(Boolean[] in){
		Arrays.sort(in, Collections.reverseOrder());
		return in;
	}
	
	public static int[] removeNegative(int[] in){
		Arrays.sort(in);
		int index = 0;
		for (int i = 0; i < in.length; i++) {
			if (in[i] < 0) index++;
		}
		int[] result = new int[in.length-index];
		int resultIndex = 0;
		for (int i = index; i < in.length; i++) {
			result[resultIndex] = in[i];
			resultIndex++;
		}
		return result;
	}
	
	public static int count(String[] in, String e){
		int count = 0;
		for (int i = 0; i < in.length; i++) {
			if (in[i].equals(e)) count++;
		}
		return count;
	}
	
	public static int maxRepetitions(String[] in){
		Map<String, Integer> h = new HashMap<String, Integer>();
		for (int i = 0; i < in.length; i++) {
			if (h.containsKey(in[i])) {
				int v = h.get(in[i]); // implicit conversion
				h.put(in[i],  v+1);
			}
			else {
				h.put(in[i], 1);
			}
		}
		int max = 0;
		for (Integer value : h.values()) {
			if (value > max) max = value;
		}
		return max;
	}
	
	public static int[] mergeAndSort(int[]... in){
		List<Integer> l = new ArrayList<Integer>();
		for (int[] a : in) {
			for (int i = 0; i < a.length; i++) {
				Integer temp = Integer.valueOf(a[i]);
				if (!l.contains(temp)) l.add(temp); 
			}
		}
		int[] result = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			result[i] = l.get(i).intValue();
		}
		Arrays.sort(result);
		return result;
	}
}