import java.util.Scanner;

public class Minesweeper
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        int horSize;
        int verSize;
        int mineCount;

        //Until game difficulty is selected:
        while (true){
            System.out.println("Choose your difficulty: E (Easy) N (Normal) H (Hard)");
            String difficulty = input.nextLine();
            if (difficulty.equals("E")){
                horSize = 9;
                verSize = 9;
                mineCount = 10;
                break;
            } else if (difficulty.equals("N")){
                    horSize = 16;
                    verSize = 16;
                    mineCount = 40;
                    break;
            } else if (difficulty.equals("H")){
                horSize = 30;
                verSize = 16;
                mineCount = 99;
                break;
            }
        }
        
        //Set dimensions of boards based on difficulty
        Board hiddenBoard = new Board(horSize, verSize);
        
        Board revealedBoard = new Board(horSize, verSize);
        
        //Set mine count based on difficulty
        hiddenBoard.setMineCount(mineCount);
        
        //Reset all the values of the hidden board to 0
        hiddenBoard.resetBoard(0);
        
        //Reset all the values of the revealed board to -2 (-2 means still hidden (-))
        revealedBoard.resetBoard(-2);
        
        //Place mines on the hidden board
        hiddenBoard.placeMines();
        
        //Create our board printer object
        BoardPrinter boardPrinter = new BoardPrinter(horSize, verSize);
        
        //Print starting board
        boardPrinter.printBoard(revealedBoard);
        
        //Set up values of board based on mine placement
        hiddenBoard.setup();
        
        //boardPrinter.printBoard(hiddenBoard);
        
        boolean markChoice = false;
        int colChoice = 0;
        int rowChoice = 0;
        
        //While game isn't won
        while (!Functions.checkWin(revealedBoard.getBoard(), hiddenBoard.getBoard())){
            System.out.print("Enter a row number, or enter 0 to mark a mine: ");
            rowChoice = input.nextInt() - 1;
            input.nextLine();
            //If marking a mine, 
            if (rowChoice == -1){
                markChoice = true;
                //If not marking mine, continue with entering a column number
            } else {
                System.out.print("Enter a column number: ");
                colChoice = input.nextInt() - 1;
                input.nextLine();
            }
            
            //If both row and column choice are valid, continue with program. Allow exception of -1 for row for marking a mine
            if ((colChoice < horSize) && (colChoice >= -1) && (rowChoice < verSize) && (rowChoice >= 0)){
            
            
            
            //Check for mine hit-if hit, break game loop. Also make sure to skip this if this iteration was used for marking a mine.
            if (!markChoice && (hiddenBoard.getValue(rowChoice, colChoice) == -1)){
                break;
            }
            
            //If this iteration was not used for marking a mine, then reveal board from location
            if (!markChoice){
                //Update the revealed board to the new revealed locations 
                revealedBoard.setBoard(Functions.revealArea(revealedBoard.getBoard(), hiddenBoard.getBoard(), rowChoice, colChoice));
            }
            
            //If this iteration is for marking a mine, then continue to ask for mark row and column
            if (markChoice){
                System.out.print("Enter a row number for marking: ");
                rowChoice = input.nextInt() - 1;
                input.nextLine();
                System.out.print("Enter a column number for marking: ");
                colChoice = input.nextInt() - 1;
                input.nextLine();
                
                //If row and col are valid, add the mark
                if ((colChoice < horSize) && (colChoice >= 0) && (rowChoice < verSize) && (rowChoice >= 0)){
                boardPrinter.addMark(rowChoice, colChoice);
                } else {
                    System.out.println("This location does not exist on the board. Please select a new location.");
                }
                //Reset markChoice to false for next iteration
                markChoice = false;
            }
            
            //Print out the updated revealed board
            boardPrinter.printBoard(revealedBoard);
            
            //If col and row are not both valid skip the process
            } else {
                System.out.println("This location does not exist on the board. Please select a new location.");
            }
        }
        //After game loop is broken:
        
        //If game is won:
        if (Functions.checkWin(revealedBoard.getBoard(), hiddenBoard.getBoard())){
            System.out.println("Congrats, you won!");
            
        //If game is not won, then that means it is lost:
        } else {
            System.out.println("Sorry, you hit a mine and lost. Try again next time.");
        }
    }
}