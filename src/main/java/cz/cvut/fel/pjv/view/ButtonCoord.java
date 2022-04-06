package cz.cvut.fel.pjv.view;

import javax.swing.*;

public class ButtonCoord extends JButton {
    private int x;
    private int y;

    public ButtonCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public String getCoords() {
        return x + " " + y;
    }
}
