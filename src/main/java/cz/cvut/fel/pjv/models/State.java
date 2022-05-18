package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.view.MainMenu;

import javax.swing.*;
import java.awt.Color;

public class State {
    private static State _instance;
    private String login = null;
    private Color white;
    private Color black;
    private boolean whiteOnMove;
    private Game game;

    private int gameLength;

    private long startTime;

    private long timeOfMoveStart = 0;

    private long timeLeftWhite = 600;

    private long timeLeftBlack = 600;

    private String piecesSet = "set1";

    private Client client;

    private MainMenu guiRef;

    public String getLogin() {
        return login;
    }

    public void setLogin() {
        this.login = JOptionPane.showInputDialog("Zadejte svoje herní jméno:");
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
        if (!State.getInstance().isWhiteOnMove()) {
            long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
            long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
            State.getInstance().setTimeLeftWhite(minutesLeft * 60 + secondsLeft);
        } else {
            long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
            long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
            State.getInstance().setTimeLeftBlack(minutesLeft * 60 + secondsLeft);
        }
        State.getInstance().setTimeOfMoveStart(System.currentTimeMillis());
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
            return elapsedSeconds % 60;
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
    public void setGameLength(int gameLength) {
        this.gameLength = gameLength;
    }

    public void setupPlayersTimes() {
        this.timeLeftBlack = Helpers.getGameLength(this.gameLength);
        this.timeLeftWhite = Helpers.getGameLength(this.gameLength);
    }

    public void resetTimers(){
        setTimeLeftWhite(Helpers.getGameLength(this.gameLength));
        setTimeLeftBlack(Helpers.getGameLength(this.gameLength));
        setTimeOfMoveStart(0);
    }

    public String getPiecesSet() {
        return piecesSet;
    }

    public void setPiecesSet(int pieceSet) {
        switch (pieceSet) {
            case 1 -> this.piecesSet = "set2";
            case 2 -> this.piecesSet = "set3";
            default -> this.piecesSet = "set1";
        }
    }

    public void setPiecesSet(String set) {
        this.piecesSet = set;
    }
}
