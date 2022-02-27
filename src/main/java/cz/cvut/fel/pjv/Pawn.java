package cz.cvut.fel.pjv;

import java.util.ArrayList;
import java.util.Scanner;

public class Pawn implements Piece {
    private final int points;
    private final Color color;
    private int x;
    private int y;
    private boolean atLeastOnceMoved;

    public Pawn(Color color, int x, int y) {
        this.points = 1;
        this.color = color;
        this.x = x;
        this.y = y;
        this.atLeastOnceMoved = false;
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


    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
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

        return possibilities;
    }

    public Square getMovesIfNotMoved(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        int way = this.getColor()==Color.WHITE ? 2:-2;
        return board.getBoard()[this.getX()][this.getY()+way];
    }

    public Square getBasicMove(Board board) {
        int way = this.getColor()==Color.WHITE ? 1:-1;
            if (board.getBoard()[this.getX()][this.getY()+1].getPiece() == null) {
                return board.getBoard()[this.getX()][this.getY()+way];
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
        }
        // RIGHT
        if (Helpers.MoveInBoard(this.getX()+1, this.getY()+way)) {
            if (board.getBoard()[this.getX()+1][this.getY()+way].getPiece() != null) {
                if (board.getBoard()[this.getX() + 1][this.getY() + way].getPiece().getColor() != this.getColor()) {
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY() + way]);
                }
            }
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
        String notation = "";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }
}
