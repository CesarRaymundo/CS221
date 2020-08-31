
import java.util.Arrays;

/**
 * 
 * @author CesarRaymundo
 *
 */
public class MethodsDriver {

		//Driver class for MethodsToAnalyze that passes an array to allow the methods to 
	   //calculate total amount of statements within each method. 
	public static void main(String[] args) {

		for (int size = 1; size < 10; size += 1) {
			int[] array = ArrayOfInts.descendingArray(size);
			System.out.println("Before sort: " + Arrays.toString(array));
			// Commented out for testing methods using random array, but currently using descending
			//int[] array = ArrayOfInts.randomizedArray(size);
			
			MethodsToAnalyze.find(array, size);
			long statementFind = MethodsToAnalyze.getFindStatements();
			MethodsToAnalyze.replaceAll(array, size, 100);
			long statementReplace = MethodsToAnalyze.getStatements();
			MethodsToAnalyze.sortIt(array);
			long statementSort = MethodsToAnalyze.getSortStatements();

			System.out.println(Arrays.toString(array));
			

			System.out.println("for n= "+size+" took "+statementFind+" statments");
			System.out.println("for n= "+size+" took "+statementReplace+" statments");
			System.out.println("-----------");
			System.out.println("for n= "+size+" took "+statementSort+" statments");
			System.out.println("-----------");


		}

	}

}