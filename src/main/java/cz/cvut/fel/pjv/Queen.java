package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Queen implements Piece {
    private int points;
    private char representation;
    private Color color;
    private int x;
    private int y;

    public Queen(char representation, Color color, int x, int y) {
        this.points = 9;
        this.representation = representation;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
        return null;
    }

    @Override
    public void Move(int x, int y) {

    }

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public String toString() {
        String notation = "Q";
        if (color == Color.BLACK){
            notation += "B" + YTranslate() + (getX()+1);
        }
        else {
            notation += "W" + YTranslate() + (getX()+1);
        }
        return notation;
    }

    @Override
    public String YTranslate() {
        return switch (getY() + 1) {
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
}
