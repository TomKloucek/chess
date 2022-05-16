package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.server.Client;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private Board board;

    String gameString = "\"RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";
    private ArrayList<String> moves;

    private boolean gameState = true;
    private Client client;

    private Player me;

    public Game(Player playerWhite, Player playerBlack, Board board) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.board = board;
        this.moves = new ArrayList<>();
        this.moves.add("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");
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
        State.getInstance().getGuiRef().getBw().repaintBoard();
        if(board.mated(Color.WHITE) && !State.getInstance().isWhiteOnMove()){
            System.out.println("Bilej v matu");
            int answer = JOptionPane.showConfirmDialog(null, "Černý vyhrál, nyní budete odpojen.", "Upozornění", JOptionPane.DEFAULT_OPTION);
            if(answer == 0 || answer == -1){
                State.getInstance().getGuiRef().closeGameFrame(true);
            }
        }
        else if(board.mated(Color.BLACK) && State.getInstance().isWhiteOnMove()){
            System.out.println("Cerny v matu");
            int answer = JOptionPane.showConfirmDialog(null, "Bílý vyhrál, nyní budete odpojen.", "Upozornění", JOptionPane.DEFAULT_OPTION);
            if(answer == 0 || answer == -1){
                State.getInstance().getGuiRef().closeGameFrame(true);
            }
        }
        State.getInstance().reverseMove();
        State.getInstance().getGame().addMove(boardString);
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void addMove(String move) {
        this.moves.add(move);
    }
}
