package cz.cvut.fel.pjv;

import java.util.ArrayList;

public class Pawn implements Piece {
    private int points;
    private char representation;
    private Color color;
    private int x;
    private int y;

    public Pawn(int points, char representation, Color color, int x, int y) {
        this.points = points;
        this.representation = representation;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public char getRepresentation() {
        return representation;
    }

    public void setRepresentation(char representation) {
        this.representation = representation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public ArrayList<Square> PossibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        if (this.color == Color.BLACK) {
            if (board.getBoard()[this.getY()-1][this.getX()].getPiece() == null) {
                possibilities.add(board.getBoard()[this.getY()-1][this.getX()]);
            }
            // TODO naimplementovat capture i pro cerneho
        }
        else {
            if (board.getBoard()[this.getY()+1][this.getX()].getPiece() == null) {
                possibilities.add(board.getBoard()[this.getY()+1][this.getX()]);
            }
            if (this.getX() == 0) {
                if (board.getBoard()[this.getY()+1][this.getX()+1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()+1].getPiece().getColor() == Color.BLACK) {
                    possibilities.add(board.getBoard()[this.getY()+1][this.getX()+1]);
                }
            }
            else if (this.getX() == 7) {
                if (board.getBoard()[this.getY()+1][this.getX()-1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()-1].getPiece().getColor() == Color.BLACK) {
                    possibilities.add(board.getBoard()[this.getY()+1][this.getX()-1]);
                }
            }
            else {
                if (board.getBoard()[this.getY()+1][this.getX()+1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()+1].getPiece().getColor() == Color.BLACK) {
                    possibilities.add(board.getBoard()[this.getY()+1][this.getX()+1]);
                }
                if (board.getBoard()[this.getY()+1][this.getX()-1].getPiece() != null && board.getBoard()[this.getY()+1][this.getX()-1].getPiece().getColor() == Color.BLACK) {
                    possibilities.add(board.getBoard()[this.getY()+1][this.getX()-1]);
                }
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
        String notation = "";
        if (color == Color.BLACK){
            notation += "B" + getY() + getX();
        }
        else {
            notation += "W" + getY() + getX();
        }
        return notation;
    }
}
