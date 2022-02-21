package cz.cvut.fel.pjv;

public class Main {
    public static void main(String[] args) {
    Board board = new Board();
    board.initializeBoard();
    board.printBoard();

    Piece piece = board.pickPiece(2,0);

    System.out.println(piece.PossibleMovement(board));

    board.movePiece(piece, 1,1);

    System.out.println();
    board.printBoard();

    }
}
