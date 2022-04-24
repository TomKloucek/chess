package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

public class AiPlayer extends Player {

    private Board board;
    private Random random;

    public AiPlayer(Color color, ArrayList<Piece> pieces, Board board) {
        super(color, pieces);
        this.random = new Random();
        this.board = board;
    }

    public void doAMove() {
        Piece chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
        while (chosen.possibleMovement(board).isEmpty()) {
            chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
        }
        Square goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0,chosen.possibleMovement(board).size()));
        board.movePiece(chosen, goalMove.getX()-1, goalMove.getY());
    }
}
