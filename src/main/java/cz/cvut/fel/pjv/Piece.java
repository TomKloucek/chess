package cz.cvut.fel.pjv;

import java.util.ArrayList;

public interface Piece {

    // metody
    public ArrayList<Square> PossibleMovement(Board board);

    public void Move(int x, int y);

    public Color getColor();
    public int getY();
    public int getX();
    public String YTranslate();
}


