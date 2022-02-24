package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class King implements  Piece {

    @Override
    public int getPoints() {
        return 0;
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
    //TODO toString King
//    @Override
//    public String toString() {
//        return notation;
//    }
}
