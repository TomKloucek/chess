package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.models.Game;
import cz.cvut.fel.pjv.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Client {
    public PrintWriter printWriter;
    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;
    Socket player;

    //    PrintWriter printWriter;
//    InputStreamReader inputStreamReader;
//    BufferedReader bufferedReader;
//    public void connectToServer() throws IOException{
//        Socket player = new Socket("localhost", 4999);
//        printWriter = new PrintWriter(player.getOutputStream());
//        inputStreamReader = new InputStreamReader(player.getInputStream());
//        bufferedReader = new BufferedReader(inputStreamReader);
//
//        System.out.println(receiveMessage());
//        }
//    public void sendMessage(String message){
//        printWriter.println(message);
//        printWriter.flush();
//    }
//    public String receiveMessage() throws IOException {
//        String serverMessage = bufferedReader.readLine();
//        return "server: " + serverMessage;
//    }
    public void connectToServer() throws IOException {
        player = new Socket("localhost", 4999);
        printWriter = new PrintWriter(player.getOutputStream());
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter.println("Hello I am player");
        printWriter.flush();
        ClientListener clientListener = new ClientListener(player, printWriter, bufferedReader);
        new Thread(clientListener).start();
    }


    //    public void readFromServer() throws IOException {
//        String serverReply;
//        while ((serverReply = bufferedReader.readLine()) != null)
//
//
//            System.out.println("Server replied " + serverReply);
//    }
    public void showGame(MainMenu game) throws IOException {
        game.showGameDialogue();
    }

}
