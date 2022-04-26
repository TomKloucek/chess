package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.pieces.Piece;

public class Square implements Comparable<Square> {
    private int x,y;
    private Piece piece;

    public Square(int x, int y,Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x+1;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", piece=" + piece +
                '}';
    }

    @Override
    public int compareTo(Square o) {
        return this.getPiece().getPoints() - o.getPiece().getPoints();
    }
}
