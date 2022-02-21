package cz.cvut.fel.pjv;

public class Board {
    private Square[][] board = new Square[8][8];

    public Square[][] getBoard() {
        return board;
    }

    public void initializeBoard(){

        Pawn p = new Pawn(3, ' ', Color.WHITE, 2, 0);

        board[0][0] = new Square(0, 0, null);
        board[0][1] = new Square(0,1, null);
        board[0][2] = new Square(0,2, p);
        board[0][3] = new Square(0,3, null);
        board[0][4] = new Square(0,4, null);
        board[0][5] = new Square(0,5, null);
        board[0][6] = new Square(0,6, null);
        board[0][7] = new Square(0,7, null);

        for (int i = 1; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Square(i,j,null);
            }
        }

        board[1][1].setPiece(new Pawn(2, ' ', Color.BLACK, 1,1));
        board[1][3].setPiece(new Pawn(2, ' ', Color.BLACK, 3,1));
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
            this.board[chosen.getY()][chosen.getX()].setPiece(null);
            this.board[y][x].setPiece(chosen);
            chosen.Move(x,y);
        }
        else {
            System.out.println("Nezadal jsi spravne hodnoty");
        }
    }

}
