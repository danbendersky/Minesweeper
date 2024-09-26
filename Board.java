public class Board {
    public static void main(String[] args) {
    }
    private int mineCount;
    private int horSize;
    private int verSize;
    private int game[][];
    
    // For hidden board: -1 = mine
    // -2 = empty undiscovered space
    
    //Create new board based on dimensions
    public Board (int horSize, int verSize){
        game = new int[verSize][horSize];
        this.horSize = horSize;
        this.verSize = verSize;
        
    }
    
    public int getHorSize(){
        return horSize;
    }
    
    public int getVerSize(){
        return verSize;
    }
    
    public void setMineCount(int mineCount){
        this.mineCount = mineCount;
    }
    
    //Place a certain number of mines on the board and update the board values to -1 at their locations to match
    public void placeMines(){
        for (int i = 1; i <= mineCount; i++){
            int horI = (int) (Math.random() * (horSize));
            int verI = (int) (Math.random() * (verSize));
            
            if (game[verI][horI] != -1){
                game[verI][horI] = -1;
            } else {
                i--;
            }
        }
    }
    
    //Set all board location values to a certain int 
    public void resetBoard(int startingInt){
        for (int row = 0; row < game.length; row++){
            for (int col = 0; col < game[row].length; col ++){
                game[row][col] = startingInt;
            }
        }
    }
    
    //Get value at a location
    public int getValue(int row, int col){
        return game[row][col];
    }
    
    //Get the entire board array
    public int[][] getBoard(){
        return game;
    }
    
    //Set the entire board array
    public void setBoard(int[][] board){
        game = board;
    }
    
    //This method sets the values on the board based on the # of mines adjacent to each location (minesweeper rules)
    public void setup(){
        int row = 0;
        int col = 0;
        for (int i=1; i<= mineCount; i++){
            if (col >= horSize){
                col = 0;
                row ++;
            }
            
            if (game[row][col] == -1){
                increaseValue(row -1, col -1);
                increaseValue(row -1, col);
                increaseValue(row -1, col +1);
                increaseValue(row, col -1);
                increaseValue(row, col +1);
                increaseValue(row +1, col -1);
                increaseValue(row +1, col);
                increaseValue(row +1, col +1);
            } else {
                i--;
            }
            
            col++;
        }
    }
    
    //Increase value at a certain location by 1
    public void increaseValue(int row, int col){
        if ((row >= 0) && (col >= 0) && (col < horSize) && (row < verSize) && (game[row][col] != -1)){
            game[row][col] = game[row][col] + 1;
        }
    }
}