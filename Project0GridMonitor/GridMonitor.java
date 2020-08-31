import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author cesarraymundo
 *
 */
public class GridMonitor implements GridMonitorInterface {

	private String testFile;
	private int numOfRows;
	private int numOfCols;
	private double[][] baseGrid;
	private double[][] surroundingSumGrid;
	private double[][] surroundingAvgGrid;
	private double[][] deltaGrid;
	private boolean[][] dangerGrid;

	/**
	 * The constructor of the GridMonitor. Uses the file entered within 
	 * the constructor to make a 2d array object.
	 * @param testFile
	 * @throws FileNotFoundException
	 */
	public GridMonitor(String testFile) throws FileNotFoundException {
		this.testFile = testFile;
		File gridFile = new File(testFile);
		// Reads the first line as integers and uses it for the dimensions of the 2d
		// array
		if (gridFile.exists() && gridFile.isFile()) {
			Scanner fileIterator = new Scanner(gridFile);
			numOfRows = fileIterator.nextInt();
			numOfCols = fileIterator.nextInt();
			baseGrid = new double[numOfRows][numOfCols];

			// Creates the 2d array
			for (int row = 0; row < numOfRows; row++) {
				for (int col = 0; col < numOfCols; col++) {
					this.baseGrid[row][col] = fileIterator.nextDouble();
				}
			}
			fileIterator.close();
		} else {
			throw new FileNotFoundException();
		}
	}

	@Override
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element is
	 * the sum of the 4 surrounding base elements. For elements on a grid border,
	 * the base element's own value is used when looking out of bounds, as if there
	 * is a mirror surrounding the grid. This method is exposed for testing.
	 * 
	 * @return grid containing the sum of adjacent positions
	 */
	public double[][] getBaseGrid() {
		// Making a copy of the base grid for encapsulation
		double[][] copyBaseGrid = new double[numOfRows][numOfCols];
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				copyBaseGrid[row][col] = baseGrid[row][col];
			}
		}

		return copyBaseGrid;
	}

	@Override
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the sum of the 4 surrounding base elements. For elements on a grid border,
	 * the base element's own value is used when looking out of bounds, as if there
	 * is a mirror surrounding the grid. This method is exposed for testing.
	 * 
	 * @return grid containing the sum of adjacent positions
	 */
	public double[][] getSurroundingSumGrid() {
		surroundingSumGrid = new double[numOfRows][numOfCols];
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				surroundingSumGrid[row][col] = baseGrid[row][col];
			}
		}
		//checks for the location the cell to decide if it needs to be added to itself and by surrounding cells.
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				double sum = 0;
				if (row + 1 < numOfRows) {
					sum += baseGrid[row + 1][col];
				} else {
					sum += baseGrid[row][col];
				}
				if (row - 1 >= 0) {
					sum += baseGrid[row - 1][col];
				} else {
					sum += baseGrid[row][col];
				}
				if (col + 1 < numOfCols) {
					sum += baseGrid[row][col + 1];
				} else {
					sum += baseGrid[row][col];
				}
				if (col - 1 >= 0) {
					sum += baseGrid[row][col - 1];
				} else {
					sum += baseGrid[row][col];
				}

				surroundingSumGrid[row][col] = sum;
			}
		}

		return surroundingSumGrid;
	}

	@Override
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is the average of the 4 surrounding base elements. This method is exposed for 
	 * testing.
	 * 
	 * @return grid containing the average of adjacent positions
	 */
	public double[][] getSurroundingAvgGrid() {
		//Assigning the surrounding sum average by calling the getSurroundingSumGrid() method
		surroundingAvgGrid = getSurroundingSumGrid();
		//Dividing the array by 4 to get the average
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				surroundingAvgGrid[row][col] = surroundingAvgGrid[row][col] / 4.0;
			}
		}

		return surroundingAvgGrid;
	}

	@Override
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element 
	 * is the maximum delta from the average. For example, if the surrounding
	 * average at some coordinate is 2.0 and the maximum delta is 50% of the average,
	 * the delta value at the same coordinate in this grid will be 1.0. This method is
	 * exposed for testing.
	 * 
	 * @return grid containing the maximum delta from average of adjacent positions
	 */
	public double[][] getDeltaGrid() {
		//Assigning the deltaGrid array by calling the getSurroundingAvgGrid() method
		deltaGrid = getSurroundingAvgGrid();
		//Dividing the array by two to get the maximum delta
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				deltaGrid[row][col] = deltaGrid[row][col] / 2.0;
				//If the result is a negative, the array is multiplied by -1 to get a positive
				if (deltaGrid[row][col] < 0) {
					deltaGrid[row][col] = deltaGrid[row][col] * -1;
				}
			}
		}
		return deltaGrid;
	}

	@Override
	/**
	 * Returns a grid with the same dimensions as the base grid, but each element
	 * is a boolean value indicating if the cell is at risk. For example, if the cell
	 * at a coordinate is less than the surrounding average minus its maximum delta or
	 * greater than the surrounding average plus its maximum delta, the corresponding
	 * coordinate in this grid will be true. If the base cell value is within the safe
	 * range, however, the corresponding value in this grid will be false.
	 * 
	 * @return grid containing boolean values indicating whether the cell at this
	 * location is in danger of exploding
	 */
	public boolean[][] getDangerGrid() {
		dangerGrid = new boolean[numOfRows][numOfCols];
		double deltaGrid[][] = getDeltaGrid();
		double surAvgGrid[][] = getSurroundingAvgGrid();
		for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				if (baseGrid[row][col] < surAvgGrid[row][col] - deltaGrid[row][col]) {
					dangerGrid[row][col] = true;
				} else if (baseGrid[row][col] > surAvgGrid[row][col] + deltaGrid[row][col]) {
					dangerGrid[row][col] = true;
				} else {
					dangerGrid[row][col] = false;
				}
			}
		}

		return dangerGrid;
	}
	/**
	 *Returns a String containing information of the current base grid and the danger grid
	 *@return String containing the attributes of the Grid Monitor
	 */
	public String toString() {
		String output = "Base Grid: "+"\n"; 
			for (int row = 0; row < numOfRows; row++) {
			for (int col = 0; col < numOfCols; col++) {
				output+= baseGrid[row][col]+" ";	
			}
			output += "\n";
		}
		
		return output;
	}

}
