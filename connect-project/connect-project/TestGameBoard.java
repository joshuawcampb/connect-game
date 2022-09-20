package cpsc2150.connectX;

import org.junit.Test;
import static org.junit.Assert.*;
public class TestGameBoard {

    private IGameBoard MakeAGameBoard(int row, int col, int win){

        return new GameBoard(row, col, win);

    }

    private String MakeAString(char [][] board){

        String brd = "";
        for(int i = 0; i < board[0].length; i++){

            if(i < 10)
                brd += "| " + i;

            else
                brd += "|" + i;

        }

        brd += "|\n";

        for(int i = board.length-1; i >= 0; i--){
            for(int j = 0; j < board[i].length; j++){
                brd += "|";

                if(board[i][j] == '\0')
                    brd += ' ';

                else
                    brd += board[i][j];

                brd += " ";
            }

            brd += "|";
            brd += "\n";

        }

        return brd;

    }

    @Test
    public void TestConstructor_CheckNumRows(){

        IGameBoard gb = MakeAGameBoard(13, 14, 15);
        assertEquals(gb.getNumRows(), 13);

    }

    @Test
    public void TestConstructor_CheckNumCols(){

        IGameBoard gb = MakeAGameBoard(13, 14, 15);
        assertEquals(gb.getNumColumns(), 14);

    }

    @Test
    public void TestConstructor_CheckNumToWin(){

        IGameBoard gb = MakeAGameBoard(13, 14, 15);
        assertEquals(gb.getNumToWin(), 15);

    }

    @Test
    public void TestCheckIfFree_EmptyCol(){

        IGameBoard gb = MakeAGameBoard(5, 5, 5);
        assertTrue(gb.checkIfFree(0));

    }

    @Test
    public void TestCheckIfFree_FullCol(){

        IGameBoard gb = MakeAGameBoard(5, 5, 5);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        assertFalse(gb.checkIfFree(0));

    }

    @Test
    public void TestCheckIfFree_SomeInCol(){

        IGameBoard gb = MakeAGameBoard(5, 5, 5);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        assertTrue(gb.checkIfFree(0));

    }

    @Test
    public void TestCheckHorizWin_TopRow(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        BoardPosition boardPos = new BoardPosition(2, 1);
        assertTrue(gb.checkHorizWin(boardPos, 'O'));

    }

