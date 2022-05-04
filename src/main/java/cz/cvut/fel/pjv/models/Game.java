package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.pieces.Piece;
import cz.cvut.fel.pjv.server.Client;
import cz.cvut.fel.pjv.view.BoardView;
import cz.cvut.fel.pjv.view.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private Board board;

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
//        State.getInstance().getGuiRef().getBw().reinitializeSquarePanels();
        State.getInstance().getGuiRef().getBw().repaintBoard();
        State.getInstance().reverseMove();
    }
}
