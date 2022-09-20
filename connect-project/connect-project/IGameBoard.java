package cpsc2150.connectX;

/**
 * Initialization Ensures:
 *      board will contain blank characters and
 *      position will be (0,0)
 *
 * Defines: number.of.rows: Z
 *          number.of.columns: Z
 *          number.to.win: Z
 *
 * Constraints: number.of.rows = ROW_SIZE
 *              number.of.cols = COL_SIZE
 *              number.to.win = NUM_TO_WIN
 *
 */

public interface IGameBoard {

    /**
     *
     * @param c represents the column
     * @return whether column is able to accept another token
     * @pre 0 <= c <= COL_SIZE
     * @post true if column is able to accept another token
     */
    public default boolean checkIfFree(int c){
        BoardPosition boardPos = new BoardPosition(getNumRows()-1, c);
        char token = whatsAtPos(boardPos);
        return token == ' ';
    }

    /**
     *
     * @param c represents the column
     * @return whether the last token place results in a win
     * @pre 0 <= c < COL_SIZE
     * @post true if the last token placed results in a player winning the game
     */
    public default boolean checkForWin(int c){
        for(int i = getNumRows()-1; i >= 0; i--) {
            BoardPosition boardPos = new BoardPosition(i, c);
            char token = whatsAtPos(boardPos);
            if (token != ' ') {

                if (checkHorizWin(boardPos, whatsAtPos(boardPos)))
                    return true;

                else if (checkVertWin(boardPos, whatsAtPos(boardPos)))
                    return true;

                else return checkDiagWin(boardPos, whatsAtPos(boardPos));
            }
        }

        return false;
    }

    /**
     *
     * @param p represents the token
     * @param c represents the column
     * @pre p == X OR p == O and 0 <= c < COL_SIZE
     * @post places token p in lowest available row in column c on game board
     */
    public void placeToken(char p, int c);

    /**
     *
     * @param pos represents the position
     * @param p represents the player
     * @return whether the player gets a win by getting 4 in a row horizontally
     * @pre pos = position and p = X OR p = O
     * @post true if the player gets a win after placing the last token results
     *       in a win horizontally by getting 4 in a row
     */
    public default boolean checkHorizWin(BoardPosition pos, char p){

        int counter = 0;

        for(int i = 0; i < getNumColumns(); i++){
            BoardPosition boardPos = new BoardPosition(pos.getRow(), i);
            if(whatsAtPos(boardPos) == p) {
                counter++;
                if (counter == getNumToWin())
                    return true;
            }

            else
                counter = 0;

        }

        return counter == getNumToWin();

    }

    /**
     *
     * @param pos represents the position
     * @param p represents the player
     * @return whether the player gets a win by getting 4 in a row vertically
     * @pre pos = position and p = X OR p = O
     * @post true if the player gets a win after placing the last token results
     *       in a win vertically by getting 4 in a row
     */
    public default boolean checkVertWin(BoardPosition pos, char p){

        int counter = 0;

        for(int i = 0; i < getNumRows(); i++){
            BoardPosition boardPos = new BoardPosition(i, pos.getColumn());
            if(whatsAtPos(boardPos) == p) {
                counter++;
                if (counter == getNumToWin())
                    return true;

            }

            else
                counter = 0;

        }

        return counter == getNumToWin();

    }

    /**
     *
     * @param pos represents the position
     * @param p represents the player
     * @return whether the player gets a win by getting 4 in a row diagonally
     * @pre pos = position and p == X OR p == O
     * @post true if the player gets a win after placing the last token results
     *       in a win diagonally by getting 4 in a row
     */
    public default boolean checkDiagWin(BoardPosition pos, char p){

        int i = pos.getRow(), j = pos.getColumn(), counter = 1;

        while (i + 1 < getNumRows() && j + 1 < getNumColumns()) {
            BoardPosition boardPos = new BoardPosition (i+1, j+1);
            if (whatsAtPos(boardPos) == p) {

                counter++;

            }

            else
                counter = 1;

            i++;
            j++;

        }

        if (counter >= getNumToWin())
            return true;

        i = pos.getRow();
        j = pos.getColumn();

        while (i - 1 >= 0 && j - 1 >= 0) {
            BoardPosition boardPos = new BoardPosition(i-1, j-1);
            if (whatsAtPos(boardPos) == p) {

                counter++;

            } else
                counter = 1;

            i--;
            j--;
        }

        if (counter == getNumToWin())
            return true;

        counter = 1;
        i = pos.getRow();
        j = pos.getColumn();

        while (i - 1 >= 0 && j + 1 < getNumColumns()) {
            BoardPosition boardPos = new BoardPosition(i-1, j+1);
            if (whatsAtPos(boardPos) == p) {

                counter++;

            } else
                counter = 1;

            i--;
            j++;
        }

        if (counter == getNumToWin())
            return true;

        i = pos.getRow();
        j = pos.getColumn();

        while (i + 1 < getNumRows() && j - 1 >= 0) {
            BoardPosition boardPos = new BoardPosition(i+1, j-1);
            if (whatsAtPos(boardPos) == p) {

                counter++;

            } else
                counter = 1;

            i++;
            j--;
        }

        if (counter == getNumToWin())
            return true;

        return false;

    }

    /**
     *
     * @param pos represents the position
     * @return the char that is in the position of the game board
     * @pre pos = position
     * @post returns the char that is in the position of the game board. If
     *       there is no token at the spot " " will be returned
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     *
     * @param pos represents the position
     * @param player represents the player
     * @return whether the player is at the position
     * @pre pos = position and player == X OR player == O
     * @post true if the player is player is at that position
     */
    public default boolean isPlayerAtPos(BoardPosition pos, char player){
    
    	return whatsAtPos(pos) == player;
    
    }

    /**
     *
     * @return a string that represents the game board
     * @post returns a fully formatted string that displays that displays the
     *       current game board
     */
    public String toString();

    /**
     *
     * @return whether the game board results in a tie
     * @pre placeToken must have been called
     * @post true if the game board results in a tie
     */
    public default boolean checkTie(){

        for(int i = 0; i < getNumColumns(); i++) {

            if(checkIfFree(i))
                return false;

        }

        return true;

    }

    /**
     * @return number of rows
     * @post getNumRows = num rows
     */
    public int getNumRows();

    /**
     * @return number of columns
     * @post getNumColumns = num columns
     */
    public int getNumColumns();

    /**
     * @return number to get in a row to win
     * @post getNumToWin = num to win
     */
    public int getNumToWin();

}
