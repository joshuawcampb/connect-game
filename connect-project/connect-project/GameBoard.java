package cpsc2150.connectX;

public class GameBoard extends AbsGameBoard{

    /**
     * @invariant ROW_SIZE = row, COL_SIZE = col, NUM_TO_WIN = win
     */

    private char[][] board;
    private final int ROW_SIZE;
    private final int COL_SIZE;
    private final int NUM_TO_WIN;
    private BoardPosition boardPos;

    /**
     * @pre 3 <= row <= 100 & 3 <= col <= 100 &
     *      (3 <= win <= 25 && win <= row && win <= col)
     * @post initializes the game board
     */
    public GameBoard(int row, int col, int win){

        COL_SIZE = col;
        ROW_SIZE = row;
        NUM_TO_WIN = win;
        board = new char[ROW_SIZE][COL_SIZE];
        boardPos = new BoardPosition(0,0);

    }

    public void placeToken(char p, int c){

        for(int i = 0; i < ROW_SIZE; i++){
            boardPos = new BoardPosition(i, c);
            if(whatsAtPos(boardPos) == ' ') {
                board[i][c] = p;
                return;
            }
        }

    }

    public char whatsAtPos(BoardPosition pos){

        if(board == null)
            return ' ';

        for(char i = 'A'; i <= 'Z'; i++){

            if(board[pos.getRow()][pos.getColumn()] == i)
                return i;

        }

        return ' ';

    }

    public int getNumRows(){

        return ROW_SIZE;

    }
    public int getNumColumns(){

        return COL_SIZE;

    }
    public int getNumToWin(){

        return NUM_TO_WIN;

    }

}
