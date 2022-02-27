package cz.cvut.fel.pjv;

import java.util.Scanner;

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
        board[4][4].setPiece(new Pawn(Color.WHITE,4,4, true));
        board[5][5].setPiece(new Pawn(Color.BLACK,5,5, true));
        board[3][5].setPiece(new Pawn(Color.BLACK,3,5, true));
        //board[2][3].setPiece(new Bishop(Color.BLACK,2,3));
        //board[6][0].setPiece(new Pawn(Color.WHITE,6,0,true));

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
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
                }
                if (y == 7 && chosen.getColor() == Color.WHITE) {
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
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

    public Piece PromoteTo(Pawn pawn) {
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        return switch (answer) {
            case "Rook" -> new Rook(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Queen" -> new Queen(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Bishop" -> new Bishop(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Knight" -> new Knight(pawn.getColor(), pawn.getX(), pawn.getY());
            default -> null;
        };
    }

}
