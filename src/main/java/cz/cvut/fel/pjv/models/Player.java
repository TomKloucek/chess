package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.pieces.IPiece;

import java.util.ArrayList;

public class Player {
    private Color color;
    private ArrayList<IPiece> IPieces;

    public Player(Color color, ArrayList<IPiece> IPieces) {
        this.color = color;
        this.IPieces = IPieces;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<IPiece> getPieces() {
        return IPieces;
    }

    public void setPieces(ArrayList<IPiece> IPieces) {
        this.IPieces = IPieces;
    }
}
