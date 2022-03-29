package cz.cvut.fel.pjv.pieces;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public interface Piece {

    public int getPoints();
    public Color getColor();

    public void setX(int x);
    public int getX();

    public void setY(int y);
    public int getY();

    public ArrayList<Square> PossibleMovement(Board board);

    public void Move(int x, int y);

    public String toString();
}


