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

    private long timeOfMoveStart = 0;

    private long timeLeftWhite = 600;

    private long timeLeftBlack = 600;


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

    public long getSecondsLeft(long timeLeft) {
        if (timeOfMoveStart == 0) {
            long elapsedTime = System.currentTimeMillis() - State.getInstance().getStartTime();
            long elapsedSeconds = timeLeft - (elapsedTime / 1000);
            long secondsLeft = elapsedSeconds % 60;
            return secondsLeft;
        } else {
            long elapsedTime = System.currentTimeMillis() - timeOfMoveStart;
            long elapsedSeconds = timeLeft - (elapsedTime / 1000);
            long secondsLeft = elapsedSeconds % 60;
            return secondsLeft;
        }
    }

    public long getMinutesLeft(long timeLeft) {
        if (timeOfMoveStart == 0) {
            long elapsedTime = System.currentTimeMillis() - State.getInstance().getStartTime();
            long elapsedSeconds = timeLeft - (elapsedTime / 1000);
            return elapsedSeconds / 60;
        } else {
            long elapsedTime = System.currentTimeMillis() - timeOfMoveStart;
            long elapsedSeconds = timeLeft - (elapsedTime / 1000);
            return elapsedSeconds / 60;

        }
    }

    public void setTimeOfMoveStart(long timeOfMoveStart) {
        this.timeOfMoveStart = timeOfMoveStart;
    }

    public void setTimeLeftWhite(long timeLeftWhite) {
        this.timeLeftWhite = timeLeftWhite;
    }

    public long getTimeLeftWhite() {
        return timeLeftWhite;
    }

    public void setTimeLeftBlack(long timeLeftBlack) {
        this.timeLeftBlack = timeLeftBlack;
    }

    public long getTimeLeftBlack() {
        return timeLeftBlack;
    }

    public void resetTimers(){
        setTimeLeftWhite(600);
        setTimeLeftBlack(600);
        setTimeOfMoveStart(0);
    }
}
