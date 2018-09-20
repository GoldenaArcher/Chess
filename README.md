# Chess
A simple chess game implemented in Java.

## Features
- All possible moves of chess piece are determined.

## How to Run the Game
In the terminal, direct to the source file's location, then type
```$xslt
java -jar <name-of-the-jar-file-with-correct-version>.jar
```
The initialized board should appeared on the board, and user can move the piece by entering the corresponding position on the terminal.

![example of running](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/example1.png)

After giving the position to move, and the board will be printed again:

![example of running2](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/example2.png)

## Project Structure
- ChessPieces provides a basic attributes of a chess piece, such as color, position, its legal moves. For more details, refer to [ChessPieces.md](https://github.com/GoldenaArcher/Chess/blob/master/src/Main/ChessPieces/ChessPieces.md).
- Operation implements various functions which allow player to initialize the board, moves pieces on the board, etc. For more details, refer to [Operation.md](https://github.com/GoldenaArcher/Chess/tree/master/src/Main/Operation/Operation.md)
- Provides GUI(Graphical User Interface) for the application. For more details, refer to [View.md](https://github.com/GoldenaArcher/Chess/blob/master/src/Main/View/View.md)

## Project Contributors

* **[Lu Han](https://github.com/GoldenaArcher)** (lu.han9467@gmail.com)

## Reference

Started as one of the mini-projects of [CS414](http://www.cs.colostate.edu/~cs414). The course was originally asked students to implemented the moves of the chess pieces, and the test cases for the chess pieces.

The instruction of setting up coverage of tests were taken from past course of [cs314](http://www.cs.colostate.edu/~cs314). The original page was posted on Piazza.

Logo is found on [Kung Fu Chess](https://www.kfchess.com), and chess pieces' images are found on [Icons8](https://icons8.com/icon/set/Chess/all) and/or [marcelk.net](https://marcelk.net/chess/pieces/), which indicates that ***free for personal non-commercial use***.