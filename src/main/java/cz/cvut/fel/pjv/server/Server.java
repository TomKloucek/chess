package cz.cvut.fel.pjv.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
                    handlePVPGame(waiting.get(0), waiting.get(1), pointerId);
                    waiting.remove(0);
                    waiting.remove(0);
                }

            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }


    }

    public void handleAiGame(Socket player) throws IOException {
        ClientHandler clientSocket = new ClientHandler(player);
        new Thread(clientSocket).start();
    }

    public void handlePVPGame(Socket playerWhite, Socket playerBlack, int pointerId) throws IOException {
        int firstPlayerId = pointerId;
        pointerId +=1;
        int secondPlayerId = pointerId;

        ClientHandler clientSocketWhite = new ClientHandler(playerWhite);
        new Thread(clientSocketWhite).start();
        ClientHandler clientSocketBlack = new ClientHandler(playerBlack);
        new Thread(clientSocketBlack).start();

        GameRoomThread gameRoomThread = new GameRoomThread(playerWhite, playerBlack, firstPlayerId, secondPlayerId);
        new Thread(gameRoomThread).start();
    }
}