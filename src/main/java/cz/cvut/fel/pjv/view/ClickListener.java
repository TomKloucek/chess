package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Square;

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
        ArrayList<Square> possibleMovement = square.getPiece().PossibleMovement(board);
        SquareView[][] squarePanels = bw.getSquarePanels();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (possibleMovement.contains(squarePanels[i][j].getSquare())) {
                    try {
                        squarePanels[i][j].setDot(ImageIO.read(new File("resources/dot.png")));
                        squarePanels[i][j].repaint();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    squarePanels[i][j].setDot(null);
                }
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
