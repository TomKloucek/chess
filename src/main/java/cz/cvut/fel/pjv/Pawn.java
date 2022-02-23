package cz.cvut.fel.pjv;

import java.util.ArrayList;
import java.util.Scanner;

public class Pawn implements Piece {
    private int points;
    private char representation;
    private Color color;
    private int x;
    private int y;
    private boolean moved;

    public Pawn(char representation, Color color, int x, int y) {
        this.points = 1;
        this.color = color;
        this.x = x;
        this.y = y;
        this.moved = false;
    }

    public Pawn(Color color, int x, int y, boolean moved) {
        this.points = 1;
        this.color = color;
        this.x = x;
        this.y = y;
        this.moved = moved;
    }

    public int getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        if (this.color == Color.BLACK) {
            if (board.getBoard()[this.getY()-1][this.getX()].getPiece() == null) {
                possibilities.add(board.getBoard()[this.getY()-1][this.getX()]);
            }
            // TODO naimplementovat capture i pro cerneho
        }
        else {
            if (!this.moved) {
                if (board.getBoard()[this.getY() + 2][this.getX()].getPiece() == null) {
                    possibilities.add(board.getBoard()[this.getY() + 2][this.getX()]);
                }
            }
            if (board.getBoard()[this.getY()+1][this.getX()].getPiece() == null) {
                if (this.getY() +1 <= 7) {
                    possibilities.add(board.getBoard()[this.getY() + 1][this.getX()]);
                }
            }
            if (this.getX() == 0) {
                if (board.getBoard()[this.getY()+1][this.getX()+1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()+1].getPiece().getColor() == Color.BLACK) {
                    if (this.getY()+1 <= 7) {
                        possibilities.add(board.getBoard()[this.getY() + 1][this.getX() + 1]);
                    }
                }
            }
            else if (this.getX() == 7) {
                if (board.getBoard()[this.getY()+1][this.getX()-1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()-1].getPiece().getColor() == Color.BLACK) {
                    if (this.getY()+1 <= 7) {
                        possibilities.add(board.getBoard()[this.getY() + 1][this.getX() - 1]);
                    }
                }
            }
            else {
                if (board.getBoard()[this.getY()+1][this.getX()+1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()+1].getPiece().getColor() == Color.BLACK) {
                    if (this.getY()+1 <= 7) {
                        possibilities.add(board.getBoard()[this.getY() + 1][this.getX() + 1]);
                    }
                }
                if (board.getBoard()[this.getY()+1][this.getX()-1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()-1].getPiece().getColor() == Color.BLACK) {
                    if (this.getY()+1 <= 7) {
                        possibilities.add(board.getBoard()[this.getY() + 1][this.getX() - 1]);
                    }
                }
            }
        }
        return possibilities;
    }

    @Override
    public void Move(int x, int y) {
        this.moved = true;
       this.setX(x);
       this.setY(y);
    }

    @Override
    public String toString() {
        String notation = "";
        if (color == Color.BLACK){
            notation += "B" + Helpers.YTranslate(this.getY()) + (getX()+1);
        }
        else {
            notation += "W" + Helpers.YTranslate(this.getY()) + (getX()+1);
        }
        return notation;
    }

    public static Piece PromoteTo(Pawn pawn) {
        // DOCASNE RESENI
        Scanner sc = new Scanner(System.in);
        String answer = sc.next();
        return switch (answer) {
            case "Rook" -> new Rook(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Queen" -> new Queen(pawn.getColor(), pawn.getX(), pawn.getY());
            case "Bishop" -> new Bishop();
            case "Knight" -> new Knight();
            default -> null;
        };
    }
}
