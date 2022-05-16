package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LastGamesView extends JPanel {
    private List<GameHistory> gameInfo;
    private ArrayList<GameHistoryButton> buttons;
    public LastGamesView(String moves) {
        this.gameInfo = Helpers.gamesFromString(moves);
        this.setLayout(new GridLayout(gameInfo.size(),1));
        generateButtons();
    }
    public void addButtons() {
        for (GameHistoryButton button : this.buttons) {
            this.add(button);
        }
    }

    public ArrayList<GameHistoryButton> getButtons() {
        return buttons;
    }

    public void generateButtons() {
        ArrayList<GameHistoryButton> buttons = new ArrayList<>();
        for (GameHistory gh : gameInfo) {
            GameHistoryButton topten = new GameHistoryButton(gh);
            topten.setBackground(Color.white);
            topten.setForeground(Color.black);
            topten.setText("Bily:"+gh.getPlayerWhite()+" - Cerny: "+ gh.getPlayerBlack() + " - Datum hry:"+gh.getTimeGameFinished());
            topten.setCursor(new Cursor(Cursor.HAND_CURSOR));
            topten.setSize(600,10);
            buttons.add(topten);
        }
        this.buttons = buttons;
    }

    public List<GameHistory> getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(List<GameHistory> gameInfo) {
        this.gameInfo = gameInfo;
    }
}
