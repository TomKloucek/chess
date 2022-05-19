package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.pieces.IPiece;

/**
 * Square is a class for  modeling the board into two-dimensional array.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Square implements Comparable<Square> {
    private int x,y;
    private IPiece IPiece;

    /**
     * A constructor of Square.
     *
     * @param x x coordinate of Square
     * @param x y coordinate of Square
     * @param IPiece piece which is on this square
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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
