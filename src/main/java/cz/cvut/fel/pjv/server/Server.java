package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.State;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server is class which handles online PvP games.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Server {

    public void handleServer() throws IOException {
        List<Socket> waiting = new ArrayList<>();
        int pointerId = 0;
        ServerSocket serverSocket = new ServerSocket(4999);
        serverSocket.setReuseAddress(true);

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());
                System.out.println(clientSocket.getRemoteSocketAddress() + " connected\n");
                waiting.add(clientSocket);

                if (waiting.size() % 2 == 0) {
                    handlePVPGame(waiting.get(0), waiting.get(1));
                    waiting.remove(0);
                    waiting.remove(0);
                }

            } catch (IOException e) {
                System.err.println("Accept failed.");
                Logger.log(Server.class, "handleServer", "Klient nebyl přijmut");
                System.exit(1);
            }
        }
    }


    /**
     * This method opens a room for online game.
     *
     * @return MainMenu reference
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void handlePVPGame(Socket playerWhite, Socket playerBlack) throws IOException {
        TwoClientsHandler twoClientsHandler = new TwoClientsHandler(playerWhite, playerBlack);
        new Thread(twoClientsHandler).start();

    }
}