package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener implements Runnable{
    private final Socket clientSocket;
    public BufferedReader bufferedReader;

    public ClientListener(Socket client, BufferedReader bufferedReader){
        this.clientSocket = client;
        this.bufferedReader = bufferedReader;

    }

    @Override
    public void run() {
        try {
            String receivedMessage;
            while ((receivedMessage = bufferedReader.readLine()) != null){
                System.out.println("Server replied "
                        + receivedMessage);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }

        // closing the connection
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
