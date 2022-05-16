package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.server.HistoryServer;
import cz.cvut.fel.pjv.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        HistoryServer hserver = new HistoryServer();
        new Thread((hserver)).start();
        server.handleServer();
    }
}
