package Main;

import Main.ChessPieces.*;

public class ChessBoard {
    private ChessPiece[][] chessBoard;
    private int[] digit;

    public ChessBoard() {
        chessBoard = new ChessPiece[8][8];
        digit = new int[128];
        initializeDigit();
        initialize();
    }

//    @TODO rewrite the set piece
//    this is a helper function to implements the chess pieces on the board for initialization
    private void initializePieces(ChessPiece.Color color, int column, int row) {
        switch (column) {
            case 0:
            case 7:
                chessBoard[row][column] = new Rook(chessBoard, color, row, column);
                break;
            case 1:
            case 6:
                chessBoard[row][column] = new Knight(chessBoard, color, row, column);
                break;
            case 2:
            case 5:
                chessBoard[row][column] = new Bishop(chessBoard, color, row, column);
                break;
            case 4:
                chessBoard[row][column] = new King(chessBoard, color, row, column);
                break;
            default:
                chessBoard[row][column] = new Queen(chessBoard, color, row, column);
        }
    }

    //    @TODO rewrite the set piece
    private void initialize() {
//        To initialize piece into correct position, i.e.,a1 is the white rook etc. Refer to chess rules for details
        ChessPiece.Color black = ChessPiece.Color.BLACK;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        for (int row = 0; row < 8; row++) {   // i refers to the digit, and row
            for (int column = 0; column < 8; column++) {   // j refers to the alphabet or letter, and column
                switch (row) {
                    case 0:     // in this case, it starts with X8, and all X8 are the black pieces
                        initializePieces(black, column, row);
                        break;
                    case 1:     // all X7 are the black pawns
                        chessBoard[row][column] = new Pawn(chessBoard, black, row, column));
                        break;
                    case 6:     // all X2 are the white pawns
                        chessBoard[row][column] = new Pawn(chessBoard, black, row, column);
                        break;
                    case 7:     // all the X1 are the white pieces
                        initializePieces(white, column, row);
                        break;
                }
            }
        }
    }

    private void initializeDigit() {
        digit['a'] = 0;
        digit['b'] = 1;
        digit['c'] = 2;
        digit['d'] = 3;
        digit['e'] = 4;
        digit['f'] = 5;
        digit['g'] = 6;
        digit['h'] = 7;
    }

//    @TODO
//    place a piece on the position, if there's opponent's piece, also have to capture it
    public boolean placePiece(ChessPiece piece, String position){
        return true;
    }

//    @TODO
//    given a position, and try to find if there is a piece on the chess board
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        return chessBoard[digit[position.charAt(0)]][position.charAt(1) - 1 - 48];
    }

//    given 2 index, and find the position of the position of the 2 index


//    @TODO
    public void move(String from, String to) throws IllegalMoveException {
    }

    public String toString() {
            String res = "┌─┬─┬─┬─┬─┬─┬─┬─┐\n";
            for (int i = 0; i < 8; i++) {
                res += "│";
                for (int j = 0; j < 8; j++) {
                    if (chessBoard[i][j] != null) {
                        res += chessBoard[i][j] + "│";
                    } else {
                        res += "  │";
                    }
                }
                if (i == 7) {
                    res += "\n└───────────────┘";
                    break;
                }

                res += "\n├─┼─┼─┼─┼─┼─┼─┼─┤\n";
            }
        return res;
    }
}
