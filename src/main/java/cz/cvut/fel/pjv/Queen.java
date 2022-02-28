package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Queen implements Piece {
    private final int points;
    private final Color color;
    private int x;
    private int y;

    public Queen(Color color, int x, int y) {
        this.points = 9;
        this.color = color;
        this.x = x;
        this.y = y;
    }

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
                    possibilities.add(board.getBoard()[this.getX()][i]);
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
    public void Move(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public String toString() {
        String notation = "Q";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }

}
