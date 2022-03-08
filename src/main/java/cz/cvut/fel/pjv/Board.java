package cz.cvut.fel.pjv;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private Square[][] board;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    public Board() {
        this.board = new Square[8][8];
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public ArrayList<Piece> getPieces(Color color) {
        if (color == Color.WHITE) {
            return whitePieces;
        }
        else {
            return blackPieces;
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void initializeBoard(){

        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j,i,null);
            }
        }

        // WHITE PLAYER
        putPiece(0,0,"Rook",Color.WHITE);
        putPiece(1,0,"Knight",Color.WHITE);
        putPiece(2,0,"Bishop",Color.WHITE);
        putPiece(3,0,"Queen",Color.WHITE);
        putPiece(4,0,"King",Color.WHITE);
        putPiece(5,0,"Bishop",Color.WHITE);
        putPiece(6,0,"Knight",Color.WHITE);
        putPiece(7,0,"Rook",Color.WHITE);

        for (int i = 0; i < 8; i++) {
            putPiece(i,1,"Pawn",Color.WHITE);
        }

        // BLACK PLAYER
        for (int i = 0; i < 8; i++) {
            putPiece(i,6,"Pawn",Color.BLACK);
        }

        putPiece(0,7,"Rook",Color.BLACK);
        putPiece(1,7,"Knight",Color.BLACK);
        putPiece(2,7,"Bishop",Color.BLACK);
        putPiece(3,7,"Queen",Color.BLACK);
        putPiece(4,7,"King",Color.BLACK);
        putPiece(5,7,"Bishop",Color.BLACK);
        putPiece(6,7,"Knight",Color.BLACK);
        putPiece(7,7,"Rook",Color.BLACK);
    }

    public void printBoard(){
        System.out.println("---------------------------------------------------------");
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
                        System.out.print("      |");
                    }
                }
            }
            System.out.println("\n---------------------------------------------------------");
        }
    }

    public void putPiece(int x, int y, String type, Color color) {
        Piece piece = switch (type) {
            case "Pawn" -> new Pawn(color, x, y);
            case "Knight" -> new Knight(color, x, y);
            case "Queen" -> new Queen(color, x, y);
            case "King" -> new King(color, x, y);
            case "Rook" -> new Rook(color, x, y);
            case "Bishop" -> new Bishop(color, x, y);
            default -> null;
        };
        if (color == Color.WHITE) {
            whitePieces.add(piece);
        }
        else {
            blackPieces.add(piece);
        }
        board[x][y].setPiece(piece);
    }

    public Piece pickPiece(int x, int y) {
        return this.board[x][y].getPiece();
    }

    public boolean movePiece(Piece chosen, int x, int y) {
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
            // EN PASSANT
//            else if(chosen instanceof Pawn) {
//                int way = chosen.getColor()==Color.WHITE ? 1:-1;
//                Pawn enPassant = (Pawn) this.board[x][y-way].getPiece();
//                if (Helpers.MoveInBoard(chosen.getX()+1, chosen.getY()+way)) {
//                    if (enPassant != null && enPassant.movedTwoSquares == true){
//                        this.board[chosen.getX()][chosen.getY()].setPiece(null);
//                        this.board[x][y-way].setPiece(null);
//                        this.board[x][y].setPiece(chosen);
//                        return true;
//                    }
//                    else if (Helpers.MoveInBoard(chosen.getX()-1, chosen.getY()+way)) {
//                        if (enPassant != null && enPassant.movedTwoSquares == true) {
//                            this.board[chosen.getX()][chosen.getY()].setPiece(null);
//                            this.board[x][y-way].setPiece(null);
//                            this.board[x][y].setPiece(chosen);
//                            return true;
//                        }
//                    }
//                }
//                this.board[chosen.getX()][chosen.getY()].setPiece(null);
//                this.board[x][y].setPiece(chosen);
//                chosen.Move(x, y);
//                return true;

//            }
            else {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y].setPiece(chosen);
                chosen.Move(x, y);
                return true;
            }
        }
        else {
            System.out.println("Nezadal jsi spravne hodnoty");
            return false;
        }
        return false;
    }

    private Piece PromoteTo(Pawn pawn) {
        String answer = "Queen";
        return switch (answer) {
            case "Rook" -> new Rook(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Queen" -> new Queen(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Bishop" -> new Bishop(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Knight" -> new Knight(pawn.getColor(), pawn.getX(), pawn.getY());
            default -> null;
        };
    }

    public Piece getKing(Color color) {
        if (color == Color.WHITE) {
            for (Piece piece: this.getPieces(Color.WHITE)) {
                if (piece.getPoints() == Integer.MAX_VALUE) {
                    return piece;
                }
            }
        }
        else {
            for (Piece piece: this.getPieces(Color.BLACK)) {
                if (piece.getPoints() == Integer.MAX_VALUE) {
                    return piece;
                }
            }
        }
        return null;
    }

    public ArrayList<Square> getEveryPossibleMoves(ArrayList<Piece> pieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (Piece piece: pieces) {
            moves.addAll(piece.PossibleMovement(this));
        }
        return moves;
    }

    public boolean whiteInCheck() {
        Piece king = getKing(Color.WHITE);
        return getEveryPossibleMoves(this.getPieces(Color.BLACK)).contains(getBoard()[king.getX()][king.getY()]);
    }

    public boolean blackInCheck() {
        Piece king = getKing(Color.BLACK);
        return getEveryPossibleMoves(this.getPieces(Color.WHITE)).contains(getBoard()[king.getX()][king.getY()]);
    }

    public boolean inCheck(Color color) {
        return blackInCheck() || whiteInCheck();
    }

}
