import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Cesar Raymundo
 * 
 *         CS221-1 HW2 Exceptions
 *
 */
public class FormatChecker {
	public static void main(String[] args) {
		// Checks to see if there's any input from user
		if (args.length == 0) {
			System.out.println("Please input a file name");
		}
		// A for loop for each file input in the command line argument
		for (int i = 0; i < args.length; i++) {
			int numRows = 0;
			int numCols = 0;
			int columnIncrementer = 0;
			boolean valid = true;
			double[][] sizeCheck;
			Scanner fileScan;
			fileScan = null;
			String input = args[i];
			File file = new File(input);

			try {
				fileScan = new Scanner(file);
			} catch (FileNotFoundException e) {
				System.out.println(e.toString());
				valid = false;
			}

			try {
				String rowCol = fileScan.nextLine();
				String[] rowColDim = rowCol.trim().split("\\s+");
				// Checking to see if the first line in the file has only 2 numbers
				// for the dimensions of the 2D array
				if (rowColDim.length != 2) {
					System.out.println(file + " has incorrect dimensions. ");
					valid = false;
				}
				// Assigns the dimensions for the 2D array
				numRows = Integer.parseInt(rowColDim[0]);
				numCols = Integer.parseInt(rowColDim[1]);
			} catch (NumberFormatException e) {
				System.out.println(file + "\n" + e.toString() + " isn't an int for 2D Array dimensions.");
				valid = false;
			}
			sizeCheck = new double[numRows][numCols];

			// while loop checking to see if the file has become false, or if the file has
			// more lines to read
			while (fileScan.hasNextLine() && valid != false) {
				try {
					String numberLine = fileScan.nextLine();
					String[] numbers = numberLine.trim().split("\\s+");

					// Ensures that the line of numbers has at least 1 integer or double(depending
					// on file) in it
					if (numbers != null && numbers.length > 1) {

						for (int numberSpot = 0; numberSpot < numbers.length; numberSpot++) {
							sizeCheck[columnIncrementer][numberSpot] = Double.parseDouble(numbers[numberSpot]);

						}
						columnIncrementer++;
					}

					// Checking if the rows size matches the row dimension in the 2D Array
					if (sizeCheck.length != numRows) {
						valid = false;

					}
					// Checking if column size matches the column dimension in the 2D Array
					for (int columnChecker = 0; columnChecker < numRows; columnChecker++) {
						if (sizeCheck[columnChecker].length != numCols) {

							valid = false;
						}

					}
				}
				// Catches for exceptions that would result in invalid files
				catch (ArrayIndexOutOfBoundsException e) {
					System.out.println(file + "\n" + e.toString()
							+ " does not have rows/columns matching to inputed row/column size.");
					valid = false;

				} catch (NumberFormatException e) {
					System.out.println(file + "\n" + e.toString() + " isn't an int or double in 2D Array.");
					valid = false;

				}

			}
			fileScan.close();
			// if statements printing out INVALID or Valid.
			// Depending if the file/s have errors within it
			if (valid == false) {
				System.out.println("INVALID");
			} else if (valid == true) {
				System.out.println(file);
				System.out.println("Valid");
			}
			System.out.println();

		}

	}

}

