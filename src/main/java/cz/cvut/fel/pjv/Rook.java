package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Rook implements Piece {
    private int points;
    private Color color;
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
            // UP
            for (int i = this.getY()+1; i < 8; i++) {
                if (board.getBoard()[this.getX()][i].getPiece() != null) {
                    if (board.getBoard()[this.getX()][i].getPiece().getColor() != this.getColor()) {
                        possibilities.add(board.getBoard()[this.getX()][i]);
                    }
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[this.getX()][i]);
                }
            }
            // RIGHT
            for (int i = this.getX()+1; i < 8; i++) {
                if (board.getBoard()[this.getX()][i].getPiece() != null) {
                    if (board.getBoard()[this.getX()][i].getPiece().getColor() != this.getColor()) {
                        possibilities.add(board.getBoard()[i][this.getY()]);
                    }
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[i][this.getY()]);
                }
            }
            // DOWN
            for (int i = this.getY()-1; i > -1; i--) {
                if (board.getBoard()[this.getX()][i].getPiece() != null) {
                    if (board.getBoard()[this.getX()][i].getPiece().getColor() != this.getColor()) {
                        possibilities.add(board.getBoard()[this.getX()][i]);
                    }
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[this.getX()][i]);
                }
            }
            // LEFT
            for (int i = this.getX()-1; i > -1; i--) {
                if (board.getBoard()[i][this.getY()].getPiece() != null) {
                    if (board.getBoard()[i][this.getY()].getPiece().getColor() != this.getColor()) {
                        possibilities.add(board.getBoard()[i][this.getY()]);
                    }
                    break;
                }
                else {
                    possibilities.add(board.getBoard()[i][this.getY()]);
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
