package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener implements Runnable{
    private final Socket clientSocket;
    public PrintWriter printWriter;
    public BufferedReader bufferedReader;

    public ClientListener(Socket client, PrintWriter printWriter, BufferedReader bufferedReader){
        this.clientSocket = client;
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;

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

            // displaying server reply
            try {
                System.out.println("Server replied "
                        + bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // closing the connection
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
