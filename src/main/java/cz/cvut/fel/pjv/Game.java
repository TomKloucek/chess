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
        Scanner sc = new Scanner(System.in);
        while (!gameEnded()) {
            board.printBoard();
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
            System.out.println(chosen.PossibleMovement(board));
            int pick_x = sc.nextInt();
            int pick_y = sc.nextInt();
            boolean result = board.movePiece(chosen, pick_x,pick_y);
            if (result) {
                whiteOnMove = !whiteOnMove;
            }
        }
    }

    public boolean gameEnded() {
        return false;
    }
}
