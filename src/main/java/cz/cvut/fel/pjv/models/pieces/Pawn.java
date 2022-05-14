package cz.cvut.fel.pjv.models.pieces;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public class Pawn implements IPiece {
    private final int points;
    private final Color color;
    private int x;
    private int y;
    private boolean atLeastOnceMoved;
    public boolean movedTwoSquares;

    public Pawn(Color color, int x, int y) {
        this.points = 1;
        this.color = color;
        this.x = x;
        this.y = y;
        this.atLeastOnceMoved = false;
        this.movedTwoSquares = false;
    }

    public Pawn(Color color, int x, int y, boolean moved) {
        this.points = 1;
        this.color = color;
        this.x = x;
        this.y = y;
        this.atLeastOnceMoved = moved;
    }
    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public Color getColor() {
        return color;
    }
    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getY() {
        return y;
    }

     public boolean getmovedTwoSquares() {
        return movedTwoSquares;
    }

    @Override
    public ArrayList<Square> possibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();

        // Prida pohyb o dva, kdyz se hrac nehybnul
        if (!this.atLeastOnceMoved) {
            possibilities.add(getMovesIfNotMoved(board));
        }

        // Prida zakladni pohyb pokud ho nic neblokuje
        Square basicMove = getBasicMove(board);
        if (basicMove != null) {
            possibilities.add(basicMove);
        }

        // Prida utocne metody
        possibilities.addAll(getAttackMoves(board));

        for (int i = 0; i < possibilities.size(); i++) {
            if(possibilities.get(i) == null ){
                possibilities.remove(i);
            }
        }
        return possibilities;
    }

    public Square getMovesIfNotMoved(Board board) {
        int way = this.getColor()==Color.WHITE ? 2:-2;
        int way_toblock = this.getColor()==Color.WHITE ? 1:-1;
        if (board.getBoard()[this.getX()][getY()+way].getPiece() == null && board.getBoard()[this.getX()][getY()+way_toblock].getPiece() == null) {
            return board.getBoard()[this.getX()][this.getY() + way];
        }
        return null;
    }

    public Square getBasicMove(Board board) {
        int way = this.getColor()==Color.WHITE ? 1:-1;
        if (this.getY()+way >= 0 && this.getY()+way <= 7) {
            if (board.getBoard()[this.getX()][this.getY() + way].getPiece() == null) {
                return board.getBoard()[this.getX()][this.getY() + way];
            }
        }
        return null;
    }

    public ArrayList<Square> getAttackMoves(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        int way = this.getColor()==Color.WHITE ? 1:-1;
        // LEFT
        if (Helpers.MoveInBoard(this.getX()-1, this.getY()+way)) {

            if (board.getBoard()[this.getX()-1][this.getY()+way].getPiece() != null) {
                if (board.getBoard()[this.getX() - 1][this.getY() + way].getPiece().getColor() != this.getColor()) {
                    possibilities.add(board.getBoard()[this.getX() - 1][this.getY() + way]);
                }
            }
            else if (board.getBoard()[this.getX() - 1][this.getY()].getPiece() instanceof Pawn) {
                Pawn enPassantLeft = (Pawn) board.getBoard()[this.getX() - 1][this.getY()].getPiece();
                if (enPassantLeft != null && enPassantLeft.color != this.getColor() && enPassantLeft.movedTwoSquares == true) {
                    possibilities.add(board.getBoard()[this.getX() - 1][this.getY() + way]);
                }
            }
        }
        // RIGHT
        if (Helpers.MoveInBoard(this.getX()+1, this.getY()+way)) {

            if (board.getBoard()[this.getX()+1][this.getY()+way].getPiece() != null) {
                if (board.getBoard()[this.getX() + 1][this.getY() + way].getPiece().getColor() != this.getColor()) {
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY() + way]);
                }
            }
            else if (board.getBoard()[this.getX() + 1][this.getY()].getPiece() instanceof Pawn) {
                Pawn enPassantRight = (Pawn) board.getBoard()[this.getX() + 1][this.getY()].getPiece();
                if (enPassantRight != null && enPassantRight.color != this.getColor() && enPassantRight.movedTwoSquares == true){
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY() + way]);
                }
            }
        }
        return possibilities;
    }

    public ArrayList<Square> getAttackMovesForKingMove(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        int way = this.getColor()==Color.WHITE ? 1:-1;
        // LEFT
        if (Helpers.MoveInBoard(this.getX()-1, this.getY()+way)) {

                    possibilities.add(board.getBoard()[this.getX() - 1][this.getY() + way]);
                }

        // RIGHT
        if (Helpers.MoveInBoard(this.getX()+1, this.getY()+way)) {
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY() + way]);
                }
        return possibilities;
    }



    @Override
    public void Move(int x, int y) {
        this.atLeastOnceMoved = true;
        movedTwoSquares = Math.abs(this.y - y) == 2;

        this.setX(x);
        this.setY(y);
    }

    @Override
    public String toString() {
        String notation = "";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        String moved = "X";
        if (this.movedTwoSquares) {
            return notation+" ";
        }
        else {
            return notation+moved;
        }
    }
}
