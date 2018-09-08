package Main;

import Main.ChessPieces.*;

public class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initialize();
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
    private void initialize() {
//        To initialize piece into correct position, i.e.,a1 is the white rook etc. Refer to chess rules for details
        ChessPiece.Color black = ChessPiece.Color.BLACK;
        ChessPiece.Color white = ChessPiece.Color.WHITE;
        String position;
        for (int row = 0; row < 8; row++) {   // i refers to the digit, and row
            for (int column = 0; column < 8; column++) {   // j refers to the alphabet or letter, and column
                position = "" + (char)('a' + column) + (row + 1);
                if (row == 0){  //  // in this case, it starts with X8, and all X8 are the black pieces
                    initializePieces(black, column, position);
                } else if (row == 1) { // all X7 are the black pawns
                    placePiece(new Pawn(this, black), position);
                } else if (row == 6) { // all X2 are the white pawns
                    placePiece(new Pawn(this, white), position);
                } else if (row == 7) { // all the X1 are the white pieces
                    initializePieces(white, column, position);
                }
            }
        }
    }
    
//    This method tries to place the given piece at a given position, and returns true if successful, and false if there is
// already a piece of the same player in the given position or the position was illegal for any of the two reasons mentioned
// in the description of getPiece. If an opponent's piece exists, that piece is captured. If successful, this method should call
// an appropriate method in the ChessPiece class (i.e., setPosition) to set the piece's position
    public boolean placePiece(ChessPiece piece, String position){
        String fromPosition = piece.getPosition();
        int fromLetter = fromPosition.charAt(0) - 'a', toLetter = position.charAt(0) - 'a';
        int fromDigit = fromPosition.charAt(1) - '1', toDigit = (int)position.charAt(1) - '1';
        try {
            ChessPiece toPiece = getPiece(position);
            // that to position can be empty or the chess piece's different, in that case, current chess piece is placed at that direction
            if (toPiece == null){
                board[toDigit][toLetter] = piece;
                piece.setPosition(position);
            } else if (toPiece.getColor() != piece.getColor()) {
                board[fromDigit][fromLetter] = null;
                board[toDigit][toLetter] = piece;
                piece.setPosition(position);
            } else  // if there already exists a chess piece with same color
                return false;
        } catch (IllegalPositionException e) {
            System.out.println(e.getMessage());
            System.out.println("Unable to place " + piece.getColor() + " " + piece.getClass() + " to position " + position);
            return false;
        }
        return true;
    }

//    @TODO
//    given a position, and try to find if there is a piece on the chess board
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        return null;
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
