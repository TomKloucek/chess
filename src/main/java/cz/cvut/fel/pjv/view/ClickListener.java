package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.pieces.King;
import cz.cvut.fel.pjv.models.pieces.IPiece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClickListener implements ActionListener {
    private Square square;
    private Board board;
    private BoardView bw;

    @Override
    public void actionPerformed(ActionEvent e) {
        Game game = State.getInstance().getGame();
        SquareView[][] squarePanels = bw.getSquarePanels();
        ButtonCoord buttonCoord = (ButtonCoord) e.getSource();
        if (squarePanels[buttonCoord.getX()][buttonCoord.getY()].getDot() != null) {
            IPiece picked = bw.getPickedPiece();
            board.movePiece(picked, buttonCoord.getX(), buttonCoord.getY());
            bw.setPickedPiece(-1, -1);
            if (State.getInstance().isWhiteOnMove()) {
                bw.deleteButtonsOnSquaresWithoutPiece();
                if (board.whiteInCheck()) {
                    if (board.mated(Color.WHITE)) {
                        JOptionPane.showMessageDialog(null, "Černý vyhrál");
                        State.getInstance().getGuiRef().closeGameFrame(true);
                        State.getInstance().resetMove();
                    }
                }
                else {
                    if (board.isDraw()) {
                        JOptionPane.showMessageDialog(null, "Remíza");
                        State.getInstance().getGuiRef().closeGameFrame(true);
                    }
                }
            }
            else {
                bw.deleteButtonsOnSquaresWithoutPiece();
                if (board.blackInCheck()) {
                    if (board.mated(Color.BLACK)) {
                        JOptionPane.showMessageDialog(null, "Bilý vyhrál");
                        State.getInstance().getGuiRef().closeGameFrame(true);
                        State.getInstance().resetMove();
                    }
                }
                else {
                    if (board.isDraw()) {
                        JOptionPane.showMessageDialog(null, "Remíza");
                        State.getInstance().getGuiRef().closeGameFrame(true);
                    }
                }
            }
        }
        else {
            ArrayList<Square> possibleMovement = square.getPiece().possibleMovement(board);
            bw.setPickedPiece(buttonCoord.getX(),buttonCoord.getY());
            IPiece picked = bw.getPickedPiece();
            ArrayList<Square> possibleMovesToUncheck = board.possibleMovesToUncheck(picked);
            if (game.getMe().getColor() != picked.getColor() && board.getType() != GameType.PVP) {
                bw.setPickedPiece(-1,-1);
                JOptionPane.showMessageDialog(null, "Toto není tvoje figurka");
            }
            else if (picked.getColor() == Color.WHITE && board.whiteInCheck() && !board.canBlockOrEscapeFromCheck(picked) && !(picked instanceof  King)) {
                bw.setPickedPiece(-1,-1);
                JOptionPane.showMessageDialog(null, "Tento výběr vás nedostane z šachu");
            }
            else if (picked.getColor() == Color.BLACK && board.blackInCheck() && !board.canBlockOrEscapeFromCheck(picked) && !(picked instanceof  King)) {
                bw.setPickedPiece(-1,-1);
                JOptionPane.showMessageDialog(null, "Tento výběr vas nedostane z šachu");
            }
            else if ((State.getInstance().isWhiteOnMove() && picked.getColor() == Color.WHITE && board.whiteInCheck() )|| (!State.getInstance().isWhiteOnMove() && picked.getColor() == Color.BLACK
            && board.blackInCheck())) {

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (picked instanceof King && possibleMovement.contains(squarePanels[i][j].getSquare())){
                            try {
                                squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                                squarePanels[i][j].setButton();
                                squarePanels[i][j].validate();
                                squarePanels[i][j].repaint();
                            } catch (IOException ex) {
                                Logger.log(ClickListener.class, "actionPerformed",ex.getMessage());
                                ex.printStackTrace();
                            }
                        }
                        else if (possibleMovesToUncheck.contains(squarePanels[i][j].getSquare())) {
                            try {
                                squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                                squarePanels[i][j].setButton();
                                squarePanels[i][j].validate();
                                squarePanels[i][j].repaint();
                            } catch (IOException ex) {
                                Logger.log(ClickListener.class, "actionPerformed",ex.getMessage());
                                ex.printStackTrace();
                            }
                        } else {
                            squarePanels[i][j].setButton();
                            squarePanels[i][j].setDot(null);
                        }
                    }
                }
            }
            else if (State.getInstance().isWhiteOnMove() && picked.getColor() == Color.WHITE || !State.getInstance().isWhiteOnMove() && picked.getColor() == Color.BLACK) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(board.getEveryXRayMove(board.getPieces(Helpers.getOtherColor(picked.getColor()))).contains(square)){
                            if(possibleMovesToUncheck.contains(squarePanels[i][j].getSquare())) {
                                try {
                                    squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                                    squarePanels[i][j].setButton();
                                    squarePanels[i][j].validate();
                                    squarePanels[i][j].repaint();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                    Logger.log(ClickListener.class, "actionPerformed",ex.getMessage());
                                }
                            } else {
                                squarePanels[i][j].setButton();
                                squarePanels[i][j].setDot(null);
                            }
                        }
                        else if (possibleMovement.contains(squarePanels[i][j].getSquare())) {
                            try {
                                squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                                squarePanels[i][j].setButton();
                                squarePanels[i][j].validate();
                                squarePanels[i][j].repaint();
                            } catch (IOException ex) {
                                Logger.log(ClickListener.class, "actionPerformed",ex.getMessage());
                                ex.printStackTrace();
                            }
                        } else {
                            squarePanels[i][j].setButton();
                            squarePanels[i][j].setDot(null);
                        }
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Tento hráč není na tahu");
            }
        }
        bw.repaintBoard();
    }

    public ClickListener(Square square, Board board, BoardView bw) {
        this.square = square;
        this.board = board;
        this.bw = bw;
    }
}
