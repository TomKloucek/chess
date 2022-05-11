package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public PrintWriter printWriter;
    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;
    public ClientListener clientListener;
    public ClientWriter clientWriter;
    Socket player;


    public void connectToServer() throws IOException {
        player = new Socket("localhost", 4999);
        printWriter = new PrintWriter(player.getOutputStream());
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        setClientListener();
        setClientWriter();
    }
    public void setClientListener(){
        clientListener = new ClientListener(player, bufferedReader);
        new Thread(clientListener).start();
    }
    public void setClientWriter(){
        clientWriter = new ClientWriter(player, printWriter);
    }

    public void sendBoard(String board) {
        printWriter.println(board);
    }
    public void sendLogin() {
        printWriter.println("login:"+State.getInstance().getLogin());
    }

    public void disconnectFromServer() throws IOException {
        System.out.println(player);
        player.close();
        System.out.println("Player is closed = "+ player.isClosed());
    }
}
