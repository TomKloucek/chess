package cz.cvut.fel.pjv.models;

import java.util.List;

public class GameHistory {
    private String playerWhite;
    private String playerBlack;

    private String timeGameFinished;
    private List<String> moves;

    public GameHistory(String playerWhite, String playerBlack, List<String> moves, String timeGameFinished) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.moves = moves;
        this.timeGameFinished = timeGameFinished;
    }

    public String getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(String playerWhite) {
        this.playerWhite = playerWhite;
    }

    public String getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(String playerBlack) {
        this.playerBlack = playerBlack;
    }

    public List<String> getMoves() {
        return moves;
    }

    public void setMoves(List<String> moves) {
        this.moves = moves;
    }

    public String getTimeGameFinished() {
        return timeGameFinished;
    }

    public void setTimeGameFinished(String timeGameFinished) {
        this.timeGameFinished = timeGameFinished;
    }
}
