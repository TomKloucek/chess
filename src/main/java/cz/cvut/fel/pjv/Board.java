package cz.cvut.fel.pjv;

public class Board {
    private Square[][] board = new Square[8][8];
    public void initializeBoard(){
        board[0][0] = new Square(0, 0, new Pawn(3, ' ', Color.WHITE, 0, 0));
    }
    public void printBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(board[i][j].getPiece());
            }
        }
    }

}
