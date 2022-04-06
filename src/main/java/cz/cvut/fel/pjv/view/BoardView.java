package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.Board;

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

        public BoardView(Board board) {
            super(new BorderLayout());
            this.board = board;
            initializeBoardLayeredPane();
            initializeSquares();
            //initializePieces();
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

        private void initializeSingleSquarePanel(int f, int r) {
            squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r]);
            squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setBackground(f % 2 == r % 2 ? COLOR_GREEN : COLOR_WHITE);
            boardPanel.add(squarePanels[f][r]);
        }



        /*

        private void drawBoard(Graphics g){
            Color COLOR_WHITE = new Color(238,238,213);
            Color COLOR_GREEN = new Color(125,148,93);
            Color textColor = COLOR_GREEN;
            Color tmp = COLOR_WHITE;
            for (int i = 0; i < 8; i++) {
                if (tmp == COLOR_GREEN){
                    tmp = COLOR_WHITE;
                }
                else if(tmp == COLOR_WHITE){
                    tmp = COLOR_GREEN;
                }

                for (int j = 0; j < 8; j++) {
                    if (tmp == COLOR_GREEN){
                        tmp = COLOR_WHITE;
                    }
                    else if(tmp == COLOR_WHITE){
                        tmp = COLOR_GREEN;
                    }
                    g.setColor(tmp);
                    g.fillRect(j*SQUARE_HEIGHT_WIDTH,i*SQUARE_HEIGHT_WIDTH, SQUARE_HEIGHT_WIDTH, SQUARE_HEIGHT_WIDTH);
                    g.drawString(Integer.toString(COORDINATES[i]), 4,i*SQUARE_HEIGHT_WIDTH+14);
                    if (i == 7){
                        if (tmp == COLOR_GREEN){
                            textColor = COLOR_WHITE;
                        }
                        else if (tmp == COLOR_WHITE){
                            textColor = COLOR_GREEN;
                        }
                        g.setColor(textColor);
                        g.drawString(LETTERS[j], j*SQUARE_HEIGHT_WIDTH+54,508);
                    }
                }
            }
        }
//        private void drawPieces(Graphics g){
//            g.drawImage()
//        }



        @Override
        protected void paintComponent(Graphics g) {
            drawBoard(g);
        }

*/
        private static void createAndShowGui() {
            Board board = new Board();
            board.initializeBoard();
            BoardView mainPanel = new BoardView(board);

            JFrame frame = new JFrame("Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.setMinimumSize(new Dimension(800, 670));
//            frame.setMaximumSize(new Dimension(800, 540));
            frame.setLocationByPlatform(true);
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGui();
                }
            });
        }
    }
