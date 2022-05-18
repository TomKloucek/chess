package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.server.HistoryServer;
import cz.cvut.fel.pjv.server.PlayerServer;
import cz.cvut.fel.pjv.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        HistoryServer hserver = new HistoryServer();
        PlayerServer pserver = new PlayerServer();
        new Thread((hserver)).start();
        new Thread((pserver)).start();
        server.handleServer();
    }
}
