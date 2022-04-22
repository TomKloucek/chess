package cz.cvut.fel.pjv.server;

import java.net.Socket;
import java.sql.Timestamp;

public class Room {
    private Socket playerWhite;
    private Socket playerBlack;

    private String gameState = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";

    public Room(Socket playerWhite, Socket playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    public Room(Socket player) {
        if (new Timestamp(System.currentTimeMillis()).getNanos() % 2 == 0) {
            playerWhite = player;
            // TODO ai player
        }
        else {
            playerBlack = player;
            // TODO ai player
        }
    }

    public Socket getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(Socket playerWhite) {
        this.playerWhite = playerWhite;
    }

    public Socket getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(Socket playerBlack) {
        this.playerBlack = playerBlack;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }
}
