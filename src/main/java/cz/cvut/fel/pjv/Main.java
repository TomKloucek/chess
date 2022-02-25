package cz.cvut.fel.pjv;

public class Main {
    public static void main(String[] args) {
    Board board = new Board();
    board.initializeBoard();
    board.printBoard();

    Piece piece = board.pickPiece(3,1);
        System.out.println(piece.PossibleMovement(board));
    board.movePiece(piece,6,4);

    System.out.println();
    board.printBoard();

    }
}
