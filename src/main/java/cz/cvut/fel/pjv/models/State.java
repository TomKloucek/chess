package cz.cvut.fel.pjv.models;

public class State {
    private static State _instance;

    private boolean whiteOnMove;
    private boolean end;
    private Game game;

    public State() {
        this.whiteOnMove = true;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isRunning() {
        return !end;
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
        System.out.println("sakra");
        whiteOnMove = !whiteOnMove;
    }

    public Game getGame() {
        return this.game;
    }
}
