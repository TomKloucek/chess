package cz.cvut.fel.pjv.models.pieces;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public interface IPiece {

    public int getPoints();
    public Color getColor();

    public void setX(int x);
    public int getX();

    public void setY(int y);
    public int getY();

    public ArrayList<Square> possibleMovement(Board board);
    public ArrayList<Square> getAttackMovesForKingMove(Board board);

    public void Move(int x, int y);

    public String toString();
}


