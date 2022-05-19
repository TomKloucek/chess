package cz.cvut.fel.pjv.models.pieces;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

/**
 * IPiece is an interface which is a pattern for all pieces.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public interface IPiece {

    /**
     * This method returns number of points corresponding to the given piece.
     *
     * @return number of piece points
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public int getPoints();
    /**
     * This method returns color of the given piece.
     *
     * @return color of piece
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public Color getColor();

    /**
     * This method sets x of piece.
     *
     * @param x x to be set
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void setX(int x);

    /**
     * This method gets x of piece.
     *
     * @return number of x coordinate
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public int getX();

    /**
     * This method sets x of piece.
     *
     * @param y y to be set
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void setY(int y);

    /**
     * This method gets y of piece.
     *
     * @return number of y coordinate
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public int getY();

    /**
     * This method returns all possible moves of the given piece.
     *
     * @param board this parameter is a board of the game
     *
     * @return all possible moves of piece
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> possibleMovement(Board board);

    /**
     * This method returns all possible moves which could possibly endanger the opponents king.
     *
     * @param board this parameter is a board of the game
     *
     * @return all possible moves of piece which endanger the opponents king
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> getAttackMovesForKingMove(Board board);

    /**
     * This method moves piece to required coordinates.
     *
     * @param x required coordinate of x
     * @param y required coordinate of y
     *
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void Move(int x, int y);

    /**
     * This method transfers piece to string.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String toString();

    /**
     * This method transfers piece to string notation.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String toStringForNotation();
}


