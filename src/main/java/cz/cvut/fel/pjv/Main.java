package cz.cvut.fel.pjv;

public class Main {
    public static void main(String[] args) {
    Board board = new Board();
    board.initializeBoard();
    board.printBoard();

    Piece piece = board.pickPiece(0,6);

    board.movePiece(piece,0,7);

    System.out.println();
    board.printBoard();

    }
}
