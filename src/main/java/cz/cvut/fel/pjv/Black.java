package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Game;

import javax.swing.*;

public class Black {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Game.createAndShowGui(Color.BLACK);
            }
        });
    }
}
