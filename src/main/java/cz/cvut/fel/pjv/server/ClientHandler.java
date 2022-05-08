package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.Main;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.view.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private PrintWriter printWriter;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;



    public ClientHandler(Socket socket)
    {
        this.clientSocket = socket;
    }



    @Override
    public void run() {

    try {
        printWriter = new PrintWriter(clientSocket.getOutputStream());
        inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);

        String receivedMessage;
        while ((receivedMessage = bufferedReader.readLine())!= null){
            System.out.printf(
                    " Sent from the client: %s\n",
                    receivedMessage);
            printWriter.println("Welcome to server");
            printWriter.flush();
        }
    } catch (IOException e) {
        Logger.log(ClientHandler.class, "run","Pri behu klienta nastala chyba");
        e.printStackTrace();
    }
    }

}
