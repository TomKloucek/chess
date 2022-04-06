package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Game;
import cz.cvut.fel.pjv.models.Player;
import cz.cvut.fel.pjv.view.BoardView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();

        Player p1 = new Player(Color.WHITE, null);
        Player p2 = new Player(Color.BLACK, null);
        Game game = new Game(p1, p2, true, board);

        BoardView boardView = new BoardView(board);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                boardView.createAndShowGui(boardView);
            }
        });

        game.Play();

    }
}
