package it.techgap.challenge.java.senior;

import java.util.Arrays;
import java.util.List;

public class Challenge00BaseMethods {
	
	public static int round(double d) {
		return (int)Math.round(d);
	}
	
	public static int round(String d) {
		Double result;
		try {
			int i = Integer.parseInt(d);
			return i;
		} catch(Exception e) {
			result = Double.parseDouble(d);
		}
		return (int)Math.round(result);
	}
	
	public static int bitNeeded(int i) {
		if (i == 0) return 0;
		return Integer.SIZE-Integer.numberOfLeadingZeros(i);
	}
	
	public static boolean palindromic(int num){
		if (num == 0) return true;
		int workingNum = num;
		int length = (int) (Math.log10(num)+1);
		int reversedNumber = 0;
		for (int i = 0; i < length; i++) {
			int chop = Math.floorDiv(workingNum,(int)Math.pow(10,(length-(i+1))));
			workingNum -= chop*(Math.pow(10,length-(i+1)));
			reversedNumber += chop*(Math.pow(10,i));
		}
		if (reversedNumber == num) return true;
		return false;
	}
	
	public static int hex(String i){
		return Integer.parseInt(i, 16);
	}
	
	public static String swapXY(String i){
		char[] c = i.toCharArray();
		for (int x = 0; x < i.length(); x++) {
			if (i.charAt(x) == 'x' || i.charAt(x) == 'X') c[x] = 'y'; 
			else if (i.charAt(x) == 'y' || i.charAt(x) == 'Y') c[x] = 'x';
		}
		return new String(c);
	}
	
	public static Number[] findNumbers(String i){
		i = i.replaceAll("[^.0-9]+", ":");
		List<String> l = Arrays.asList((i.trim().split(":")));
		int count = 0;
		for (int x = 0; x < l.size(); x++) {
			if (l.get(x).matches(".*[0-9].*")) {
				count++;
			}
		}
		Number[] n = new Number[count];
		int index = 0;
		for (int x = 0; x < l.size(); x++) {
			if (l.get(x).matches(".*[0-9].*")) {
				try {
					n[index] = Integer.parseInt(l.get(x));
					index++;
				} catch(Exception e) {
					n[index] = Double.parseDouble(l.get(x));
					index++;
				}
			}
		}
		return n;
	}
}