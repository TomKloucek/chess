package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * LastGameView serves as GUI for showing all already played games.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class LastGamesView extends JPanel {
    private List<GameHistory> gameInfo;
    private ArrayList<GameHistoryButton> buttons;
    public LastGamesView(String moves) {
        this.gameInfo = Helpers.gamesFromString(moves);
        this.setLayout(new GridLayout(gameInfo.size(),1));
        generateButtons();
    }
    /**
     * <p>
     * For every game loaded in the list adds buttons to the view.
     * </p>
     */
    public void addButtons() {
        for (GameHistoryButton button : this.buttons) {
            this.add(button);
        }
    }

    public ArrayList<GameHistoryButton> getButtons() {
        return buttons;
    }

    /**
     * <p>
     * This method generates button for every game that is in the gameInfo list.
     * </p>
     */
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
}
