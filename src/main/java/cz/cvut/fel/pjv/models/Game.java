package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.pieces.Piece;
import cz.cvut.fel.pjv.server.Client;
import cz.cvut.fel.pjv.view.BoardView;

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

    public String boardToString() {
        StringBuilder game = new StringBuilder();
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.board.getBoard()[j][i].getPiece();
                if (piece == null) {
                    game.append(" ");
                }
                else {
                    game.append(piece);
                }
                game.append(",");
            }
        }
        game.deleteCharAt(game.length() - 1);
        return game.toString();
    }

    public Player getMe() {
        return me;
    }

    public void setMe(Player me) {
        this.me = me;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static void createAndShowGui(Color color) {

        Board board = new Board();
        board.stringToBoard("RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , ,Wd4 , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X, ,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1");

        Player p1 = new Player(Color.WHITE, null);
        Player p2 = new Player(Color.BLACK, null);
        Game game = new Game(p1, p2, board);


        /*Client client = new Client();
        client.connectToServer();*/

        if (color == Color.WHITE) {
            game.setMe(p1);
        }
        else {
            game.setMe(p2);
        }

        //game.setClient(client);

        State.getInstance();
        State.getInstance().setGame(game);

        //board.initializeBoard();
        BoardView mainPanel = new BoardView(board);
//        System.out.println(game.boardToString());

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(800, 679));
//            frame.setMaximumSize(new Dimension(800, 540));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
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
}
