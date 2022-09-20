package cpsc2150.connectX;

public class BoardPosition {

    /**
     * @invariant 0 <= rowPos <= 5 and 0 <= colPos <= 6
     */

    private int rowPos;
    private int colPos;

    /**
     *
     * @param r represents the row position
     * @param c represents the column position
     * @pre 0 <= r <= 5 and 0 <= c <= 6
     * @post r = rowPos and c = colPos
     */
    public BoardPosition(int r, int c){

        rowPos = r;
        colPos = c;

    }

    /**
     *
     * @return the row position
     * @post getRow = rowPos
     */
    public int getRow(){

        return rowPos;

    }

    /**
     *
     * @return the column position
     * @post getColumn = colPos
     */
    public int getColumn(){

        return colPos;

    }

    /**
     *
     * @return true if row position = column position
     * @post returns true if rowPos = colPos
     */
    @Override
    public boolean equals(Object Obj){

        if(!(Obj instanceof BoardPosition))
            return false;

        BoardPosition pos = (BoardPosition) Obj;
        return this.getColumn() == pos.getColumn() && this.getRow() == pos.getRow();

    }

    /**
     *
     * @return a formatted string of the row and col positions
     * @post Makes a string formatted of the row and col positions as
     * "<rowPos>,<colPos>"
     */
    @Override
    public String toString(){

        return String.valueOf(rowPos) + ',' + colPos;

    }

}
