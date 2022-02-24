package cz.cvut.fel.pjv;

public class Main {
    public static void main(String[] args) {
    Board board = new Board();
    board.initializeBoard();
    board.printBoard();

    Piece piece = board.pickPiece(6,0);
    board.movePiece(piece,6,1);

    System.out.println();
    board.printBoard();

    }
}
