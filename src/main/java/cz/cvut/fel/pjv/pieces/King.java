package cz.cvut.fel.pjv.pieces;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;

import java.util.ArrayList;

public class King implements IPiece {
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
    public ArrayList<Square> possibleMovement(Board board) {
        ArrayList<Square> possibilities = new ArrayList<>();
        // UPPER LINE
        for (int i = -1; i <= 1; i++){
            if (this.getX()+i <= 7 && this.getX()+i >= 0 && this.getY()+1 >= 0 && this.getY()+1 <= 7) {
                if (board.getBoard()[this.getX() + i][this.getY() + 1].getPiece() != null) {
                    if (board.getBoard()[this.getX() + i][this.getY() + 1].getPiece().getColor() != this.getColor()) {
                        if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() + 1])) {
                            possibilities.add(board.getBoard()[this.getX() + i][this.getY() + 1]);
                        }
                    }
                } else {
                    if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() + 1])) {
                        possibilities.add(board.getBoard()[this.getX() + i][this.getY() + 1]);
                    }
                }
            }
        }
        // SQUARE ON THE RIGHT SIDE
        if (this.getX()+1 <= 7 && this.getX()+1 >= 0) {
            if (board.getBoard()[this.getX() + 1][this.getY()].getPiece() != null) {
                if (board.getBoard()[this.getX() + 1][this.getY()].getPiece().getColor() != this.getColor()) {
                    if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + 1][this.getY()])) {
                        possibilities.add(board.getBoard()[this.getX() + 1][this.getY()]);
                    }
                }

            } else {
                if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + 1][this.getY()])) {
                    possibilities.add(board.getBoard()[this.getX() + 1][this.getY()]);
                    System.out.println("pridal jsem:" + this.getX() + 1 + " - " + (this.getY()) );
                }
            }
        }
        // SQUARE ON THE LEFT SIDE
        if (this.getX()-1 <= 7 && this.getX()-1 >= 0){
            if (board.getBoard()[this.getX() - 1][this.getY()].getPiece() != null) {
                if (board.getBoard()[this.getX() - 1][this.getY()].getPiece().getColor() != this.getColor()) {
                    if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() - 1][this.getY()])) {
                        possibilities.add(board.getBoard()[this.getX() - 1][this.getY()]);
                        System.out.println("pridal jsem:" + (this.getX() - 1) + " - " + (this.getY()) );
                    }
                }

            } else {
                if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() - 1][this.getY()])) {
                    possibilities.add(board.getBoard()[this.getX() - 1][this.getY()]);
                    System.out.println("pridal jsem:" + (this.getX() - 1) + " - " + (this.getY()) );
                }
            }
        }
        // LOWER LINE
            for (int i = -1; i <= 1; i++){
                if (this.getX()+i <= 7 && this.getX()+i >= 0 && this.getY()-1 >= 0 && this.getY()-1 <= 7) {

                    if (board.getBoard()[this.getX() + i][this.getY() - 1].getPiece() != null) {
                        if (board.getBoard()[this.getX() + i][this.getY() - 1].getPiece().getColor() != this.getColor()) {
                            if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() - 1])) {
                                System.out.println("pridal jsem:" + (this.getX() + i) + " - " + (this.getY()-1) );
                                possibilities.add(board.getBoard()[this.getX() + i][this.getY() - 1]);
                            }
                        }
                    } else {
                        if (!board.getEveryPossibleMovesWithCover(board.getPieces(Helpers.getOtherColor(this.getColor()))).contains(board.getBoard()[this.getX() + i][this.getY() - 1])) {
                            possibilities.add(board.getBoard()[this.getX() + i][this.getY() - 1]);
                            System.out.println("pridal jsem:" + (this.getX() + i) + " - " + (this.getY()-1) );
                        }
                    }
                }
        }

        // CASTLE
        if (!this.atLeastOnceMoved) {
            if (this.getColor() == Color.WHITE) {
                if (board.getBoard()[0][0].getPiece() instanceof Rook left_rook) {
                    if (!left_rook.isAtLeastOnceMoved() && board.getBoard()[1][0].getPiece() == null && board.getBoard()[2][0].getPiece() == null && board.getBoard()[3][0].getPiece() == null) {
                        if (!board.willBeChecked(Color.WHITE, 1, 0) && !board.willBeChecked(Color.WHITE, 2, 0) && !board.willBeChecked(Color.WHITE, 3, 0) && !board.willBeChecked(Color.WHITE, 4, 0) ) {
                            possibilities.add(board.getBoard()[0][0]);
                        }
                    }
                }
                if (board.getBoard()[7][0].getPiece() instanceof Rook right_rook) {
                    if (!right_rook.isAtLeastOnceMoved() && board.getBoard()[5][0].getPiece() == null && board.getBoard()[6][0].getPiece() == null) {
                        if (!board.willBeChecked(Color.WHITE, 4, 0) && !board.willBeChecked(Color.WHITE, 5, 0) && !board.willBeChecked(Color.WHITE, 6, 0)) {
                            possibilities.add(board.getBoard()[7][0]);
                        }
                    }
                }
            } else {
                if (board.getBoard()[0][7].getPiece() instanceof Rook left_rook) {
                    if (!left_rook.isAtLeastOnceMoved() && board.getBoard()[1][7].getPiece() == null && board.getBoard()[2][7].getPiece() == null && board.getBoard()[3][7].getPiece() == null) {
                        if (!board.willBeChecked(Color.BLACK, 1, 7) && !board.willBeChecked(Color.BLACK, 2, 7) && !board.willBeChecked(Color.BLACK, 3, 7) && !board.willBeChecked(Color.BLACK, 4, 7)) {
                            possibilities.add(board.getBoard()[0][7]);
                        }
                    }
                }
                if (board.getBoard()[7][7].getPiece() instanceof Rook right_rook) {
                    if (!right_rook.isAtLeastOnceMoved() && board.getBoard()[5][7].getPiece() == null && board.getBoard()[6][7].getPiece() == null) {
                        if (!board.willBeChecked(Color.WHITE, 4, 7) &&!board.willBeChecked(Color.BLACK, 5, 7) && !board.willBeChecked(Color.BLACK, 6, 7)) {
                            possibilities.add(board.getBoard()[7][7]);
                        }
                    }
                }
            }
        }
        return possibilities;
    }

    // GET SQUARES AROUND KING
    public ArrayList<Square> getAttackMovesForKingMove(Board board){
        ArrayList<Square> squaresAroundKing = new ArrayList<>();
        // UPPER LINE
        for (int i = -1; i <= 1; i++){
            if (this.getX()+i <= 7 && this.getX()+i >= 0 && this.getY()+1 >= 0 && this.getY()+1 <= 7) {
                       squaresAroundKing.add(board.getBoard()[this.getX() + i][this.getY() + 1]);
                }
            }

        // SQUARE ON THE RIGHT SIDE
        if (this.getX()+1 <= 7 && this.getX()+1 >= 0) {
                squaresAroundKing.add(board.getBoard()[this.getX() + 1][this.getY()]);
        }
        // SQUARE ON THE LEFT SIDE
        if (this.getX()-1 <= 7 && this.getX()-1 >= 0){
                squaresAroundKing.add(board.getBoard()[this.getX() - 1][this.getY()]);
        }
        // LOWER LINE
        for (int i = -1; i <= 1; i++){
            if (this.getX()+i <= 7 && this.getX()+i >= 0 && this.getY()-1 >= 0 && this.getY()-1 <= 7) {
                    squaresAroundKing.add(board.getBoard()[this.getX() + i][this.getY() - 1]);
            }
        }
        return squaresAroundKing;
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
