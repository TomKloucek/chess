package cz.cvut.fel.pjv.view;

import javax.swing.*;
/**
 * ButtonCoord is class which stores button coordinates.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
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

}
