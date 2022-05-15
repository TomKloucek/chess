package cz.cvut.fel.pjv.view;

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
    public LastGamesView() {

        // Testing
        GameHistory gh;
        this.gameInfo = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            gh = new GameHistory("Karel","Omacka",null,"12.5.2001");
            this.gameInfo.add(gh);
        }
        ArrayList<String> moves = new ArrayList<>();
        moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
        moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , ,Wc4 , , , , , , , , , , , , , ,Wa2X,Wb2X, ,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
        moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X, ,Be7X,Bf7X,Bg7X,Bh7X, , , ,Bd6X, , , , , , , , , , , , , , ,Wc4 , , , , , , , , , , , , , ,Wa2X,Wb2X, ,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
        moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X, ,Be7X,Bf7X,Bg7X,Bh7X, , , ,Bd6X, , , , , , , , , , , , , , ,Wc4X, ,We4 , , , , , , , , , , , ,Wa2X,Wb2X, ,Wd2X, ,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
        moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X, ,Be7X, ,Bg7X,Bh7X, , , ,Bd6X, ,Bf6X, , , , , , , , , , , , ,Wc4X, ,We4 , , , , , , , , , , , ,Wa2X,Wb2X, ,Wd2X, ,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
        GameHistory test = new GameHistory("Test","Testik",moves,"15.05.2022");
        this.gameInfo.add(test);
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
}
