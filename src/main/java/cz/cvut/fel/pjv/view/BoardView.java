package cz.cvut.fel.pjv.view;

import javax.swing.*;
import java.awt.*;

    public class BoardView extends JPanel {
        private static final int SQUARE_HEIGHT_WIDTH = 64;
        private static final String[] LETTERS = new String[]{"a", "b", "c", "d", "e", "f", "g", "h"};
        private static final int[] COORDINATES = new int[]{8,7,6,5,4,3,2,1};



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


        private static void createAndShowGui() {
            BoardView mainPanel = new BoardView();

            JFrame frame = new JFrame("Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.setMinimumSize(new Dimension(800, 540));
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
