package cz.cvut.fel.pjv;

import java.util.ArrayList;
import java.util.Optional;

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
        // RIGHT UP
        for (int i = 1; i < 8; i++) {
                if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                        && board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() == this.getColor()) {
                    break;
                }
                else if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() != null
                        && board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece().getColor() != this.getColor()) {
                    possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                    break;
                }
                else if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")].getPiece() == null) {
                    possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),i, "y")]);
                }
        }
        // RIGHT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),+i, this.getY(),-i, "y")]);
            }
        }
        //LEFT UP
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),i, "y")]);
            }
        }
        // LEFT DOWN
        for (int i = 1; i < 8; i++) {
            if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece().getColor() == this.getColor()) {
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() != null
                    && board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece().getColor() != this.getColor()) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
                break;
            }
            else if (board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")].getPiece() == null) {
                possibilities.add(board.getBoard()[checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "x")][checkSquareCoordinates(this.getX(),-i, this.getY(),-i, "y")]);
            }
        }
        return possibilities;
    }

    private int checkSquareCoordinates(int actualCoordinateX, int additionToCoordinateX, int actualCoordinateY, int additionToCoordinateY, String coordinate){
        int requiredCoordinateX =  actualCoordinateX + additionToCoordinateX;
        int requiredCoordinateY = actualCoordinateY + additionToCoordinateY;
        if (coordinate.equals("x")) {
            if ((-1 < requiredCoordinateX && requiredCoordinateX < 8) && (-1 < requiredCoordinateY && requiredCoordinateY < 8)) {
                return requiredCoordinateX;
            } else {
                return actualCoordinateX;
            }
        }
        else{
            if ((-1 < requiredCoordinateX && requiredCoordinateX < 8) && (-1 < requiredCoordinateY && requiredCoordinateY < 8)) {
                return requiredCoordinateY;
            } else {
                return actualCoordinateY;
            }
        }
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
