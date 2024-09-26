public class Functions {
    
    //Method that checks win state given a gameboard and the hiddenBoard of that game
    public static boolean checkWin(int[][] board, int[][] hiddenBoard) {
        
        //At the index of every square in the board:
        for (int row = 0; row < board.length; row++){
            
            for (int col = 0; col < board[row].length; col ++){
                
                //If the board is not yet revealed at that location, and the location is not a hidden mine:
                if ((board[row][col] == -2) && (hiddenBoard[row][col] != -1)){
                    //Win is false
                    return false;
                }
            }
        }
        //Otherwise, that means the board is fully revealed excepting mines and the win is true
        return true;
    }
    
    
    //Method that reveals all empty locations possible around a given location based on minesweeper's rules. This is the fundamental game algorithm
    public static int[][] revealArea(int[][] revealed, int[][] hidden, int row, int col){
        //Initialize arrays for storing future location checks, and fill with null (-1) locations
        int[] savedRow = new int[100000000];
        int[] savedCol = new int[100000000];
        
        for (int i = 0; i < savedRow.length; i++){
            savedRow[i] = -1;
            savedCol[i] = -1;
        }
        //Index of last saved location variable
        int savedIndex = 1;
        
        //Initialize arrays for storing past location checks, and fill with null (-1) locations
        int[] doneRow = new int[1000];
        int[] doneCol = new int[1000];
        
        for (int i = 0; i < doneRow.length; i++){
            doneRow[i] = -1;
            doneCol[i] = -1;
        }
        
        //The starting row and col is the first location that needs to be checked
        savedRow[savedIndex] = row;
        savedCol[savedIndex] = col;
        
        //Index of last done location variable
        int doneIndex = 0;
        
        //While there are still locations to be checked
        while (savedIndex > 0){
            
            //Set row and col to the saved location at the end of the saved arrays
            row = savedRow[savedIndex];
            col = savedCol[savedIndex];
            //System.out.println("Applying index " + savedIndex + " Row: " + row + " Col: " + col);
            
            //If this location hasn't already been 'done':
            if (!isSaved(row, doneRow, col, doneCol)){
                //System.out.println("reached");
                
                //Add it to the done arrays
                doneRow[doneIndex] = row;
                doneCol[doneIndex] = col;
                
                //System.out.println("Done index: " + doneIndex + " Row: " + row + " Col: " + col);
                doneIndex ++;
                
                //Large code section below is for debugging (ignore)
                
                //System.out.print("savedRow: ");
                
                
                //for (int i = 0; i <= savedIndex; i++){
                    //System.out.print(savedRow[i] + " ");
                //}
                //System.out.println();
                
                //System.out.print("savedCol: ");
                //for (int i = 0; i <= savedIndex; i++){
                    //System.out.print(savedCol[i] + " ");
                //}
                //System.out.println();
                
                //System.out.print("doneRow: ");
                //for (int i = 0; i <= doneIndex; i++){
                    //System.out.print(doneRow[i] + " ");
                //}
                //System.out.println();
                
                //System.out.print("doneCol: ");
                //for (int i = 0; i <= doneIndex; i++){
                    //System.out.print(doneCol[i] + " ");
                //}
                //System.out.println();
            
            
            //If this location is valid then reveal it
            if (isValid(hidden, row, col)){
                revealed[row][col] = hidden[row][col];
                
            }
            
            //All the following code adds the surrounding 8 squares of the location tot he saved location arrays (as long as they're not already saved or done)
            
            if ((isValid(hidden, row, col)) && (hidden[row][col] == 0)){
                boolean indexAdded = false;
                if ((isValid(hidden, row -1, col -1)) && (!isSaved (row - 1, savedRow, col - 1, savedCol)) && (!isSaved (row - 1, doneRow, col - 1, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row -1;
                    savedCol[savedIndex] = col -1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row -1, col) && (!isSaved (row - 1, savedRow, col, savedCol)) && (!isSaved (row - 1, doneRow, col, doneCol)))){
                    savedIndex ++;
                    savedRow[savedIndex] = row -1;
                    savedCol[savedIndex] = col;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row -1, col +1)) && (!isSaved (row - 1, savedRow, col +1, savedCol)) && (!isSaved (row, doneRow, col -1, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row -1;
                    savedCol[savedIndex] = col +1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row, col -1)) && (!isSaved (row, savedRow, col -1, savedCol)) && (!isSaved (row, doneRow, col -1, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row;
                    savedCol[savedIndex] = col -1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row, col +1)) && (!isSaved (row, savedRow, col +1, savedCol)) && (!isSaved (row, doneRow, col +1, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row;
                    savedCol[savedIndex] = col +1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row +1, col -1)) && (!isSaved (row +1, savedRow, col -1, savedCol)) && (!isSaved (row +1, doneRow, col -1, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row +1;
                    savedCol[savedIndex] = col -1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row +1, col)) && (!isSaved (row +1, savedRow, col, savedCol)) && (!isSaved (row +1, doneRow, col, doneCol))){
                    savedIndex ++;
                    savedRow[savedIndex] = row +1;
                    savedCol[savedIndex] = col;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
            
                if ((isValid(hidden, row +1, col +1)) && (!isSaved (row +1, savedRow, col +1, savedCol)) && (!isSaved (row +1, doneRow, col +1, doneCol)) ){
                    savedIndex ++;
                    savedRow[savedIndex] = row +1;
                    savedCol[savedIndex] = col +1;
                    //System.out.println(savedRow[savedIndex] + ", " + savedCol[savedIndex] + " saved at " + savedIndex);
                    indexAdded = true;
                }
                
                //If there was any new locations added you have to increase the last saved index by 1 (so we can catch all of them eventually)
                if(indexAdded){
                    savedIndex ++;
                }
            }
            }
            //Decrease saved index to continue iterating through the rest of the saved locations
            savedIndex --;
        }
        //return the new updated revealed board
        return revealed;
    }
    
    public static boolean isValid(int[][] hidden, int row, int col){
        if ((row >= 0) && (col >= 0) && (row < hidden.length) && (col < hidden[row].length) && (hidden[row][col] != -1)){
            // //System.out.println(row + ", " + col + " isValid");
            return true;
        } else {
            return false;
        }
        
    }
    
    //Check if the location has been saved in the past
    public static boolean isSaved(int row, int[] rowArray, int col, int[] colArray){                                                                                        //For every index available in the saved array                              
        for (int i = 0; i < rowArray.length; i++){
            if ((rowArray[i] == row) && (colArray[i] == col)){
                //If both col and row match at that index, the location has already been saved so return true.
                // //System.out.println(row + ", " + col + " is saved already");
                return true;
            }
        }
        //Once every index has been checked, return false because the location has not yet been saved.
        //System.out.println(row + ", " + col + " isn't saved already");
        return false;
    }
}