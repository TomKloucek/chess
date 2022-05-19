package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.server.ClientHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client is one of the main classes of the whole project. It has all information about the client.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Client {
    public PrintWriter printWriter;
    public InputStreamReader inputStreamReader;
    public BufferedReader bufferedReader;
    public ClientHandler clientListener;
    Socket player;


    /**
     * This method connects client socket to server.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void connectToServer() throws IOException {
        player = new Socket("localhost", 4999);
        printWriter = new PrintWriter(player.getOutputStream());
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        setClientHandler();
    }


    /**
     * This method gets statistics of player with the given username from server.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String getPlayerStatistics(String username) throws IOException {
        player = new Socket("localhost", 5001);
        printWriter = new PrintWriter(player.getOutputStream());
        printWriter.println(username);
        printWriter.flush();
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

    /**
     * This method gets game history from server.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String getGamesHistory() throws IOException {
        player = new Socket("localhost", 5000);
        inputStreamReader = new InputStreamReader(player.getInputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.readLine();
    }

    /**
     * This method sets client handler.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void setClientHandler(){
        clientListener = new ClientHandler(player, bufferedReader, printWriter);
        new Thread(clientListener).start();
    }

    /**
     * This method disconnects client from server.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void disconnectFromServer() throws IOException {
        player.close();
    }
}
