## Operation

  This package provides the major functions of the game.For instance, initializing the chessboard, place the chess pieces
on the chessboard, move the chess pieces on the board, etc.
   
### Function 

#### Illegal exception.
* Illegal Position Exception - throw exception when the position received is not valid.

* Illegal Move Exception - throw exception when play tries to move piece that could not be moved.

  1. If player try to move piece from a position that does not contain piece.
  
  2. If the player tries to move the piece against the rule.
  
     - Try to move piece pass some other piece blocks the move.
    
     - Try to capture the piece with same color.
     
     - Try to move the piece to the position that is not included in the legal move.
     
### Chessboard
