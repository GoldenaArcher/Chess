package Main;

import Main.Operation.ChessBoard;
import Main.Operation.IllegalMoveException;

import java.util.Scanner;

/**
 * @author Lu Han
 */
public class Chess {
    public static void main(String[] args) {
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
                e.printStackTrace();
            }
            try {
                exit = scanner.nextInt();
            } catch (Exception ignored){
            }
        }
        scanner.close();
    }
}
