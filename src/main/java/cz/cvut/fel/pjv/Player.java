package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Player {
    private Color color;
    private ArrayList<Piece> pieces;

    public Player(Color color, ArrayList<Piece> pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
}
