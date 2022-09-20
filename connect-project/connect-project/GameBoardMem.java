package cpsc2150.connectX;

import java.util.*;

public class GameBoardMem extends AbsGameBoard{


    /**
     * @invariant ROW_SIZE = row, COL_SIZE = col, NUM_TO_WIN = wins
     */

    private Map <Character, List<BoardPosition>> board;
    private final int ROW_SIZE;
    private final int COL_SIZE;
    private final int NUM_TO_WIN;
    private BoardPosition boardPos;

    /**
     * @pre 3 <= row <= 100 & 3 <= col <= 100 &
     *      (3 <= win <= 25 && win <= row && win <= col)
     * @post initializes the game board
     */
    public GameBoardMem(int row, int col, int win){

        COL_SIZE = col;
        ROW_SIZE = row;
        NUM_TO_WIN = win;
        board = new HashMap<>();
        boardPos = new BoardPosition(0,0);

    }

    public void placeToken(char p, int c) {

        List<BoardPosition> listOfValues = board.get(p);

        for(int i = 0; i < ROW_SIZE; i++){

            boardPos = new BoardPosition(i, c);
            if(whatsAtPos(boardPos) == ' ')
                i = ROW_SIZE;

        }

        if(listOfValues == null){

            listOfValues = new ArrayList<BoardPosition>();
            listOfValues.add(boardPos);
            board.put(p, listOfValues);

        }

        else{

            listOfValues.add(boardPos);

        }

    }

    public char whatsAtPos(BoardPosition pos){

        if(board.isEmpty()){
            return ' ';
        }

        for(Map.Entry<Character, List<BoardPosition>> m: board.entrySet())
            for(int i = 0; i < board.get(m.getKey()).size(); i++)
                if (board.get(m.getKey()).get(i).equals(pos))
                    return m.getKey();

        return ' ';

    }

		@Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){


        if(board.isEmpty())
            return false;

        if(board.containsKey(player))
            for(int i = 0; i < board.get(player).size(); i++)
                if (board.get(player).get(i).equals(pos))
                    return true;

        return false;


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
