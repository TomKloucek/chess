package cz.cvut.fel.pjv.models.pieces;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public class Bishop implements IPiece {
    private final int points;
    private final Color color;
    private int x;
    private int y;

    public Bishop(Color color, int x, int y) {
        this.points = 3;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public ArrayList<Square> possibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        // RIGHT UP
        for (int i = 1; i < 8; i++) {
                if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                        && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() == this.getColor()) {
                    break;
                }
                else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                        && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() != this.getColor()) {
                    possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                    break;
                }
                else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() == null) {
                    possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                }
        }
        // RIGHT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
            }
        }
        //LEFT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
            }
        }
        // LEFT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
                break;
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
            }
        }
        return possibilities;
    }

    @Override
    public ArrayList<Square> getAttackMovesForKingMove(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        // RIGHT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != this){
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
            }
        }
        // RIGHT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != this){
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
                if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }

            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
            }
        }
        //LEFT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != this){
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
                if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }

            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
            }
        }
        // LEFT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != this){
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
                if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][Helpers.checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
            }
        }
        return possibilities;
    }

    /**
     * This method generates all xray moves of the piece.
     *
     * @param board given board of game
     *
     * @return list of squares of all xrayedmoves
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> getXRayedMoves(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        ArrayList<Square> betweenKingAndPiece = new ArrayList<>();
        IPiece king = board.getKing(Helpers.getOtherColor(this.color));
        // RIGHT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != this){
                    if ((board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() != this.getColor() &&
                    board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() instanceof King) ||
                    board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() == this.getColor()){
                        betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                        break;
                    }
                    else {
                        betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][Helpers.checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                    }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();


        // RIGHT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")].getPiece() != this) {
                if ((board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")].getPiece().getColor() != this.getColor() &&
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")].getPiece() instanceof King) ||
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")].getPiece().getColor() == this.getColor()) {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")]);
                    break;
                } else {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), +i, this.getY(), -i, "y")]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();
        //LEFT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")].getPiece() != this) {
                if ((board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")].getPiece().getColor() != this.getColor() &&
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")].getPiece() instanceof King) ||
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")].getPiece().getColor() == this.getColor()) {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")]);
                    break;
                } else {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), i, "y")]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();
        // LEFT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")].getPiece() != null
                    && board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")].getPiece() != this) {
                if ((board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")].getPiece().getColor() != this.getColor() &&
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")].getPiece() instanceof King) ||
                        board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")].getPiece().getColor() == this.getColor()) {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")]);
                    break;
                } else {
                    betweenKingAndPiece.add(board.getBoard()[Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "x")][Helpers.checkSquareCoordinates(this.getX(), -i, this.getY(), -i, "y")]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        return possibilities;
    }


    @Override
    public void Move(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }
    @Override
    public String toString() {
        String notation = "";
        if (color == Color.BLACK){
            notation += "BB" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "BW" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }

    @Override
    public String toStringForNotation() {
        String notation = "B";
        if (color == Color.BLACK){
            notation += Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }
}
