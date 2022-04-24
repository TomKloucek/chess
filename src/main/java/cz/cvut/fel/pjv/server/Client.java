package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    PrintWriter printWriter;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;

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

        printWriter = new PrintWriter(player.getOutputStream());
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        printWriter.println("Hello I am player");
        printWriter.flush();
        MainMenu game = new MainMenu();
        game.showGameDialogue();
//        Scanner sc = new Scanner(System.in);
//        String line = null;
//        while (!"exit".equalsIgnoreCase(line)) {
//
//            // reading from user
//            line = sc.nextLine();
//
//            // sending the user input to server
//            printWriter.println(line);
//            printWriter.flush();
//
//            // displaying server reply
//            System.out.println("Server replied "
//                    + bufferedReader.readLine());
//        }
//
//        // closing the connection
//        player.close();
    }

//    public void readFromServer() throws IOException {
//        String serverReply;
//        while ((serverReply = bufferedReader.readLine()) != null)
//
//
//            System.out.println("Server replied " + serverReply);
//    }

}
