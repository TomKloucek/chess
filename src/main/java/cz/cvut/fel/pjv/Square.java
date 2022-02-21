package cz.cvut.fel.pjv;

public class Square {
    private int x,y;
    private Piece piece;

    public Square(int y, int x,Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }

    public int getX() {
        return x+1;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getY() {
        return switch (this.y + 1) {
            case 1 -> "a";
            case 2 -> "b";
            case 3 -> "c";
            case 4 -> "d";
            case 5 -> "e";
            case 6 -> "f";
            case 7 -> "g";
            case 8 -> "h";
            default -> "";
        };

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
}
