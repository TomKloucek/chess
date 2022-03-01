package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class King implements  Piece {
    private final int points;
    private final Color color;
    private int x;
    private int y;
    private boolean inCheck;
    private boolean atLeastOnceMoved;

    public King(Color color, int x, int y) {
        this.points = Integer.MAX_VALUE;
        this.color = color;
        this.x = x;
        this.y = y;
        this.inCheck = false;
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
    public ArrayList<Square> PossibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        // UPPER LINE
        for (int i = -1; i <= 1; i++){
                if (board.getBoard()[this.getX() + i][this.getY() + 1].getPiece() != null) {
                    if (board.getBoard()[this.getX() + i][this.getY() + 1].getPiece().getColor() != this.getColor()) {
                        if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() + 1])) {
                            possibilities.add(board.getBoard()[this.getX() + i][this.getY() + 1]);
                        }
                    }
                }
                else{
                    if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() + 1])) {
                        possibilities.add(board.getBoard()[this.getX() + i][this.getY() + 1]);
                    }
                }
        }
        // SQUARE ON THE RIGHT SIDE
        if (board.getBoard()[this.getX() + 1][this.getY()].getPiece() != null) {
            if (board.getBoard()[this.getX() + 1][this.getY()].getPiece().getColor() != this.getColor()) {
                if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + 1][this.getY() ])) {
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY()]);
                }
            }

        }
        else{
            if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + 1][this.getY()])) {
                possibilities.add(board.getBoard()[this.getX() + 1][this.getY()]);
            }
        }
        // SQUARE ON THE LEFT SIDE
        if (board.getBoard()[this.getX() - 1][this.getY()].getPiece() != null) {
            if (board.getBoard()[this.getX() - 1][this.getY()].getPiece().getColor() != this.getColor()) {
                if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() - 1][this.getY()])) {
                    possibilities.add(board.getBoard()[this.getX() - 1][this.getY()]);
                }
            }

        }
        else{
            if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() - 1][this.getY()])) {
                possibilities.add(board.getBoard()[this.getX() - 1][this.getY()]);
            }
        }
        // LOWER LINE
        for (int i = -1; i <= 1; i++){
            if (board.getBoard()[this.getX() + i][this.getY() - 1].getPiece() != null) {
                if (board.getBoard()[this.getX() + i][this.getY() - 1].getPiece().getColor() != this.getColor()) {
                    if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() - 1])) {
                        possibilities.add(board.getBoard()[this.getX() + i][this.getY() - 1]);
                    }
                }
            }
            else{
                if (!board.getEveryPossibleMoves(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() - 1])) {
                    possibilities.add(board.getBoard()[this.getX() + i][this.getY() - 1]);
                }
            }
        }

        // CASTLE

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
        String notation = "K";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }
}
