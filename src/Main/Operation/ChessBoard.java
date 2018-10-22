package Main.Operation;

import Main.ChessPieces.*;
import Main.ChessPieces.ChessPiece.Color;
import Main.Exception.IllegalMoveException;
import Main.Exception.IllegalPositionException;
import Main.View.ChessboardFrame;

import java.util.ArrayList;

import static Main.ChessPieces.ChessPiece.Color.WHITE;
import static Main.ChessPieces.ChessPiece.Color.BLACK;

/**
 * @author Lu Han
 */
public class ChessBoard {
    private ChessPiece[][] board;
    private King kingB, kingW;
    private boolean whiteMove;
    private Record record;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        whiteMove = true;
        record = new Record();
    }

    //    this is a helper function to implements the chess pieces on the board for initialization
    private void initializePieces(Color color, int column, String position) {
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
                King king = new King(this, color);
                placePiece(king, position);
                if (color == BLACK)
                    kingB = king;
                else kingW = king;
                break;
            default:
                placePiece(new Queen(this, color), position);
        }
    }

    //    This method initializes the board to the standard chess opening state with indexing as shown in the figure. This method
// should use the constructors of the appropriate pieces, and call placePiece below to place the newly constructed pieces in the right position.
    public void initialize() {
//        To initialize piece into correct position, i.e.,a1 is the white rook etc. Refer to chess rules for details
        Color black = BLACK;
        Color white = WHITE;
        String position;
        for (int row = 0; row < 8; row++) {   // i refers to the digit, and row
            for (int column = 0; column < 8; column++) {   // j refers to the alphabet or letter, and column
                position = "" + (char) ('a' + column) + (7 - row + 1);
                if (row == 0) {  //  // in this case, it starts with X8, and all X8 are the black pieces
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

    private boolean validPosition(String position) {
        return position.matches("^[a-h][1-8]$");
    }

    //    This method tries to place the given piece at a given position, and returns true if successful, and false if there is
// already a piece of the same player in the given position or the position was illegal for any of the two reasons mentioned
// in the description of getPiece. If an opponent's piece exists, that piece is captured. If successful, this method should call
// an appropriate method in the ChessPiece class (i.e., setPosition) to set the piece's position
    public boolean placePiece(ChessPiece piece, String position) {
//        if the position that piece is going to be placed is not legal, just return false
        if (!validPosition(position))
            return false;

        int toLetter = position.charAt(0) - 'a';
        int toDigit = 7 - (position.charAt(1) - '1');
        try {
            ChessPiece toPiece = getPiece(position);
            // that to position can be empty or the chess piece's different, in that case, current chess piece is placed at that direction
            if (toPiece != null && toPiece.getColor() == piece.getColor()) { // if it's the same color, return false
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

    //This method checks if moving the piece from the fromPosition to toPosition is a legal move. Legality is defined based
// on the rules described above in Section 2.1. If the move is legal, it executes the move, changing the value of the
// board as needed. Otherwise, the stated exception is thrown.
    public void move(String from, String to, ChessboardFrame boardFrame) throws IllegalMoveException {
        ChessPiece fromPiece, toPiece;

        try {   // check if both position to see if they are legal
            fromPiece = getPiece(from);
            toPiece = getPiece(to);
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException("Check the position of " + from + " and " + to + ", the position may be illegal");
        }

        if (fromPiece == null) {    // try to move things from nothing, BTW, this is handled in GUI
            throw new IllegalMoveException(from + " Does not have any chess piece on it");
        }

        ArrayList<String> legalMove = fromPiece.legalMoves();

//        if there is other piece at to position, then it cannot be en Passant, or the current moving piece is not Pawn
//        if the piece meets 1st 2 conditions, then check if it's en Passant
        if (toPiece == null && fromPiece instanceof Pawn && ((Pawn) fromPiece).enPassant(to)) {
            String prevTo = (String) record.lastMove()[3];  // previous piece is set to empty
            board[7 - (prevTo.charAt(1) - '1')][prevTo.charAt(0) - 'a'] = null;
            int prevPos = posConv(prevTo);    // find previous moved Pawn's position, and remove that piece
            boardFrame.updatePiece(prevPos, null);  // as it's captured
//            keyword, the piece moved during en Passant, and the piece's original position
            record.writeRecord(new Object[]{"enPassant", record.lastMove()[0], prevTo});
        } else if (!legalMove.contains(to))    // check to position is a legal moves
            throw new IllegalMoveException("You cannot move from " + from + " to " + to + " since it is not a legal move");

        int fromPos = posConv(from);
        int toPos = posConv(to);
        if (placePiece(fromPiece, to)) {  // if move is successful, which should since it's a legal move
            fromPiece.move();   // fromPiece moved successfully
            // add record, the order is: fromPiece, toPiece, from, to. In the future, just need to place toPiece on to, fromPiece on from
            record.writeRecord(new Object[]{fromPiece, toPiece == null ? "" : toPiece, from, to});
            // set the original piece to null after move
            board[7 - (from.charAt(1) - '1')][from.charAt(0) - 'a'] = null;
            boardFrame.updatePiece(fromPos, toPos, fromPiece); // update GUI stuffs
        } else
            throw new IllegalMoveException("You cannot move from " + from + " to  " + to + " since both pieces may be the same color");

        if (toPiece instanceof King) {
            System.out.println("Game Over");
            boardFrame.gameOver();
        }

        pawnPromotion(fromPos, toPos, to, fromPiece, boardFrame);

        System.out.println(record);
    }

//    wrap up the method convert String pos to int Pos
    private int posConv(String pos) {
        int posRow = 7 - (pos.charAt(1) - '1');
        int posCol = pos.charAt(0) - 'a';
        return posRow * 8 + posCol;
    }

    //    On reaching the last rank, a pawn must immediately be exchanged, as part of the same move, for [either] a queen, a
// rook, a bishop, or a knight, of the same colour as the pawn, at the player's choice and without taking into account
// the other pieces still remaining on the chessboard. The effect of the promoted piece is immediate and permanent!
    private void pawnPromotion(int fromPos, int toPos, String to, ChessPiece toPiece, ChessboardFrame boardFrame) {
        int row = 7 - toPiece.getPosition().charAt(1) + '1';

        if (!(toPiece instanceof Pawn))     // if moved piece is not pawn, do nothing
            return;

        if ((toPiece.getColor() == BLACK && row != 7) || (toPiece.getColor() == WHITE && row != 0))
            return; // if the pawn hasn't reach the position, do nothing

//        0: queen, 1: bishop, 2: Knight, 3: Rook, the previous default value has been set to queen
        String option = boardFrame.pawnPromotion();
        ChessPiece piece = null;

        switch (option) {
            case "queen":
                piece = new Queen(this, toPiece.getColor());
                break;
            case "bishop":
                piece = new Bishop(this, toPiece.getColor());
                break;
            case "knight":
                piece = new Knight(this, toPiece.getColor());
                break;
            case "rook":
                piece = new Rook(this, toPiece.getColor());
                break;
        }

        try {
            piece.setPosition(to);  // illegal test has been set in the move, so just choose to ignore it
        } catch (IllegalPositionException ignored) {
        }

//        place piece will return false, manually place piece on the position
        board[row][to.charAt(0) - 'a'] = piece;
        boardFrame.updatePiece(fromPos, toPos, piece);
    }

    //    @TODO check if move leads to check
    private void check() {
    }

    //    @TODO check if move leads to checkmate
    private void checkmate() {
    }

    public String toString() {
        StringBuilder res = new StringBuilder(" ┌─┬─┬─┬─┬─┬─┬─┬─┐\n");
        for (int i = 0; i < 8; i++) {
            res.append(8 - i).append("│");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    res.append(board[i][j]).append("│");
                } else {
                    res.append("  │");
                }
            }
            if (i == 7) {
                res.append("\n └───────────────┘");
                res.append("\n   a   b   c   d   e   f   g   h");
                break;
            }
            res.append("\n ├─┼─┼─┼─┼─┼─┼─┼─┤\n");
        }
        return res.toString();
    }

    public Record getRecord() {
        return record;
    }
}
