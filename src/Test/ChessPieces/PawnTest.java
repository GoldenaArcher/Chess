package Test.ChessPieces;

import Main.ChessBoard;
import Main.ChessPieces.ChessPiece;
import Main.ChessPieces.Pawn;
import Main.IllegalPositionException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PawnTest {
    ChessBoard board = new ChessBoard();

    @Test
    public void legalMoves() {
        board.placePiece(new Pawn(board, ChessPiece.Color.BLACK), "a7");
        ChessPiece curr;
        try {
            curr = board.getPiece("a7");
            ArrayList<String> res = curr.legalMoves();
            for (String str: curr.legalMoves()) {
                System.out.println(str);
            }
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }
}