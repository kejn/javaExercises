package com.capgemini.pascalrectangle;

/**
 * Created by ldrygala on 2015-01-23.
 * 0                     1
 * 1                   1   1
 * 2                 1   2   1
 * 3               1   3   3   1
 * 4             1   4   6   4   1
 * 5           1   5   10  10   5   1
 * 6         1   6   15  20  15   6   1
 * 7       1   7   21  35  35   21  7   1
 * 8     1   8   28  56  70  56   28  8   1
 * 9   1   9  36   84  126 126  84  36  9   1
 */
public class PascalRectangle {
	private final int maxPrintLevel;
	
	/**
	 * !!! If you won't {@link #printTriangle() print} the triangle you can pass any integer
	 * as a parameter. 
	 * @param maxLevelToPrint Used later in {@link #printTriangle() print} method to limit
	 * the height of printed Pascal Triangle  
	 */
	public PascalRectangle(int maxLevelToPrint) {
		maxPrintLevel = maxLevelToPrint;
	}
	
	/**
	 * @param c Column in Pascal Triangle counting from 0.
	 * @param r Row in Pascal Triangle counting from 0.
	 * @return
	 * 		<b>1</b> for left/right edge (first/last) elements</p>
	 * 		<b>2</b> for element in 3<sup>rd</sup> row and 2<sup>nd</sup> column (counting from 1)</p>
	 * 		Sum of two elements from a row above given position (counting from 0) in Pascal Triangle. 
	 */
    public long pascal(int c, int r) {
    	if(c == 0 || c == r) {
    		return 1;
    	} else if(c == 1 && r == 2) {
    		return 2;
    	}
    	return pascal(c-1,r-1) + pascal(c,r-1);
    }
    
    /**
     * Prints Pascal Triangle to console.
     */
    private void printTriangle() {
    	Integer strLenPlusSpace = getMaxResultLengthToDisplay();
    	for(int i=0; i < maxPrintLevel; ++i) {
    		for(int j=i; j < maxPrintLevel; ++j) {
    			System.out.print(String.format("%"+strLenPlusSpace/2+"s", new String()));
    		}
    		for(int j=0; j < i+1; ++j) {
    			System.out.print(String.format("%"+strLenPlusSpace+"s", pascal(j, i)));
    		}
    		System.out.println("");
    	}
    }
    
    /**
     * @return Value describing proper format to nicely print the Pascal Triangle.
     */
    private Integer getMaxResultLengthToDisplay() {
    	Integer result = Integer.toString((int)pascal(maxPrintLevel/2,maxPrintLevel)).length(); 
		return  (result % 2 == 1)  ? result + 1 : result + 2; 
	}

	public static void main(String[] args) {
    	PascalRectangle pr = new PascalRectangle(12);
    	pr.printTriangle();
    }
}
