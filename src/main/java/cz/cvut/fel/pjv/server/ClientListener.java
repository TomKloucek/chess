package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.State;

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
                System.out.println(receivedMessage);
                if (receivedMessage.equals("White")) {
                    State.getInstance().getGuiRef().openNetworkGame("", Color.WHITE);
                    System.out.println("Jsem bilej");
                }
                else  if (receivedMessage.equals("Black")) {
                    State.getInstance().getGuiRef().openNetworkGame("", Color.BLACK);
                    System.out.println("Jsem cernej");
                }
                else {
                    State.getInstance().getGame().updateGame(receivedMessage);
                }
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
