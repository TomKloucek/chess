package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.models.State;

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
    public void setClientHandler(){
        clientListener = new ClientHandler(player, bufferedReader, printWriter);
        new Thread(clientListener).start();
    }

    public void disconnectFromServer() throws IOException {
        System.out.println(player);
        player.close();
        System.out.println("Player is closed = "+ player.isClosed());
    }
}
