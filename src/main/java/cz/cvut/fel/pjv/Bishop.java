package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Bishop implements Piece {
    private int points;
    private Color color;
    private int x;
    private int y;

    public Bishop(Color color, int x, int y) {
        this.points = 3;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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
        return color;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }
}
