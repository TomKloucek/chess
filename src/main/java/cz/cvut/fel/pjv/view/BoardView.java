package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.pieces.IPiece;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class BoardView extends JPanel {
        private static final int SQUARE_HEIGHT_WIDTH = 64;
        private static final String[] LETTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        private static final int[] COORDINATES = new int[]{8,7,6,5,4,3,2,1};

        Color COLOR_WHITE;
        Color COLOR_GREEN;

        public static final int SQUARE_DIMENSION = 64;
        private final Board board;
        private boolean boardReversed;
        private JLayeredPane boardLayeredPane;
        private JPanel boardPanel;
        public SquareView[][] squarePanels;

        private IPiece pickedIPiece;

        public BoardView(Board board) {
            super(new BorderLayout());
            this.board = board;
            if (State.getInstance().getGame() != null && State.getInstance().getGame().getMe().getColor() == cz.cvut.fel.pjv.models.Color.BLACK) {
                boardReversed = true;
            }

            try {
                BoardView.readColors();
            }
            catch (Exception e) {
                Logger.log(BoardView.class,"Constructor read colors", e.getMessage());
            }

            COLOR_WHITE = State.getInstance().getWhite();
            COLOR_GREEN = State.getInstance().getBlack();

            try {
                Helpers.writeColors();
            } catch(Exception e) {
                Logger.log(BoardView.class, "Constructor", e.getMessage());
            }

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

    public IPiece getPickedPiece() {
        return pickedIPiece;
    }

    public void setPickedPiece(int x, int y) {
         if (x == -1 && y == -1) {
             this.pickedIPiece = null;
             this.restoreBoard();
             this.repaintBoard();
         }
         else {
             this.pickedIPiece = board.pickPiece(x, y);
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
        public void deleteButtonsOnSquaresWithoutPiece(){
            for (int r = 7; r >= 0; r --) {
                for (int f = 0; f < 8; f++) {
                    if (squarePanels[f][r].getSquare().getPiece()==null) {
                        squarePanels[f][r].remove(squarePanels[f][r].getButton());
                        squarePanels[f][r].validate();
                        squarePanels[f][r].repaint();
                    }
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
            if (board.getPieces(cz.cvut.fel.pjv.models.Color.WHITE).isEmpty()) {
                squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r],board, this,true);
            }
            else {
                squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r],board, this,false);
            }
            squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
            squarePanels[f][r].setBackground(f % 2 == r % 2 ? COLOR_GREEN : COLOR_WHITE);
            boardPanel.add(squarePanels[f][r]);
        }

        public void reinitializeSquarePanels() {
            squarePanels = new SquareView[8][8];
            if (boardReversed) {
                for (int r = 0; r < 8; r ++) {
                    for (int f = 7; f >= 0; f--) {
                        squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r],board, this,false);
                        squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
                        squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
                        squarePanels[f][r].setBackground(f % 2 == r % 2 ? COLOR_GREEN : COLOR_WHITE);
                        boardPanel.add(squarePanels[f][r]);
                    }
                }
            } else {
                for (int r = 7; r >= 0; r --) {
                    for (int f = 0; f < 8; f++) {
                        squarePanels[f][r] = new SquareView(new GridLayout(1, 1),board.getBoard()[f][r],board, this,false);
                        squarePanels[f][r].setPreferredSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
                        squarePanels[f][r].setSize(new Dimension(SQUARE_DIMENSION, SQUARE_DIMENSION));
                        squarePanels[f][r].setBackground(f % 2 == r % 2 ? COLOR_GREEN : COLOR_WHITE);
                        boardPanel.add(squarePanels[f][r]);
                    }
                }
            }
        }

    public Board getBoard() {
        return board;
    }

    public static void readColors() throws Exception {
        try {
            File client = new File("client.txt");
            Scanner clientReader = new Scanner(client);
            while (clientReader.hasNextLine()) {
                String data = clientReader.nextLine();
                if (data.contains("Color")) {
                    String[] values = data.split(":");
                    String[] white = values[1].split(",");
                    String[] black = values[2].split(",");
                    System.out.println(Arrays.toString(white));
                    System.out.println(Arrays.toString(black));
                    State.getInstance().setWhite(new Color(Integer.parseInt(white[0].trim()),Integer.parseInt(white[1].trim()),Integer.parseInt(white[2].trim())));
                    State.getInstance().setBlack(new Color(Integer.parseInt(black[0].trim()),Integer.parseInt(black[1].trim()),Integer.parseInt(black[2].trim())));
                    System.out.println("hej");
                }
            }
            clientReader.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
