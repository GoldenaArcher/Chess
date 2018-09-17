## Operation

  This package provides the major functions of the game.For instance, initializing the chessboard, place the chess pieces
on the chessboard, move the chess pieces on the board, etc.
   
### Function 

#### Illegal exception.
* Illegal Position Exception - refer to invalid position provided by player.

* Illegal Move Exception - refer to the invalid move of chess pieces.

  1. If player try to move piece from a position that does not contain piece.
  
  2. If the player tries to move the piece against the rule.
  
     - Try to move piece pass some other piece blocks the move.
    
     - Try to capture the piece with same color.
     
     - Try to move the piece to the position that is not included in the legal move.
     
### Chessboard

* Implemented the details functions to catch and throw exceptions listed above.

* Initialize the chess board in the format listed below:

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/example1.png)

* Aak user to give the position and therefore move the pieces on the board:

![example of running2](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/example2.png)

* Display the chessboard on the console after move each time

* Return piece of the given position..