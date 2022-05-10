package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.server.Client;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private Board board;

    private boolean gameState = true;
    private Client client;

    private Player me;

    public Game(Player playerWhite, Player playerBlack, Board board) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.board = board;
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Player getPlayerBlack() {
        return playerBlack;
    }

    public Player getMe() {
        return me;
    }

    public void setMe(Player me) {
        this.me = me;
    }

    public boolean playForAi() {
        boolean turn = State.getInstance().isWhiteOnMove();
        if (turn && playerWhite instanceof AiPlayer) {
            return ((AiPlayer) playerWhite).doAMove();
        }
        if (!turn && playerBlack instanceof AiPlayer) {
            return ((AiPlayer) playerBlack).doAMove();
        }
        return false;
    }

    public void updateGame(String boardString) {
        board.stringToBoard(boardString, true);

        if (gameState) {
            long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
            long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
            State.getInstance().setTimeLeftWhite(minutesLeft * 60 + secondsLeft);
            System.out.println("ahoj1server");
            gameState = false;
        } else {
            long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
            long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
            State.getInstance().setTimeLeftBlack(minutesLeft * 60 + secondsLeft);
            System.out.println("ahoj2server");
            gameState = true;
        }
        State.getInstance().setTimeOfMoveStart(System.currentTimeMillis());


        State.getInstance().getGuiRef().getBw().repaintBoard();
        State.getInstance().reverseMove();
    }
}
