package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Square;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SquareView extends JPanel {
    private Square square;
    private Image figure;

    public SquareView(Square square) {
        this.square = square;
    }

    public SquareView(LayoutManager layout, Square square) {
        super(layout);
        this.square = square;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image piece = Helpers.getPieceImage(square);
        g.drawImage(piece, 10, 10, this);
    }

}