    @Test
    public void TestCheckHorizWin_NumToWinMet(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        BoardPosition boardPos = new BoardPosition(0, 1);
        assertTrue(gb.checkHorizWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckHorizWin_NumToWinNotMet_SameTokens(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        BoardPosition boardPos = new BoardPosition(0, 1);
        assertFalse(gb.checkHorizWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckHorizWin_NumToWinNotMet_DifferentTokens(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        BoardPosition boardPos = new BoardPosition(0, 1);
        assertFalse(gb.checkHorizWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckVertWin_TopTokenInTopRow(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 0);
        BoardPosition boardPos = new BoardPosition(1, 0);
        assertTrue(gb.checkVertWin(boardPos, 'O'));

    }

    @Test
    public void TestCheckVertWin_NumToWinMet(){

        IGameBoard gb = MakeAGameBoard(4, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(2, 0);
        assertTrue(gb.checkVertWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckVertWin_NumToWinNotMet_SameTokens(){

        IGameBoard gb = MakeAGameBoard(5, 4, 4);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(2, 0);
        assertFalse(gb.checkVertWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckVertWin_NumToWinNotMet_DifferentTokens(){

        IGameBoard gb = MakeAGameBoard(5, 4, 4);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(1, 0);
        assertFalse(gb.checkVertWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_ForwardDiagonalWin(){

        IGameBoard gb = MakeAGameBoard(5, 5, 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('O', 4);
        BoardPosition boardPos = new BoardPosition(2, 1);
        assertTrue(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_BackwardDiagonalWin(){

        IGameBoard gb = MakeAGameBoard(5, 5, 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(2, 3);
        assertTrue(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_ForwardDiagonalWin_TopTokenInTopRow(){

        IGameBoard gb = MakeAGameBoard(5, 5, 5);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(3, 1);
        assertTrue(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_BackwardDiagonalWin_TopTokenInTopRow(){

        IGameBoard gb = MakeAGameBoard(5, 5, 5);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('X', 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(3, 3);
        assertTrue(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_ForwardDiagonal_NumToWinNotMet_SameTokens(){

        IGameBoard gb = MakeAGameBoard(5, 5, 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(2, 1);
        assertFalse(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_BackwardDiagonal_NumToWinNotMet_SameTokens(){

        IGameBoard gb = MakeAGameBoard(5, 5, 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('O', 3);
        gb.placeToken('O', 3);
        gb.placeToken('X', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(2, 3);
        assertFalse(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckDiagWin_ForwardDiagonal_NumToWinNotMet_DifferentTokens(){

        IGameBoard gb = MakeAGameBoard(5, 5, 4);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 1);
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 3);
        gb.placeToken('O', 3);
        gb.placeToken('O', 4);
        gb.placeToken('X', 4);
        BoardPosition boardPos = new BoardPosition(1, 2);
        assertFalse(gb.checkDiagWin(boardPos, 'X'));

    }

    @Test
    public void TestCheckTie_BoardFullNoWin(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('O', 2);
        gb.placeToken('O', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        assertTrue(gb.checkTie());

    }

    @Test
    public void TestCheckTie_OneTokenInEveryCol(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        assertFalse(gb.checkTie());

    }

    @Test
    public void TestCheckTie_OneToken(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        assertFalse(gb.checkTie());

    }

    @Test
    public void TestCheckTie_ColFull(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        assertFalse(gb.checkTie());

    }

    @Test
    public void TestWhatsAtPos_NoTokenAtPos(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 2);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertEquals(' ', gb.whatsAtPos(boardPos));

    }

    @Test
    public void TestWhatsAtPos_TokenAtPos(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertEquals('X', gb.whatsAtPos(boardPos));

    }

    @Test
    public void TestWhatsAtPos_EmptyBoard(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertEquals(' ', gb.whatsAtPos(boardPos));

    }

    @Test
    public void TestWhatsAtPos_DiffTokensOnTopOfEachOther(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertEquals('X', gb.whatsAtPos(boardPos));

    }

    @Test
    public void TestWhatsAtPos_MultipleOfSameToken(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertEquals('X', gb.whatsAtPos(boardPos));

    }

    @Test
    public void TestIsPlayerAtPos_PlayerMatchesTokenAtPos(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertTrue(gb.isPlayerAtPos(boardPos, 'X'));

    }

    @Test
    public void TestIsPlayerAtPos_PlayerDoesntMatchTokenAtPos_Exists(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertFalse(gb.isPlayerAtPos(boardPos, 'O'));

    }

    @Test
    public void TestIsPlayerAtPos_NoTokenAtPos(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 2);
        BoardPosition boardPos = new BoardPosition(0,1);
        assertFalse(gb.isPlayerAtPos(boardPos, 'X'));

    }

    @Test
    public void TestIsPlayerAtPos_EmptyBoard(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        BoardPosition boardPos = new BoardPosition(0,1);
        assertFalse(gb.isPlayerAtPos(boardPos, 'X'));

    }

    @Test
    public void TestIsPlayerAtPos_PlayerDoesntMatchTokenAtPos_DoesntExist(){

        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        BoardPosition boardPos = new BoardPosition(0,0);
        assertFalse(gb.isPlayerAtPos(boardPos, 'O'));

    }

    @Test
    public void TestPlaceToken_OneToken_EmptyBoard(){

        char [][] board = new char[3][3];
        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        board[0][0] = 'X';
        assertEquals(MakeAString(board), gb.toString());

    }

    @Test
    public void TestPlaceToken_TwoDiffTokensOnTopOfEachOther(){

        char [][] board = new char[3][3];
        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        board[0][0] = 'X';
        board[1][0] = 'O';
        assertEquals(MakeAString(board), gb.toString());

    }

    @Test
    public void TestPlaceToken_TwoDifferentTokens(){

        char [][] board = new char[3][3];
        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        board[0][0] = 'X';
        board[0][1] = 'O';
        assertEquals(MakeAString(board), gb.toString());

    }

    @Test
    public void TestPlaceToken_FillARow(){

        char [][] board = new char[3][3];
        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 1);
        gb.placeToken('X', 2);
        board[0][0] = 'X';
        board[0][1] = 'O';
        board[0][2] = 'X';
        assertEquals(MakeAString(board), gb.toString());

    }

    @Test
    public void TestPlaceToken_FillACol(){

        char [][] board = new char[3][3];
        IGameBoard gb = MakeAGameBoard(3, 3, 3);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);

        board[0][0] = 'X';
        board[1][0] = 'O';
        board[2][0] = 'X';
        assertEquals(MakeAString(board), gb.toString());

    }

}