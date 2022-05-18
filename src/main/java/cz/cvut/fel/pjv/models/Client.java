package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.server.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public PrintWriter printWriter;
    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;
    public ClientHandler clientListener;
    Socket player;


    public void connectToServer() throws IOException {
        player = new Socket("localhost", 4999);
        printWriter = new PrintWriter(player.getOutputStream());
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        setClientHandler();
    }

    public String getPlayerStatistics(String username) throws IOException {
        player = new Socket("localhost", 5001);
        printWriter = new PrintWriter(player.getOutputStream());
        printWriter.println(username);
        printWriter.flush();
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

    public String getGamesHistory() throws IOException {
        player = new Socket("localhost", 5000);
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }
    public void setClientHandler(){
        clientListener = new ClientHandler(player, bufferedReader, printWriter);
        new Thread(clientListener).start();
    }

    public void disconnectFromServer() throws IOException {
        player.close();
    }
}
