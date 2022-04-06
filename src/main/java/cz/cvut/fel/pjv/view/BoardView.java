package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoardView extends JPanel {
        private static final int SQUARE_HEIGHT_WIDTH = 64;
        private static final String[] LETTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        private static final int[] COORDINATES = new int[]{8,7,6,5,4,3,2,1};

        Color COLOR_WHITE = new Color(238,238,213);
        Color COLOR_GREEN = new Color(125,148,93);

        public static final int SQUARE_DIMENSION = 64;
        private Board board;
        private boolean boardReversed;
        private JLayeredPane boardLayeredPane;
        private JPanel boardPanel;
        private SquareView[][] squarePanels;

        private Piece pickedPiece;

        public BoardView(Board board) {
            super(new BorderLayout());
            this.board = board;
            initializeBoardLayeredPane();
            initializeSquares();
        }

        public SquareView[][] getSquarePanels() {
            return this.squarePanels;
        }

        private void initializeBoardLayeredPane() {
            boardPanel = new JPanel(new GridLayout(8, 8));
            boardPanel.setBounds(0, 0, 640, 640);
            boardLayeredPane = new JLayeredPane();
            boardLayeredPane.setPreferredSize(new Dimension(640, 640));
            boardLayeredPane.add(boardPanel, JLayeredPane.DEFAULT_LAYER);
            boardLayeredPane.setVisible(true);
            this.add(boardLayeredPane, BorderLayout.CENTER);
        }

    public Piece getPickedPiece() {
        return pickedPiece;
    }

    public void setPickedPiece(int x, int y) {
         if (x == -1 && y == -1) {
             this.pickedPiece = null;
             this.restoreBoard();
             this.repaintBoard();
         }
         else {
             this.pickedPiece = board.pickPiece(x, y);
         }
    }

    private void initializeSquares() {
            squarePanels = new SquareView[8][8];
            if (boardReversed) {
                for (int r = 0; r < 8; r ++) {
                    for (int f = 7; f >= 0; f--) {
                        initializeSingleSquarePanel(f, r);
                    }
                }
            } else {
                for (int r = 7; r >= 0; r --) {
                    for (int f = 0; f < 8; f++) {
                        initializeSingleSquarePanel(f, r);
                    }
                }
            }
        }

        public void repaintBoard() {
            for (int r = 7; r >= 0; r --) {
                for (int f = 0; f < 8; f++) {
                    squarePanels[f][r].repaint();
                }
            }
        }

    public void restoreBoard() {
        for (int r = 7; r >= 0; r --) {
            for (int f = 0; f < 8; f++) {
                squarePanels[f][r].setDot(null);
            }
        }
    }

        private void initializeSingleSquarePanel(int f, int r) {
            squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r],board, this);
            squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setBackground(f % 2 == r % 2 ? COLOR_GREEN : COLOR_WHITE);
            boardPanel.add(squarePanels[f][r]);
        }

    }
