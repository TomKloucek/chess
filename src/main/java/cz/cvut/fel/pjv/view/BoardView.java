package cz.cvut.fel.pjv.view;

import javax.swing.*;
import java.awt.*;

    public class BoardView extends JPanel {
        private static final int RECT_WIDTH = 50;
        private static final int RECT_HEIGHT = 50;


        private void drawBoard(Graphics g){
            Color COLOR_WHITE = new Color(255,255,255);
            Color COLOR_BROWN = new Color(132,97,40);
            // draw the rectangle here
            Color tmp = COLOR_BROWN;
            for (int i = 0; i < 8; i++) {
                if (tmp == COLOR_BROWN){
                    tmp = COLOR_WHITE;
                }
                else if(tmp == COLOR_WHITE){
                    tmp = COLOR_BROWN;
                }
                for (int j = 0; j < 8; j++) {
                    if (tmp == COLOR_BROWN){
                        tmp = COLOR_WHITE;
                    }
                    else if(tmp == COLOR_WHITE){
                        tmp = COLOR_BROWN;
                    }
                    g.setColor(tmp);
                    g.fillRect(j*50,i*50, RECT_WIDTH, RECT_HEIGHT);
                }
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            drawBoard(g);
        }


        // create the GUI explicitly on the Swing event thread
        private static void createAndShowGui() {
            BoardView mainPanel = new BoardView();

            JFrame frame = new JFrame("Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(mainPanel);
            frame.setMinimumSize(new Dimension(400, 428));
            frame.pack();
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
