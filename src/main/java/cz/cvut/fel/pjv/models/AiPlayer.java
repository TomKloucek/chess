package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import cz.cvut.fel.pjv.models.pieces.King;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import static cz.cvut.fel.pjv.models.Color.WHITE;

public class AiPlayer extends Player {

    private Board board;
    private Random random;

    public AiPlayer(Color color, ArrayList<IPiece> pieces, Board board) {
        super(color, pieces);
        this.random = new Random();
        this.board = board;
    }

    public boolean doAMove() {
        IPiece chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
        boolean checked = false;
        if (getColor() == WHITE) {
            checked = board.whiteInCheck();
        } else {
            checked = board.blackInCheck();
        }
        if (checked && board.mated(getColor())) {
            return true;
        }
        while (chosen.possibleMovement(board).isEmpty()) {
            chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            if (chosen instanceof King && chosen.possibleMovement(board).isEmpty() && board.getPieces(getColor()).size() == 1) {
                JOptionPane.showMessageDialog(null, "Remíza");
            }
        }
        Square goalMove = null;
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
        else {
            ArrayList<Object> field = getBestAiMove(board.getPieces(getColor()));
            if (field != null) {
                board.movePiece(((IPiece) field.get(1)), ((Square) field.get(0)).getX() - 1, ((Square) field.get(0)).getY());
                return false;
            }
        }
        while (goalMove == null) {
            System.out.println(chosen.possibleMovement(board));
            System.out.println(chosen);
            if (chosen.possibleMovement(board).isEmpty()) {
                chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            }
            goalMove = chosen.possibleMovement(board).get(Helpers.randomNumber(0, chosen.possibleMovement(board).size()));

            if (board.getEveryXRayMove(board.getPieces(Helpers.getOtherColor(chosen.getColor()))).contains(board.getBoard()[chosen.getX()][chosen.getY()])
                    && !board.possibleMovesToUncheck(chosen).contains(board.getBoard()[goalMove.getX()-1][goalMove.getY()])){
                goalMove = null;
                chosen = board.getPieces(getColor()).get(Helpers.randomNumber(0, board.getPieces(getColor()).size()));
            }

        }
        board.movePiece(chosen, goalMove.getX()-1, goalMove.getY());
        return false;
    }

    public ArrayList<Object> getBestAiMove(ArrayList<IPiece> pieces) {
        ArrayList<Square> bestMoves = new ArrayList<>();
        for (IPiece chosen : pieces) {
            if (!chosen.possibleMovement(board).isEmpty()) {
                for (Square square : chosen.possibleMovement(board)) {
                    if (square != null) {
                        if (board.getEveryXRayMove(board.getPieces(Helpers.getOtherColor(chosen.getColor()))).contains(board.getBoard()[chosen.getX()][chosen.getY()])){
                            bestMoves.addAll(board.possibleMovesToUncheck(chosen));
                            System.out.println("Best move Xray: " +bestMoves);
                        }
                        else if (square.getPiece() != null && !board.getEveryXRayMove(board.getPieces(Helpers.getOtherColor(chosen.getColor()))).contains(board.getBoard()[chosen.getX()][chosen.getY()])) {
                            bestMoves.add(square);
                            System.out.println("Normal Best move: "+bestMoves);
                        }
                    }
                }
            }
        }
        if (bestMoves.isEmpty()) {
            return null;
        }
        Square move = bestMoves.get(0);
        for (IPiece chosen : pieces) {
            if (chosen.possibleMovement(board).contains(move)) {
                ArrayList<Object> toReturn = new ArrayList<>();
                toReturn.add(move);
                toReturn.add(chosen);
                System.out.println("To return: "+ toReturn);
                return toReturn;
            }
        }
        return null;
    }
}
