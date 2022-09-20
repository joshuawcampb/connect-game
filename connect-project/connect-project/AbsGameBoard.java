package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard{

    @Override
    public String toString() {

        String brd = "";
        for(int k = 0; k < getNumColumns(); k++){

            if(k < 10)
                brd += "| ";

            else
                brd += "|";

            brd += String.valueOf(k);

        }

        brd += "|\n";

        for(int i = getNumRows()-1; i >= 0; i--){
            for(int j = 0; j < getNumColumns(); j++){
                BoardPosition boardPos = new BoardPosition(i, j);
                brd += "|";
                brd += whatsAtPos(boardPos);
                brd += " ";
            }

            brd += "|";
            brd += "\n";

        }
        return brd;

    }

}
