package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Bishop implements Piece {
    private int points;
    private Color color;
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
    public ArrayList<Square> PossibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            if (board.getBoard()[this.getY() + i][this.getX()+i].getPiece() == null){
                possibilities.add(board.getBoard()[this.getY() + 2][this.getX()]);
            }
        }
        return possibilities;
    }

    @Override
    public void Move(int x, int y) {

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
            notation += "WB" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }
}
