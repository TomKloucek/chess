package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.pieces.Piece;

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
            Piece picked = bw.getPickedPiece();
            board.movePiece(picked, buttonCoord.getX(), buttonCoord.getY());
            bw.setPickedPiece(-1, -1);
            if (!State.getInstance().isWhiteOnMove()) {
                if (board.whiteInCheck()) {
                    System.out.println("Bílý je v šachu");
                    if (board.Mated(Color.WHITE)) {
                        JOptionPane.showMessageDialog(null, "Černý vyhrál");
                    }
                }
            }
            else {
                if (board.blackInCheck()) {
                    System.out.println("Černý je v šachu");
                    if (board.Mated(Color.BLACK)) {
                        JOptionPane.showMessageDialog(null, "Bilý vyhrál");
                    }
                }
            }
            State.getInstance().reverseMove();
        }
        else {
            ArrayList<Square> possibleMovement = square.getPiece().possibleMovement(board);
            bw.setPickedPiece(buttonCoord.getX(),buttonCoord.getY());
            Piece picked = bw.getPickedPiece();
            if (picked.getColor() == Color.WHITE && board.whiteInCheck() && !board.canBlockOrEscapeFromCheck(picked)) {
                bw.setPickedPiece(-1,-1);
                JOptionPane.showMessageDialog(null, "Tento výběr vás nedostane z šachu");
            }
            else if (picked.getColor() == Color.BLACK && board.blackInCheck() && !board.canBlockOrEscapeFromCheck(picked)) {
                bw.setPickedPiece(-1,-1);
                JOptionPane.showMessageDialog(null, "Tento výběr vas nedostane z šachu");
            }
            else if (State.getInstance().isWhiteOnMove() && picked.getColor() == Color.WHITE || !State.getInstance().isWhiteOnMove() && picked.getColor() == Color.BLACK) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (possibleMovement.contains(squarePanels[i][j].getSquare())) {
                            try {
                                squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                                squarePanels[i][j].repaint();
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
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
