package Main;

/**
 * @author Lu Han
 */
public class Chess {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        board.initialize();
        System.out.println(board.toString());
    }
}
