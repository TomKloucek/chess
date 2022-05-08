package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.server.Client;
import cz.cvut.fel.pjv.view.BoardView;
import cz.cvut.fel.pjv.view.MainMenu;

import javax.swing.*;
import java.awt.Color;

public class State {
    private static State _instance;
    private String login = null;
    private Color white;
    private Color black;
    private boolean whiteOnMove;
    private boolean end;
    private Game game;

    private long startTime;

    private Client client;

    private MainMenu guiRef;

    public String getLogin() {
        return login;
    }

    public void setLogin() {
        this.login = JOptionPane.showInputDialog("Zadej svoje herni jmeno:");
    }

    public State() {
        this.whiteOnMove = true;
    }

    public void setGame(Game game) {
        this.game = game;
        this.startTime = System.currentTimeMillis();
    }

    public Color getWhite() {
        if (white == null) {
            white = new Color(238,238,213);
        }
        return white;
    }

    public void setWhite(Color color) {
        this.white = color;
    }

    public Color getBlack() {
        if (black == null) {
            black = new Color(125,148,93);
        }
        return black;
    }

    public void setBlack(Color color) {
        this.black = color;
    }

    public boolean isRunning() {
        return !end;
    }

    public static State getInstance()
    {
        if (_instance == null) {
            synchronized (State.class) {
                if (_instance == null) {
                    _instance = new State();
                }
            }
        }
            return _instance;
    }


    public MainMenu getGuiRef() {
        return guiRef;
    }

    public long getStartTime() {
        return startTime;
    }


    public void setGuiRef(MainMenu guiRef) {
        this.guiRef = guiRef;
    }

    public boolean isWhiteOnMove() {
        return whiteOnMove;
    }

    public void reverseMove() {
        whiteOnMove = !whiteOnMove;
    }

    public void resetMove() {
        whiteOnMove = true;
    }

    public Game getGame() {
        return this.game;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
