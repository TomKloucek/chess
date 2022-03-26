package cz.cvut.fel.pjv;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Player playerWhite;
    private Player playerBlack;
    private boolean whiteOnMove;
    private Board board;
    private boolean state = false;

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
    }

    public boolean gameEnded() {
        return state;
    }
}
