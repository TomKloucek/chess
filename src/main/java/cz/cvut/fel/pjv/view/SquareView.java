package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SquareView extends JPanel {
    private Square square;
    private final JButton button;
    private Image dot = null;

    public Square getSquare() {
        return square;
    }

    public SquareView(Square square, JButton button, Board board, BoardView bw) {
        this.square = square;
        this.button = button;
        button.addActionListener(new ClickListener(square,board,bw));
    }

    public void setDot(Image dot) {
        this.dot = dot;
    }

    public SquareView(LayoutManager layout, Square square, Board board, BoardView bw) {
        super(layout);
        this.square = square;
        this.button = new JButton();
        button.addActionListener(new ClickListener(square,board, bw));
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        this.add(button);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image piece = Helpers.getPieceImage(square);
        g.drawImage(piece, 10, 10, this);

        if (dot != null) {
            g.drawImage(dot, 10, 10, this);
        }

    }

}
