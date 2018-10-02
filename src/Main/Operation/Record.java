package Main.Operation;

/**
 * use array to implement a history of chess moves
 */
public class Record {
    private String[] record;
    private ChessBoard chessBoard;

    public Record(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        this.record = new String[]{};
    }
}
