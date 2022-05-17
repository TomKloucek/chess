package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.server.Client;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        board.addMoveToNotation(formatMoveToNotation(diff(gameString, boardString).toString()));
        State.getInstance().getGuiRef().updateNotation(board.getNotation());
        board.addCounterHelper();
        gameString = boardString;
        State.getInstance().getGame().addMove(boardString);
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public void addMove(String move) {
        this.moves.add(move);
    }
    public static Pair<String> diff(String a, String b) {
        return diffHelper(a, b, new HashMap<>());
    }

    /**
     * Recursively compute a minimal set of characters while remembering already computed substrings.
     * Runs in O(n^2).
     */
    private static Pair<String> diffHelper(String a, String b, Map<Long, Pair<String>> lookup) {
        long key = ((long) a.length()) << 32 | b.length();
        if (!lookup.containsKey(key)) {
            Pair<String> value;
            if (a.isEmpty() || b.isEmpty()) {
                value = new Pair<>(a, b);
            } else if (a.charAt(0) == b.charAt(0)) {
                value = diffHelper(a.substring(1), b.substring(1), lookup);
            } else {
                Pair<String> aa = diffHelper(a.substring(1), b, lookup);
                Pair<String> bb = diffHelper(a, b.substring(1), lookup);
                if (aa.first.length() + aa.second.length() < bb.first.length() + bb.second.length()) {
                    value = new Pair<>(a.charAt(0) + aa.first, aa.second);
                } else {
                    value = new Pair<>(bb.first, b.charAt(0) + bb.second);
                }
            }
            lookup.put(key, value);
        }
        return lookup.get(key);
    }

    public static class Pair<T> {
        public Pair(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public final T first, second;

        public String toString() {
            return (String) second+",";
        }
    }
    public void setGameString(String gameString){
        this.gameString = gameString;
    }




    public String formatMoveToNotation(String receivedMessage){
        System.out.println(receivedMessage);
        String result ="";
        char[] character = new char[receivedMessage.length()];

        for (int i = 0; i < receivedMessage.length(); i++) {
            character[i] = receivedMessage.charAt(i);
        }

        for (int i = 0; i < receivedMessage.length(); i++){
            if((character[i] == 'B' && character[i+1] == 'B' ) ||
                    (character[i] != 'X' && character[i] != 'W' && character[i] != ',' && character[i] != ' ' && character[i] != 'B') ){
                    result += Character.toString(character[i]);
            }

        }
        return result;
    }
}
