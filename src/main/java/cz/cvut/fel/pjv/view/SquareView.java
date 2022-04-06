package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.Square;

import javax.swing.*;

public class SquareView extends JButton {
    private Square square;

    public SquareView(Square square) {
        this.square = square;
    }
}
