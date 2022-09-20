package cpsc2150.connectX;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameScreen {

    /**
     * @post allows for multiple completions of a game of connect x if desired
     */
    public static void main(String[] args){

        boolean playAgain = true;
        int numPlayers;
        int numRows;
        int numCols;
        int numtowin;

        while(playAgain) {

            String character;
            Scanner scan = new Scanner(System.in);

            System.out.println("How many players?");
            numPlayers = scan.nextInt();

            while(numPlayers < 2 || numPlayers > 10){

                if(numPlayers < 2)
                    System.out.println("Must be at least 2 players");

                else
                    System.out.println("Must be 10 players or fewer");

                numPlayers = scan.nextInt();

            }

            List<String> a = new ArrayList<>();

            for(int i = 1; i <= numPlayers; i++) {
                System.out.print("Enter the character to represent player ");
                System.out.println(i);

                character = scan.next();
                scan.nextLine();
                character = character.toUpperCase();


                if(i != 1){

                    while (a.contains(character.toUpperCase()) ||
                            character.equals(" ")) {

                        System.out.print(character);
                        System.out.println(" is already taken as a player " +
                                "token!");
                        System.out.print("Enter the character to represent" +
                                " player ");
                        System.out.println(i);
                        character = scan.next();
                        scan.nextLine();
                        character = character.toUpperCase();


                    }

                }

                a.add(character);
            }

            System.out.println("How many rows should be on the board?");
            numRows = scan.nextInt();

            while(numRows < 3 || numRows > 100){

                System.out.println("Number of rows must be greater than " +
                        "2 and less than 101");
                numRows = scan.nextInt();

            }

            System.out.println("How many columns should be on the board?");
            numCols = scan.nextInt();

            while(numCols < 3 || numCols > 100){

                System.out.println("Number of columns must be greater " +
                        "than 2 and less than 101");
                numCols = scan.nextInt();

            }

            System.out.println("How many in a row to win?");
            numtowin = scan.nextInt();

            while(numtowin < 3 || numtowin > 25 || numtowin > numCols
                    || numtowin > numRows){

                if(numtowin < 3 || numtowin > 25)
                    System.out.println("Number to win should be " +
                            "greater than 2 and less than 26");

                else if(numtowin > numCols)
                    System.out.println("Number to win should " +
                            "not be greater than the number of columns");

                else
                    System.out.println("Number to win should not be " +
                            "greater than the number of rows");

                numtowin = scan.nextInt();

            }

            System.out.println("Would you like a Fast Game (F/f) or " +
                    "a Memory Efficient Game (M/m)?");
            String gameChoice = scan.next();
            scan.nextLine();

            IGameBoard gameBoard;

            while(!gameChoice.equals("F") && !gameChoice.equals("f")
                    && !gameChoice.equals("m") && !gameChoice.equals("M")){

                System.out.println("Please enter F or M");
                System.out.println("Would you like a Fast Game (F/f) " +
                        "or a Memory Efficient Game (M/m)?");
                gameChoice = scan.next();
                scan.nextLine();

            }

            if(gameChoice.equals("F") || gameChoice.equals("f")){

                gameBoard = new GameBoard(numRows, numCols, numtowin);

            }

            else{

                gameBoard = new GameBoardMem(numRows, numCols, numtowin);

            }

            System.out.print(gameBoard.toString());

            boolean win = false;
            int i = 0;
            char token;

            while(!win) {

                token = a.get(i).charAt(0);
                System.out.print("Player ");
                System.out.print(token);
                System.out.println(", what column do you want to place your " +
                        "marker in?");

                int colNum = scan.nextInt();

                while(colNum < 0 || colNum > gameBoard.getNumColumns()-1 ||
                        !gameBoard.checkIfFree(colNum)) {

                    if (colNum < 0)
                        System.out.println("Column cannot be less than 0");

                    else if (colNum > gameBoard.getNumColumns()-1) {
                        System.out.print("Column cannot be greater than ");
                        System.out.println(numCols-1);
                    }

                    else if (!gameBoard.checkIfFree(colNum))
                        System.out.println("Column is full");

                    System.out.print("Player ");
                    System.out.print(token);
                    System.out.println(", what column do you want to place " +
                            "your marker in?");
                    colNum = scan.nextInt();

                }

                gameBoard.placeToken(token, colNum);
                System.out.print(gameBoard.toString());

                if (gameBoard.checkForWin(colNum) || gameBoard.checkTie()) {

                    if(gameBoard.checkForWin(colNum)) {
                        System.out.print("Player ");
                        System.out.print(token);
                        System.out.println(" Won!");
                    }

                    else
                        System.out.println("The game resulted in a tie!");

                    win = true;
                    System.out.println("Would you like to play again? Y/N");
                    String answer;
                    answer = scan.next();

                    while(!answer.equals("y") && !answer.equals("Y") &&
                            !answer.equals("n") && !answer.equals("N")){

                        System.out.println("Would you like to play again? " +
                                "Y/N");
                        answer = scan.next();

                    }

                    playAgain = answer.equals("y") || answer.equals("Y");

                }

                if(i < numPlayers-1)
                    i++;

                else
                    i = 0;

            }

        }

    }

}
