package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Game;
import cz.cvut.fel.pjv.models.Player;
import cz.cvut.fel.pjv.server.Server2;
import cz.cvut.fel.pjv.view.BoardView;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server2 server2 = new Server2();
        server2.handleServer();
    }
}
