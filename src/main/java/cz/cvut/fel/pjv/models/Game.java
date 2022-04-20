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

    /* public void Play() {
        Scanner sc = new Scanner(System.in);
        while (!gameEnded()) {
            board.printBoard();
            Piece chosen = null;
            if (whiteOnMove) {
                while (chosen == null || chosen.getColor() == Color.BLACK) {
                    int pick_x = sc.nextInt();
                    int pick_y = sc.nextInt();
                    chosen = board.pickPiece(pick_x, pick_y);

                    if (chosen == null){
                        System.out.println("Na vybraném poli není figurka, nebo vybraná figurka nemá žádný validní pohyb.");
                    }
                    else if(board.whiteInCheck() && !board.canBlockOrEscapeFromCheck(chosen)){
                        System.out.println("Tento výběr vás nedostane z šachu.");
                        chosen = null;
                    }

                }
            }
            else {
                while (chosen == null || chosen.getColor() == Color.WHITE) {
                    int pick_x = sc.nextInt();
                    int pick_y = sc.nextInt();
                    chosen = board.pickPiece(pick_x,pick_y);

                    if (chosen == null){
                        System.out.println("Na vybraném poli není figurka, nebo vybraná figurka nemá žádný validní pohyb.");
                    }
                    else if(board.blackInCheck() && !board.canBlockOrEscapeFromCheck(chosen)){
                        System.out.println("Tento výběr vás nedostane z šachu.");
                    }
                }
            }
            if (board.whiteInCheck() || board.blackInCheck()){
                System.out.println(board.possibleMovesToUncheck(chosen));
            }
            else {
                System.out.println(chosen.PossibleMovement(board));
            }
            int pick_x = sc.nextInt();
            int pick_y = sc.nextInt();
            boolean result = board.movePiece(chosen, pick_x,pick_y);
            if (result) {
                if (!whiteOnMove) {
                    if (board.whiteInCheck()) {
                        if (board.Mated(Color.WHITE) && board.getSquaresToBlock(board.getPieces(Color.BLACK)).isEmpty()) {
                            System.out.println("Cerny vyhral");
                            state = true;
                        }
                    }
                }
                else {
                    if (board.blackInCheck()) {
                        if (board.Mated(Color.BLACK) && board.getSquaresToBlock(board.getPieces(Color.WHITE)).isEmpty()) {
                            System.out.println("Bily vyhral");
                            state = true;
                        }
                    }
                }
                whiteOnMove = !whiteOnMove;
            }
        }
    }*/

    public static void createAndShowGui(Color color) {

        Board board = new Board();

        Player p1 = new Player(Color.WHITE, null);
        Player p2 = new Player(Color.BLACK, null);
        Game game = new Game(p1, p2, board);

        Client client = new Client();
        client.connectToServer();

        if (color == Color.WHITE) {
            game.setMe(p1);
        }
        else {
            game.setMe(p2);
        }

        game.setClient(client);

        State.getInstance();
        State.getInstance().setGame(game);

        board.initializeBoard();
        BoardView mainPanel = new BoardView(board);

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(800, 679));
//            frame.setMaximumSize(new Dimension(800, 540));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
