/**
 * A collection of methods that work with arrays of ints.
 * 
 * @author mvail
 */
public class MethodsToAnalyze {

	private static long numStatements;
	private static long findStatements;
	private static long sortStatements;
	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	public static int find(int[] array, int value) {
		// 2 for initializing i, and for condition check
		findStatements = 2; 
		for (int i = 0; i < array.length; i++) {
			 //one for incrementing i, one for condition check, one for checking if statement
			findStatements += 3;
			if (array[i] == value) {
				//return statements don't count
				return i;
			}
		}
		//does not add to statement as it is part of the cost of a method
		return -1; 
	}

	/**
	 * Replace all occurrences of oldValue with newValue in array.
	 * @param array ints where oldValue may be found
	 * @param oldValue value to replace
	 * @param newValue new value
	 */
	public static void replaceAll(int[] array, int oldValue, int newValue) {
		//1 for initializing index, 1 for the while loop condition, and findStatements is however many statements were in find
		numStatements = 2 + findStatements; 
		int index = find(array, oldValue);
		while (index > -1) {
			 //1 for assigning a value to the index in the array, 1 for the while loop condition check, 1 for assigning a value to index,
			 //	and however many statements in find
			numStatements += 3 + findStatements;
			array[index] = newValue;
			index = find(array, oldValue);
		}
		
	}
	
	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	public static void sortIt(int[] array) {
		//one for initializing next, and one for condition
		sortStatements = 2; 
		for (int next = 1; next < array.length; next++) {
			//one for incrementing next, 2 for assigning value and index, two for condition checks, and one for assigning array[index] to value
			sortStatements += 6; 
			int value = array[next];
			int index = next;
			while (index > 0 && value < array[index - 1]) {
				//one for condition check, one for array[index] = array[index -1], and one for decreasing index
				sortStatements += 3; 
				array[index] = array[index - 1];
				index--;
			}
			array[index] = value;
		}
	}
	
	//Gets the total amount of statements in a method and returns it
	public static long getStatements() {
		return numStatements;
	}
	public static long getFindStatements() {
	
		return findStatements;
	}
	public static long getSortStatements() {
		return sortStatements;
	}
}