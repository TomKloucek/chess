package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.server.Client;
import cz.cvut.fel.pjv.view.BoardView;
import cz.cvut.fel.pjv.view.MainMenu;

import javax.swing.*;

public class State {
    private static State _instance;
    private String login = null;
    private boolean whiteOnMove;
    private boolean end;
    private Game game;

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
