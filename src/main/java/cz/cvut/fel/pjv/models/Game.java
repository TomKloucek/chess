package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.pieces.Piece;
import cz.cvut.fel.pjv.view.BoardView;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private Board board;

    public Game(Player playerWhite, Player playerBlack, Board board) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.board = board;
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

    public static void createAndShowGui() {

        Board board = new Board();
        board.initializeBoard();
        BoardView mainPanel = new BoardView(board);

        Player p1 = new Player(Color.WHITE, null);
        Player p2 = new Player(Color.BLACK, null);
        Game game = new Game(p1, p2, board);

        State.getInstance();
        State.getInstance().setGame(game);

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(800, 679));
//            frame.setMaximumSize(new Dimension(800, 540));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
