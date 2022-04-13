package cz.cvut.fel.pjv.pieces;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public class Rook implements Piece {
    private final int points;
    private final Color color;
    private int x;
    private int y;
    private boolean atLeastOnceMoved;

    public Rook(Color color, int x, int y) {
        this.points = 5;
        this.color = color;
        this.x = x;
        this.y = y;
        this.atLeastOnceMoved = false;
    }

    public boolean isAtLeastOnceMoved() {
        return atLeastOnceMoved;
    }

    @Override
    public int getPoints() {
        return this.points;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public ArrayList<Square> possibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
            // UP
            for (int i = this.getY()+1; i < 8; i++) {
                if (board.getBoard()[this.getX()][i].getPiece() != null) {
                    if (board.getBoard()[this.getX()][i].getPiece().getColor() == this.getColor()) {
                        break;
                    }
                    possibilities.add(board.getBoard()[this.getX()][i]);
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[this.getX()][i]);
                }
            }
            // RIGHT
            for (int i = this.getX()+1; i < 8; i++) {
                if (board.getBoard()[i][this.getY()].getPiece() != null) {
                    if (board.getBoard()[i][this.getY()].getPiece().getColor() == this.getColor()) {
                        break;
                    }
                    possibilities.add(board.getBoard()[i][this.getY()]);
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[i][this.getY()]);
                }
            }
            // DOWN
            for (int i = this.getY()-1; i > -1; i--) {
                if (board.getBoard()[this.getX()][i].getPiece() != null) {
                    if (board.getBoard()[this.getX()][i].getPiece().getColor() == this.getColor()) {
                        break;
                    }
                    possibilities.add(board.getBoard()[this.getX()][i]);
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[this.getX()][i]);
                }
            }
            // LEFT
            for (int i = this.getX()-1; i > -1; i--) {
                if (board.getBoard()[i][this.getY()].getPiece() != null) {
                    if (board.getBoard()[i][this.getY()].getPiece().getColor() == this.getColor()) {
                        break;
                    }
                    possibilities.add(board.getBoard()[i][this.getY()]);
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[i][this.getY()]);
                }
            }
        return possibilities;
    }

    @Override
    public ArrayList<Square> getAttackMovesForKingMove(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        // UP
        for (int i = this.getY()+1; i < 8; i++) {
            if (board.getBoard()[this.getX()][i].getPiece() != null && board.getBoard()[this.getX()][i].getPiece() != this) {
                possibilities.add(board.getBoard()[this.getX()][i]);
                if (board.getBoard()[this.getX()][i].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                possibilities.add(board.getBoard()[this.getX()][i]);
            }
        }
        // RIGHT
        for (int i = this.getX()+1; i < 8; i++) {
            if (board.getBoard()[i][this.getY()].getPiece() != null && board.getBoard()[i][this.getY()].getPiece() != this) {
                possibilities.add(board.getBoard()[i][this.getY()]);
                if (board.getBoard()[i][this.getY()].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                possibilities.add(board.getBoard()[i][this.getY()]);
            }
        }
        // DOWN
        for (int i = this.getY()-1; i > -1; i--) {
            if (board.getBoard()[this.getX()][i].getPiece() != null && board.getBoard()[this.getX()][i].getPiece() != this) {
                possibilities.add(board.getBoard()[this.getX()][i]);
                if (board.getBoard()[this.getX()][i].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                possibilities.add(board.getBoard()[this.getX()][i]);
            }
        }
        // LEFT
        for (int i = this.getX()-1; i > -1; i--) {
            if (board.getBoard()[i][this.getY()].getPiece() != null && board.getBoard()[i][this.getY()].getPiece() != this) {
                possibilities.add(board.getBoard()[i][this.getY()]);
                if (board.getBoard()[i][this.getY()].getPiece() instanceof King){
                    continue;
                }
                else {
                    break;
                }
            }
            else {
                possibilities.add(board.getBoard()[i][this.getY()]);
            }
        }
        return possibilities;
    }


    public ArrayList<Square> getXRayedMoves(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        ArrayList<Square> betweenKingAndPiece = new ArrayList<>();
        Piece king = board.getKing(Helpers.getOtherColor(this.color));
        // UP
        for (int i = this.getY()+1; i < 8; i++) {
            if (board.getBoard()[this.getX()][i].getPiece() != null && board.getBoard()[this.getX()][i].getPiece() != this) {
                if ((board.getBoard()[this.getX()][i].getPiece().getColor() != this.getColor() && board.getBoard()[this.getX()][i].getPiece() instanceof King) ||
                        ((board.getBoard()[this.getX()][i].getPiece().getColor() == this.getColor()))){
                    betweenKingAndPiece.add(board.getBoard()[this.getX()][i]);
                    break;
                }
                else {
                    betweenKingAndPiece.add(board.getBoard()[this.getX()][i]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();

        // RIGHT
        for (int i = this.getX()+1; i < 8; i++) {
            if (board.getBoard()[i][this.getY()].getPiece() != null && board.getBoard()[i][this.getY()].getPiece() != this) {
                if ((board.getBoard()[i][this.getY()].getPiece().getColor() != this.getColor() && board.getBoard()[i][this.getY()].getPiece() instanceof King) ||
                        ((board.getBoard()[i][this.getY()].getPiece().getColor() == this.getColor()))){
                    betweenKingAndPiece.add(board.getBoard()[i][this.getY()]);
                    break;
                }
                else {
                    betweenKingAndPiece.add(board.getBoard()[i][this.getY()]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();

        // DOWN
        for (int i = this.getY()-1; i > -1; i--) {
            if (board.getBoard()[this.getX()][i].getPiece() != null && board.getBoard()[this.getX()][i].getPiece() != this) {
                if ((board.getBoard()[this.getX()][i].getPiece().getColor() != this.getColor() && board.getBoard()[this.getX()][i].getPiece() instanceof King) ||
                        ((board.getBoard()[this.getX()][i].getPiece().getColor() == this.getColor()))){
                    betweenKingAndPiece.add(board.getBoard()[this.getX()][i]);
                    break;
                }
                else {
                    betweenKingAndPiece.add(board.getBoard()[this.getX()][i]);
                }
            }
        }
        if (betweenKingAndPiece.contains(board.getBoard()[king.getX()][king.getY()]) && betweenKingAndPiece.size()==2){
            possibilities.add(betweenKingAndPiece.get(0));
        }
        betweenKingAndPiece = new ArrayList<>();

        // LEFT
        for (int i = this.getX()-1; i > -1; i--) {
            if (board.getBoard()[i][this.getY()].getPiece() != null && board.getBoard()[i][this.getY()].getPiece() != this) {
                if ((board.getBoard()[i][this.getY()].getPiece().getColor() != this.getColor() && board.getBoard()[i][this.getY()].getPiece() instanceof King) ||
                        ((board.getBoard()[i][this.getY()].getPiece().getColor() == this.getColor()))){
                    betweenKingAndPiece.add(board.getBoard()[i][this.getY()]);
                    break;
                }
                else {
                    betweenKingAndPiece.add(board.getBoard()[i][this.getY()]);
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
        this.atLeastOnceMoved = true;
        this.setX(x);
        this.setY(y);
    }
    @Override
    public String toString() {
        String notation = "R";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }


}
