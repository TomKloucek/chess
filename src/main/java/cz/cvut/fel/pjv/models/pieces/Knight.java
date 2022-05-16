package cz.cvut.fel.pjv.models.pieces;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public class Knight implements IPiece {
    private final int points;
    private final Color color;
    private int x;
    private int y;

    public Knight(Color color, int x, int y) {
        this.points = 3;
        this.color = color;
        this.x = x;
        this.y = y;
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
        ArrayList<int[]> possibilities = generateAllPossibleWays(this.getX(), this.getY());
        ArrayList<Square> returnArray = new ArrayList<>();
        for (var value : possibilities) {
            if (board.getBoard()[value[0]][value[1]].getPiece() == null || board.getBoard()[value[0]][value[1]].getPiece().getColor() != this.getColor()) {
                returnArray.add(board.getBoard()[value[0]][value[1]]);
            }
        }
        return returnArray;
    }

    @Override
    public ArrayList<Square> getAttackMovesForKingMove(Board board) {
        ArrayList<int[]> possibilities = generateAllPossibleWays(this.getX(), this.getY());
        ArrayList<Square> returnArray = new ArrayList<>();
        for (var value : possibilities) {
            if (board.getBoard()[value[0]][value[1]].getPiece() == null || board.getBoard()[value[0]][value[1]].getPiece().getColor() != this.getColor() || board.getBoard()[value[0]][value[1]].getPiece().getColor() == this.getColor()) {
                returnArray.add(board.getBoard()[value[0]][value[1]]);
            }
        }
        return returnArray;
    }

    public ArrayList<int[]> generateAllPossibleWays(int x, int y) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int[] moves = {-2,-1,1,2};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (x+moves[i]<8 && x+moves[i]>=0 && y+moves[j]>=0 && y+moves[j]<8 && (Math.abs(moves[i])+Math.abs(moves[j])==3)) {
                    int[] eligible = new int[2];
                    eligible[0] = x+moves[i];
                    eligible[1] = y+moves[j];
                    possibleMoves.add(eligible);
                }
            }
        }
        return possibleMoves;
    }


    @Override
    public void Move(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
    @Override
    public String toString() {
        String notation = "N";
        if (color == Color.BLACK){
            notation += "B" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += "W" + Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }

    @Override
    public String toStringForNotation() {
        String notation = "N";
        if (color == Color.BLACK){
            notation += Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        else {
            notation += Helpers.XTranslate(this.getX()) + (getY()+1);
        }
        return notation;
    }
}

