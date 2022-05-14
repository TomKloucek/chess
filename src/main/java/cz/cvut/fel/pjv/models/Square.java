package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.pieces.IPiece;

public class Square implements Comparable<Square> {
    private int x,y;
    private IPiece IPiece;

    public Square(int x, int y, IPiece IPiece) {
        this.x = x;
        this.y = y;
        this.IPiece = IPiece;
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

    public void setPiece(IPiece IPiece) {
        this.IPiece = IPiece;
    }

    public IPiece getPiece() {
        return IPiece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "x=" + x +
                ", y=" + y +
                ", piece=" + IPiece +
                '}';
    }

    @Override
    public int compareTo(Square o) {
        return this.getPiece().getPoints() - o.getPiece().getPoints();
    }
}
