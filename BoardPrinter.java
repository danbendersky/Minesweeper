public class BoardPrinter {
    
    //Array of marked flags
    private boolean[][] isMarked;
    
    //Add mark to board
    public void addMark(int row, int col){
        isMarked[row][col] = true;
    }
    
    //Initialize a new array of markers and reset them all to false
    public BoardPrinter (int horSize, int verSize){
        isMarked = new boolean[horSize][verSize];
        
        for (int row = 0; row < isMarked.length; row++){
            for (int col = 0; col < isMarked[0].length; col ++){
                isMarked[row][col] = false;
            }
        }
        
    }
    
    //Print board based on revealed board and flag mark array
    public void printBoard(Board board){
        //Top header
        System.out.print("  ");
        for (int i=1; i<=9; i++){
            System.out.print("  " + i);
        }
        for (int i = 10; i<=board.getHorSize(); i++){
            System.out.print(" " + i);
        }
        //Space between header and rest of board
        System.out.println("");
        System.out.println("");
        
        for (int i=1; i<=9; i++){
            //Left header
            System.out.print(" " + i);
            for (int col = 0; col < board.getHorSize(); col++){
                System.out.print("  "); 
                int value = board.getValue(i-1, col);
                
                if (isMarked[i-1][col]){
                    System.out.print("!");
                } else if (value == -2){
                    System.out.print("-");
                } else {
                    System.out.print(value);
                }
            }
            //Space between each row
            System.out.println("");
        }
        
        for (int i=10; i<=board.getVerSize(); i++){
            //Left header
            System.out.print(i);
            for (int col = 0; col < board.getHorSize(); col++){
                System.out.print("  "); 
                int value = board.getValue(i-1, col);
                
                if (isMarked[i-1][col]){
                    System.out.print("!");
                } else if (value == -2){
                    System.out.print("-");
                } else {
                    System.out.print(value);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}