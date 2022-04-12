package cz.cvut.fel.pjv.models;

public class State {
    private static State _instance;

    private boolean whiteOnMove;
    private Game game;

    public State() {
        this.whiteOnMove = true;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public static State getInstance()
    {
        if (_instance == null)
        {
            _instance = new State();
        }
        return _instance;
    }

    public boolean isWhiteOnMove() {
        return whiteOnMove;
    }

    public void reverseMove() {
        whiteOnMove = !whiteOnMove;
        System.out.println(this.game.boardToString());
    }

    public Game getGame() {
        return this.game;
    }
}