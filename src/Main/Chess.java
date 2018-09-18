package Main;

import Main.Operation.ChessBoard;
import Main.Exception.IllegalMoveException;
import Main.View.ChessboardMainFrame;

import java.util.Scanner;

/**
 * @author Lu Han
 */
public class Chess {
    public static void main(String[] args) {
        new ChessboardMainFrame();
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board.toString());
        Scanner scanner = new Scanner(System.in);
        int exit = 0;
        System.out.println("Enter -1 to exit the game");
        while (exit != -1) {
            System.out.println("Which piece do you want to move: ");
            String from = scanner.next();
            System.out.println("Which place do you want to place: ");
            String to = scanner.next();
            try {
                board.move(from, to);
            } catch (IllegalMoveException e) {
                System.out.println("Illegal moves");
            }
            try {
                exit = scanner.nextInt();
            } catch (Exception ignored){
            }
        }
        scanner.close();
        System.exit(0);
    }
}
