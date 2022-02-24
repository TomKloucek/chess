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
    public int getPoints() {
        return 0;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setX(int x) {

    }
    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {

    }
    @Override
    public int getY() {
        return y;
    }


    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
        return null;
    }

    @Override
    public void Move(int x, int y) {

    }
    //TODO toString Rook
//    @Override
//    public String toString() {
//        return notation;
//    }


}
