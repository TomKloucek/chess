package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.models.*;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public void HandleServer() throws IOException {

        Game game;
        List<Socket> waiting = new ArrayList<>();

        ServerSocket serverSocket = null;
        try {
            System.out.println(InetAddress.getLocalHost());
            serverSocket = new ServerSocket(7165,0,InetAddress.getLocalHost());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 7165.");
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected!");
                System.out.println(clientSocket.getRemoteSocketAddress()+" connected\n");
                waiting.add(clientSocket);
                if (waiting.size() == 1) {

                    handleAiGame(waiting.get(0));
                }
                if (waiting.size() == 2) {
                    handleGame(waiting.get(0),waiting.remove(1));
                }
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }
    }

    public void handleClient(Socket client) throws IOException {
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        String inputLine;
        boolean connected = true;
        while (connected)
        {
            try {
                inputLine = in.readLine();
                System.out.println("Client said : "+inputLine);

                if (inputLine == null)
                {
                    System.out.println("Client Disconnected!");
                    connected = false;
                }
            }
            catch(java.net.SocketTimeoutException e)
            {
                System.out.println("Timed out trying to read from socket");
            }

        }
    }

    public void handleAiGame(Socket player) throws IOException {
        Room room = new Room(player);

        DataOutputStream playerOut = new DataOutputStream(player.getOutputStream());
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(player.getOutputStream()));
        DataInputStream playerIn = new DataInputStream(player.getInputStream());

        writer.write("HelloThere");
        writer.flush();

        System.out.println("odeslano");


//        playerOut.writeBytes(room.getGameState());


    }

    public void handleGame(Socket white, Socket black) throws IOException {

        Room room = new Room(white,black);

        DataOutputStream whiteOut = new DataOutputStream(white.getOutputStream());
        DataOutputStream blackOut = new DataOutputStream(black.getOutputStream());

        DataInputStream whiteIn = new DataInputStream(white.getInputStream());
        DataInputStream blackIn = new DataInputStream(black.getInputStream());


        whiteOut.writeBytes(room.getGameState());
        blackOut.writeBytes(room.getGameState());
    }

}
