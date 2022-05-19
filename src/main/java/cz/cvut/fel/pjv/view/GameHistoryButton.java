package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.GameHistory;

import javax.swing.*;

/**
 * This class is custom button with added GameHistory field.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class GameHistoryButton extends JButton {
    private GameHistory gh;

    public GameHistoryButton(GameHistory gh) {
        this.gh = gh;
    }

    public GameHistory getGh() {
        return gh;
    }

}
