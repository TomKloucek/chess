package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.pieces.King;
import cz.cvut.fel.pjv.pieces.Piece;

import javax.swing.*;
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

    public boolean doAMove() {
        Piece chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
        boolean checked = false;
        if (getColor() == WHITE) {
            checked = board.whiteInCheck();
        } else {
            checked = board.blackInCheck();
        }
        if (checked && board.Mated(getColor())) {
            return true;
        }
        while (chosen.possibleMovement(board).isEmpty()) {
            chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            if (chosen instanceof King && chosen.possibleMovement(board).isEmpty() && board.getPieces(getColor()).size() == 1) {
                JOptionPane.showMessageDialog(null, "Remíza");
            }
            System.out.println(chosen + "-empty");
        }
        Square goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
        if (checked) {
            while (!board.canBlockOrEscapeFromCheck(chosen)) {
                chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            }
            ArrayList<Square> possibleMovesToUncheck = board.possibleMovesToUncheck(chosen);
            goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
            if (chosen instanceof King) {
                goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
            } else {
                while (!possibleMovesToUncheck.contains(goalMove)) {
                    goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
                }
            }
        }
        while (goalMove == null) {
            chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            if (!chosen.possibleMovement(board).isEmpty()) {
                goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));
            }
        }
        board.movePiece(chosen, goalMove.getX() - 1, goalMove.getY());
        return false;
    }
}
