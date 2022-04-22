package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client2 {
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
        Socket player = new Socket("localhost", 4999);
        PrintWriter printWriter = new PrintWriter(player.getOutputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(player.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        printWriter.println("hello there");
        printWriter.flush();

        while (true){
            String serverReply = bufferedReader.readLine();
            System.out.println("Server replied " + serverReply);
        }

    }


}
