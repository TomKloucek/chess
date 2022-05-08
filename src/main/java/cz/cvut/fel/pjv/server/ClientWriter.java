package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.State;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientWriter implements Runnable{
    private final Socket clientSocket;
    public PrintWriter printWriter;


    public ClientWriter(Socket client, PrintWriter printWriter){
        this.clientSocket = client;
        this.printWriter = printWriter;


    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        String line = null;
        while (!"exit".equalsIgnoreCase(line)) {

            // reading from user
            line = sc.nextLine();

            // sending the user input to server
            printWriter.println(line);
            printWriter.flush();
        }

        // closing the connection
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.log(ClientWriter.class, "run",e.getMessage());
            e.printStackTrace();
        }
    }
}
