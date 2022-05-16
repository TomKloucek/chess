package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HistoryServer implements Runnable{
    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
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
                System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());
                System.out.println(clientSocket.getRemoteSocketAddress() + " connected\n");
                PrintWriter toSend = new PrintWriter(clientSocket.getOutputStream());
                toSend.println(getAllGames());
                toSend.flush();
                clientSocket.close();

            } catch (IOException e) {
                System.err.println("Accept failed.");
                Logger.log(Server.class, "handleServer", "Klient nebyl přijmut");
                System.exit(1);
            }
        }
    }

    public String getAllGames() throws FileNotFoundException {
        File file = new File("games.txt");
        StringBuilder resultString = new StringBuilder();
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            resultString.append(sc.nextLine());
            resultString.append("@");
        }
        return resultString.toString().substring(0, resultString.toString().length() - 1);
    }
}
