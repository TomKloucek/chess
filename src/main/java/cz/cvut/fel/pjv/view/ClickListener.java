package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.pieces.King;
import cz.cvut.fel.pjv.pieces.IPiece;
import cz.cvut.fel.pjv.server.ClientListener;

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
        System.out.println("26"+State.getInstance().isWhiteOnMove());
        SquareView[][] squarePanels = bw.getSquarePanels();
        ButtonCoord buttonCoord = (ButtonCoord) e.getSource();
        if (squarePanels[buttonCoord.getX()][buttonCoord.getY()].getDot() != null) {
            IPiece picked = bw.getPickedPiece();
            board.movePiece(picked, buttonCoord.getX(), buttonCoord.getY());
            bw.setPickedPiece(-1, -1);
            if (State.getInstance().isWhiteOnMove()) {
                bw.deleteButtonsOnSquaresWithoutPiece();
                if (board.whiteInCheck()) {
                    System.out.println("Bílý je v šachu");
                    if (board.Mated(Color.WHITE)) {
                        JOptionPane.showMessageDialog(null, "Černý vyhrál");
                        State.getInstance().resetMove();
                    }
                }
                else {
                    if (board.getKing(Color.WHITE).possibleMovement(board).isEmpty() && board.getPieces(Color.WHITE).size() == 1) {
                        JOptionPane.showMessageDialog(null, "Remíza");
                    }
                }
            }
            else {
                bw.deleteButtonsOnSquaresWithoutPiece();
                if (board.blackInCheck()) {
                    System.out.println("Černý je v šachu");
                    if (board.Mated(Color.BLACK)) {
                        JOptionPane.showMessageDialog(null, "Bilý vyhrál");
                        State.getInstance().resetMove();
                    }
                    else {
                        if (board.getKing(Color.BLACK).possibleMovement(board).isEmpty() && board.getPieces(Color.BLACK).size() == 1) {
                            JOptionPane.showMessageDialog(null, "Remíza");
                        }
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
            else if (State.getInstance().isWhiteOnMove() && picked.getColor() == Color.WHITE && board.whiteInCheck() || !State.getInstance().isWhiteOnMove() && picked.getColor() == Color.BLACK
            && board.blackInCheck()) {
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
                                System.out.println("chyba");
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
                System.out.println("151"+State.getInstance().isWhiteOnMove());
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
