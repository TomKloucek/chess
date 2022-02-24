package cz.cvut.fel.pjv;

public class Board {
    private Square[][] board = new Square[8][8];

    public Square[][] getBoard() {
        return board;
    }

    public void initializeBoard(){

        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j,i,null);
            }
        }
//        board[0][2].setPiece(new Bishop(Color.WHITE,0,2));
        board[6][0].setPiece(new Pawn(Color.WHITE,6,0,true));

    }
    public void printBoard(){
        System.out.println("------------------------------------------");
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i].getPiece() != null) {
                    if (j == 0) {
                        System.out.print("| "+board[j][i].getPiece()+" |");
                    }
                    else {
                        System.out.print(" "+board[j][i].getPiece()+" |");
                    }
                }
                else {
                    if (j == 0) {
                        System.out.print("|     |");
                    }
                    else {
                        System.out.print("    |");
                    }
                }
            }
            System.out.println("\n------------------------------------------");
        }
    }

    public Piece pickPiece(int x, int y) {
        return this.board[x][y].getPiece();
    }

    public void movePiece(Piece chosen, int x, int y) {
        if (chosen.PossibleMovement(this).contains(board[x][y])) {
            if ((y == 0 || y == 7) && chosen instanceof Pawn) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                if (y == 0 && chosen.getColor() == Color.BLACK) {
                    board[x][y].setPiece(Pawn.PromoteTo((Pawn) chosen));
                }
                if (y == 7 && chosen.getColor() == Color.WHITE) {
                    board[x][y].setPiece(Pawn.PromoteTo((Pawn) chosen));
                }
                board[x][y].getPiece().Move(x,y);
            }
            else {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y].setPiece(chosen);
                chosen.Move(x, y);
            }
        }
        else {
            System.out.println("Nezadal jsi spravne hodnoty");
        }
    }

}
