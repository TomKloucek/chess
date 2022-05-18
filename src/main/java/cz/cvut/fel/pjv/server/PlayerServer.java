package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class PlayerServer implements Runnable {

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5001);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            serverSocket.setReuseAddress(true);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("nice");
                String name = null;
                name = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())).readLine();
                System.out.println(name);
                System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());
                System.out.println(clientSocket.getRemoteSocketAddress() + " connected\n");
                PrintWriter toSend = new PrintWriter(clientSocket.getOutputStream());
                toSend.println(getPlayerStatistic(name));
                toSend.flush();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Accept failed.");
                Logger.log(Server.class, "handleServer", "Klient nebyl přijmut");
                System.exit(1);
            }
        }
    }

    public String getPlayerStatistic(String playerName) throws FileNotFoundException {
        File file = new File("games.txt");
        int wins = 0;
        int loses = 0;
        int draws = 0;
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String game = sc.nextLine();
            String[] data = game.split("\\$");
            if (data[1].equals(playerName)) {
                if (data[4].equals("1")) {
                    wins++;
                }
                else if(data[4].equals("2")) {
                    loses++;
                }
                else {
                    draws++;
                }
            }
            else if (data[2].equals(playerName)) {
                if (data[4].equals("1")) {
                    loses++;
                }
                else if(data[4].equals("2")) {
                    wins++;
                }
                else {
                    draws++;
                }
            }
        }
        return (wins+loses+draws)+","+wins+","+loses+","+draws;
    }
}

