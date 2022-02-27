package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Queen implements Piece {
    private final int points;
    private final Color color;
    private int x;
    private int y;

    public Queen(Color color, int x, int y) {
        this.points = 9;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getPoints() {
        return points;
    }



    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public void setX(int x) {
    }
    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void setY(int y) {
    }
    @Override
    public int getY() {
        return 0;
    }



    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
        return null;
    }

    @Override
    public void Move(int x, int y) {

    }

    @Override
    public String toString() {
        String notation = "Q";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }

}
