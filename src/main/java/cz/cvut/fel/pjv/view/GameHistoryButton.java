package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.models.GameHistory;

import javax.swing.*;

public class GameHistoryButton extends JButton {
    private GameHistory gh;

    public GameHistoryButton(GameHistory gh) {
        this.gh = gh;
    }

    public GameHistory getGh() {
        return gh;
    }

    public void setGh(GameHistory gh) {
        this.gh = gh;
    }
}
