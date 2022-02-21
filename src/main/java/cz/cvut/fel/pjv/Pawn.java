package cz.cvut.fel.pjv;

public class Pawn implements Piece {
    private int points;
    private char representation;
    private Color color;
    private Square myPosition;

    public Pawn(int points, char representation, Color color, Square myPosition) {
        this.points = points;
        this.representation = representation;
        this.color = color;
        this.myPosition = myPosition;
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

    public Square getMyPosition() {
        return myPosition;
    }

    public void setMyPosition(Square myPosition) {
        this.myPosition = myPosition;
    }

    @Override
    public Square[] PossibleMovement() {
        Board.
    }

    @Override
    public void Move() {
        Board.
    }
}
