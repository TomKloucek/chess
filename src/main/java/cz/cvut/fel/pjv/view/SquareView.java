package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SquareView extends JPanel {
    private Square square;
    private Board board;
    private final ButtonCoord button;
    private Image dot = null;

    public Square getSquare() {
        return square;
    }

    public SquareView(Square square, Board board, BoardView bw) {
        this.square = square;
        this.button = new ButtonCoord(square.getX()-1,square.getY());
        button.addActionListener(new ClickListener(square,board,bw));
    }

    public void setDot(Image dot) {
        this.dot = dot;
    }

    public Image getDot() {
        return dot;
    }

    public SquareView(LayoutManager layout, Square square, Board board, BoardView bw) {
        super(layout);
        this.square = square;
        this.board = board;
        this.button = new ButtonCoord(square.getX()-1, square.getY());
        button.addActionListener(new ClickListener(square,board, bw));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if(square.getPiece()!=null) {
            this.add(button);
        }
    }
    public void setButton(){
        if(square.getPiece()!=null || this.getDot()!=null) {
            this.add(button);
        }
    }
    public ButtonCoord getButton(){
        return this.button;
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Image piece = Helpers.getPieceImage(square, board);
        g.drawImage(piece, 8, 8, this);


        if (dot != null) {
            g.drawImage(dot, 8, 8, this);
        }


    }

}
