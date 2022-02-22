package cz.cvut.fel.pjv;

public class Board {
    private Square[][] board = new Square[8][8];

    public Square[][] getBoard() {
        return board;
    }

    public void initializeBoard(){

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i,j,null);
            }
        }

        board[6][0].setPiece(new Pawn(' ',Color.WHITE,0,6,true));

    }
    public void printBoard(){
        System.out.println("------------------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getPiece() != null) {
                    if (j == 0) {
                        System.out.print("| "+board[i][j].getPiece()+" |");
                    }
                    else {
                        System.out.print(" "+board[i][j].getPiece()+" |");
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
        return this.board[y][x].getPiece();
    }

    public void movePiece(Piece chosen, int x, int y) {
        if (chosen.PossibleMovement(this).contains(board[y][x])) {
            if ((y == 0 || y == 7) && chosen instanceof Pawn) {
                this.board[chosen.getY()][chosen.getX()].setPiece(null);
                if (y == 0 && chosen.getColor() == Color.BLACK) {
                    board[y][x].setPiece(Pawn.PromoteTo((Pawn) chosen));
                }
                if (y == 7 && chosen.getColor() == Color.WHITE) {
                    board[y][x].setPiece(Pawn.PromoteTo((Pawn) chosen));
                }
                board[y][x].getPiece().Move(x,y);
            }
            else {
                this.board[chosen.getY()][chosen.getX()].setPiece(null);
                this.board[y][x].setPiece(chosen);
                chosen.Move(x, y);
            }
        }
        else {
            System.out.println("Nezadal jsi spravne hodnoty");
        }
    }

}
