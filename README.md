# Chess
A simple chess game implemented in Java.

## Features
- All possible moves of chess piece are determined.
- GUI is implemented therefore player can play the game on the screen.

## How to Run the Game
In the terminal, direct to the source file's location, then type
```$xslt
java -jar <name-of-the-jar-file-with-correct-version>.jar
```

Or simply double click jar file.

The initialized board should appeared on the board.

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extraFiles/runResult/v2_1.png)

Player can click the piece, and correspond position of the chosen square will pop up.

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extraFiles/runResult/v2_2.png)

After player makes move, the chess piece will moves to the correspond position as well.

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extraFiles/runResult/v2_3.png)

Player can move pieces arount and continue the game.

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extraFiles/runResult/v2_4.png)

## Project Structure
- Exception provides 2 exceptions that will be thrown in the game. For more details, refer to [Exception.md](https://github.com/GoldenaArcher/Chess/blob/master/src/Main/Exception/Exception.md)
- ChessPieces provides a basic attributes of a chess piece, such as color, position, its legal moves. For more details, refer to [ChessPieces.md](https://github.com/GoldenaArcher/Chess/blob/master/src/Main/ChessPieces/ChessPieces.md).
- Operation implements various functions which allow player to initialize the board, moves pieces on the board, etc. For more details, refer to [Operation.md](https://github.com/GoldenaArcher/Chess/tree/master/src/Main/Operation/Operation.md)
- Provides GUI(Graphical User Interface) for the application. For more details, refer to [View.md](https://github.com/GoldenaArcher/Chess/blob/master/src/Main/View/View.md)

## Project Contributors

* **[Lu Han](https://github.com/GoldenaArcher)** (lu.han9467@gmail.com)

## Reference

Started as one of the mini-projects of [CS414](http://www.cs.colostate.edu/~cs414). The course was originally asked students to implemented the moves of the chess pieces, and the test cases for the chess pieces.

The instruction of setting up coverage of tests were taken from past course of [cs314](http://www.cs.colostate.edu/~cs314). The original page was posted on Piazza.

Logo is found on [Kung Fu Chess](https://www.kfchess.com), and chess pieces' images are found on [Icons8](https://icons8.com/icon/set/Chess/all) and/or [marcelk.net](https://marcelk.net/chess/pieces/), which indicates that ***free for personal non-commercial use***.