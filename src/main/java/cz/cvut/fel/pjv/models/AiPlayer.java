package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.pieces.King;
import cz.cvut.fel.pjv.pieces.Piece;

import java.util.ArrayList;
import java.util.Random;

import static cz.cvut.fel.pjv.models.Color.WHITE;

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
        boolean checked = false;
        if (getColor() == WHITE) {
            checked = board.whiteInCheck();
        }
        else {
            checked = board.blackInCheck();
        }
        while (chosen.possibleMovement(board).isEmpty()) {
            chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
        }
        Square goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0,chosen.possibleMovement(board).size()));
        if (checked) {
            while (!board.canBlockOrEscapeFromCheck(chosen)) {
                System.out.println("tady");
                chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            }
            ArrayList<Square> possibleMovesToUncheck = board.possibleMovesToUncheck(chosen);
            System.out.println(chosen);
            goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0,chosen.possibleMovement(board).size()));
            if (chosen instanceof King) {
                goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0,chosen.possibleMovement(board).size()));
            }else {
                while (!possibleMovesToUncheck.contains(goalMove)) {
                    goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
                }
            }
        }
        while (goalMove == null) {
            goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0,chosen.possibleMovement(board).size()));
        }
        board.movePiece(chosen, goalMove.getX()-1, goalMove.getY());
    }
}
