package cz.cvut.fel.pjv;

public class Pawn implements Piece {
    private int points;
    private char representation;
    private Color color;
    private int x;
    private int y;

    public Pawn(int points, char representation, Color color, int x, int y) {
        this.points = points;
        this.representation = representation;
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public char getRepresentation() {
        return representation;
    }

    public void setRepresentation(char representation) {
        this.representation = representation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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
    public Square[] PossibleMovement() {
        return null;
    }

    @Override
    public void Move() {
       return;
    }

    @Override
    public String toString() {
        String notation = "";
        if (color == Color.BLACK){
            notation += "B" + getY() + getX();
        }
        else {
            notation += "W" + getY() + getX();
        }
        return notation;
    }
}
