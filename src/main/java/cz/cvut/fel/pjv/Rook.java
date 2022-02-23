package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Rook implements Piece {
    private int points;
    private Color color;
    private int x;
    private int y;
    private boolean atLeastOnceMoved;

    public Rook(Color color, int x, int y) {
        this.points = 5;
        this.color = color;
        this.x = x;
        this.y = y;
        this.atLeastOnceMoved = false;
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
        return this.color;
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
