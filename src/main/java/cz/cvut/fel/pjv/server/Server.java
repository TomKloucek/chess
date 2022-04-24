package cz.cvut.fel.pjv.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    PrintWriter printWriter;

    public void handleServer() throws IOException {
        List<Socket> waiting = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(4999);
        serverSocket.setReuseAddress(true);

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected " + clientSocket.getInetAddress().getHostAddress());
                System.out.println(clientSocket.getRemoteSocketAddress()+" connected\n");
                waiting.add(clientSocket);
                if (waiting.size() == 1) {
                    handleAiGame(waiting.get(0));
                    waiting.remove(clientSocket);
                }

            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
        }


    }
    public void handleAiGame(Socket player) throws IOException {
        ClientHandler clientSocket  = new ClientHandler(player);
        new Thread(clientSocket).start();

//        inputStreamReader = new InputStreamReader(player.getInputStream());
//        bufferedReader = new BufferedReader(inputStreamReader);
//        printWriter = new PrintWriter(player.getOutputStream());
//        sendMessage("Hello I am server");
//        String lastMessage;
//        while (true){
//            if((lastMessage = bufferedReader.readLine())!= null) {
//                System.out.println(lastMessage);
//            }
//        }
//    }
//    public String receiveMessage() throws IOException {
//        String clientMessage = bufferedReader.readLine();
//        return "client: " +clientMessage;
//    }
//
//    public void sendMessage(String message){
//        printWriter.println(message);
//        printWriter.flush();
    }
}