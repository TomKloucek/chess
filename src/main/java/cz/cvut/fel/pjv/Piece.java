package cz.cvut.fel.pjv;

import java.util.ArrayList;

public interface Piece {

    // metody


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


