package cz.cvut.fel.pjv;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();

        Player p1 = new Player(Color.WHITE, null);
        Player p2 = new Player(Color.BLACK, null);
        Game game = new Game(p1, p2, true, board);
        game.Play();
    }
}
