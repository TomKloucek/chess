package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.pieces.IPiece;

import java.util.ArrayList;

/**
 * Player is a class for more convenient game handling.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Player {
    private Color color;
    private ArrayList<IPiece> IPieces;

    /**
     * A constructor of Player.
     *
     * @param color player color
     * @param IPieces players pieces
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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
