package it.techgap.challenge.java.senior;

public class Challenge06Extra {
    public static String splitAndReverse(String i, int positions) {
    	char[] c = i.toCharArray();
    	char[] result = new char[i.length()];
    	int index = 0;
    	for (int x = i.length()-1; x >= positions; x--) {
    		result[index] = c[x];
    		index++;
    	}
    	for (int x = 0; x < positions; x++) {
    		result[index] = c[x];
    		index++;
    	}
        return new String(result);
    }
}
