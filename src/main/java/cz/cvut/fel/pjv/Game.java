package cz.cvut.fel.pjv;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private boolean whiteOnMove;
    private Board board;

    public Game(Player playerWhite, Player playerBlack, boolean whiteOnMove, Board board) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.whiteOnMove = whiteOnMove;
        this.board = board;
    }

    public void Play() {
        while (!gameEnded()) {
            board.printBoard();
            Scanner sc = new Scanner(System.in);
            Piece chosen = null;
            if (whiteOnMove) {
                while (chosen == null || chosen.getColor() == Color.BLACK) {
                    int pick_x = sc.nextInt();
                    int pick_y = sc.nextInt();
                    chosen = board.pickPiece(pick_x, pick_y);
                }
            }
            else {
                while (chosen == null || chosen.getColor() == Color.WHITE) {
                    int pick_x = sc.nextInt();
                    int pick_y = sc.nextInt();
                    chosen = board.pickPiece(pick_x,pick_y);
                }
            }
            int pick_x = sc.nextInt();
            int pick_y = sc.nextInt();
            board.movePiece(chosen, pick_x,pick_y);
            whiteOnMove = !whiteOnMove;

        }
    }

    public boolean gameEnded() {
        return false;
    }
}
