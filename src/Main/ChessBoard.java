package Main;

import Main.ChessPieces.*;

import java.util.ArrayList;

/**
 * @author Lu Han
 */
public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
    }

//    this is a helper function to implements the chess pieces on the board for initialization
    private void initializePieces(ChessPiece.Color color, int column, String position) {
        switch (column) {
            case 0:
            case 7:
                placePiece(new Rook(this, color), position);
                break;
            case 1:
            case 6:
                placePiece(new Knight(this, color), position);
                break;
            case 2:
            case 5:
                placePiece(new Bishop(this, color), position);
                break;
            case 4:
                placePiece(new King(this, color), position);
                break;
            default:
                placePiece(new Queen(this, color), position);
        }
    }

//    This method initializes the board to the standard chess opening state with indexing as shown in the figure. This method
// should use the constructors of the appropriate pieces, and call placePiece below to place the newly constructed pieces in the right position.
    public void initialize() {
//        To initialize piece into correct position, i.e.,a1 is the white rook etc. Refer to chess rules for details
        ChessPiece.Color black = ChessPiece.Color.BLACK;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        String position;
        for (int row = 0; row < 8; row++) {   // i refers to the digit, and row
            for (int column = 0; column < 8; column++) {   // j refers to the alphabet or letter, and column
                position = "" + (char)('a' + column) + (7 - row + 1);
                if (row == 0){  //  // in this case, it starts with X8, and all X8 are the black pieces
                    initializePieces(black, column, position);
                } else if (row == 1) { // all X7 are the black pawns
                    placePiece(new Pawn(this, black), position);
                } else if (row == 6) { // all X2 are the white pawns
                    placePiece(new Pawn(this, white), position);
                } else if (row == 7)  // all the X1 are the white pieces
                    initializePieces(white, column, position);
            }
        }
    }

    private boolean validPosition(String position){
        if (position.length() != 2)
            return false;

        int letter = position.charAt(0) - 'a';
        int digit = position.charAt(1) - '1';
        return letter <= 7 && letter >= 0 && digit >= 0 && digit <= 7;
    }

//    This method tries to place the given piece at a given position, and returns true if successful, and false if there is
// already a piece of the same player in the given position or the position was illegal for any of the two reasons mentioned
// in the description of getPiece. If an opponent's piece exists, that piece is captured. If successful, this method should call
// an appropriate method in the ChessPiece class (i.e., setPosition) to set the piece's position
    public boolean placePiece(ChessPiece piece, String position){
//        if the position that piece is going to be placed is not legal, just return false
        if (!validPosition(position))
            return false;

        int toLetter = position.charAt(0) - 'a';
        int toDigit = 7 - (position.charAt(1) - '1');
        try {
            ChessPiece toPiece = getPiece(position);
            // that to position can be empty or the chess piece's different, in that case, current chess piece is placed at that direction
            if (toPiece != null && toPiece.getColor() == piece.getColor()){ // if it's the same color, return false
                return false;
            } else {    // either put on the empty place or capture that piece
                board[toDigit][toLetter] = piece;
                piece.setPosition(position);
                return true;
            }
        } catch (IllegalPositionException e) {
            System.out.println("Unable to place " + piece.getColor() + " " + piece.getClass() + " to position " + position);
            return false;
        }
    }

//    given a position, and try to find if there is a piece on the chess board
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (validPosition(position)) {
            return board[7 - (position.charAt(1) - '1')][position.charAt(0) - 'a'];
        } else {
            throw new IllegalPositionException("Position is illegal, it must be between a1 to h8");
        }
    }

//    @TODO need to finish legal moves for other pieces before testing, and test after done with other legal moves implementation
//This method checks if moving the piece from the fromPosition to toPosition is a legal move. Legality is defined based
// on the rules described above in Section 2.1. If the move is legal, it executes the move, changing the value of the
// board as needed. Otherwise, the stated exception is thrown.
    public void move(String from, String to) throws IllegalMoveException {
        ChessPiece fromPiece, toPiece;

        try {
            fromPiece = getPiece(from);
            toPiece = getPiece(to);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException("Check the position of " + from + " and " + to + ", the position may be illegal");
        }

        ArrayList<String> legalMove = fromPiece.legalMoves();

        if (!legalMove.contains(to))
            throw new IllegalMoveException("You cannot move from " + from + " to " + to + " since it is not a legal move");

        if (placePiece(fromPiece, to))  // if move is successful
            board[7 - (from.charAt(1) - '1')][from.charAt(0) - 'a'] = null;   // set the original piece to null after move
        else
            throw new IllegalMoveException("You cannot move from " + from + " to " + to + " since both pieces may be the same color");
    }

    public String toString() {
            String res = "┌─┬─┬─┬─┬─┬─┬─┬─┐\n";
            for (int i = 0; i < 8; i++) {
                res += "│";
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] != null) {
                        res += board[i][j] + "│";
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
