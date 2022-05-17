package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;

public class TwoClientsHandler implements Runnable {
    private final Socket playerWhite;
    private PrintWriter printWriterWhite;
    private InputStreamReader inputStreamReaderWhite;
    private BufferedReader bufferedReaderWhite;

    private final Socket playerBlack;
    private PrintWriter printWriterBlack;
    private InputStreamReader inputStreamReaderBlack;
    private BufferedReader bufferedReaderBlack;

    private boolean listenWhite = true;
    private boolean listenForLogin = true;

    private boolean goCycle = true;

    private boolean whiteDisconnected = false;
    private boolean blackDisconnected = false;

    public TwoClientsHandler(Socket playerWhite, Socket playerBlack) {
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
    }

    @Override
    public void run() {
        try {
            printWriterWhite = new PrintWriter(playerWhite.getOutputStream());
            inputStreamReaderWhite = new InputStreamReader(playerWhite.getInputStream());
            bufferedReaderWhite = new BufferedReader(inputStreamReaderWhite);

            printWriterBlack = new PrintWriter(playerBlack.getOutputStream());
            inputStreamReaderBlack = new InputStreamReader(playerBlack.getInputStream());
            bufferedReaderBlack = new BufferedReader(inputStreamReaderBlack);

            printWriterWhite.println("White");
            printWriterWhite.flush();
            printWriterBlack.println("Black");
            printWriterBlack.flush();

            String receivedMessageFromWhite = null;
            String receivedMessageFromBlack = null;
            int firstChar;
            String  firstLetter = null;
            while (goCycle) {
                if (listenWhite == true) {
                    try{
                        firstChar = inputStreamReaderWhite.read();
                        if(firstChar != -1){
                            firstLetter = Character.toString(firstChar);
                        }
                        else {
                            whiteDisconnected = true;
                            printWriterBlack.println("OneOfPlayersDisconnected");
                            printWriterBlack.flush();
                        }
                    }
                    catch (SocketException e){
                        whiteDisconnected = true;
                        printWriterBlack.println("OneOfPlayersDisconnected");
                        printWriterBlack.flush();

                    }
                    while (!bufferedReaderWhite.ready() && bufferedReaderBlack.ready() && !whiteDisconnected && !blackDisconnected) {
                        receivedMessageFromBlack = bufferedReaderBlack.readLine();
                        System.out.printf(
                                "Black sent message but not allowed: %s\n", receivedMessageFromBlack);
                        printWriterBlack.println("You have to wait for white");
                        printWriterBlack.flush();
                        break;
                    }
                    if (bufferedReaderWhite.ready()) {

                        while ((receivedMessageFromWhite = bufferedReaderWhite.readLine()) != null) {
                            if(receivedMessageFromWhite.contains("login")){
                                printWriterBlack.println("W"+receivedMessageFromWhite);
                                printWriterBlack.flush();
                                receivedMessageFromBlack = bufferedReaderBlack.readLine();
                                if (receivedMessageFromBlack.contains("login")){
                                    printWriterWhite.println("B"+receivedMessageFromBlack);
                                    printWriterWhite.flush();
                                }
                            }
                            else {
                                if(listenForLogin == true && !whiteDisconnected && !blackDisconnected){
                                    System.out.printf(
                                            "Sent from the White client: %s\n",
                                            receivedMessageFromWhite);
                                    printWriterBlack.println(receivedMessageFromWhite);
                                    printWriterBlack.flush();
                                    listenWhite = false;
                                    listenForLogin = false;
                                    break;
                                }
                                else {
                                    System.out.printf(
                                            "Sent from the White client: %s\n",
                                            firstLetter+receivedMessageFromWhite);
                                    if(receivedMessageFromWhite.contains("$")){
                                        this.saveGameToFile(firstLetter+receivedMessageFromWhite.strip());
                                        goCycle = false;
                                        break;
                                    }
                                    printWriterBlack.println(firstLetter + receivedMessageFromWhite);
                                    printWriterBlack.flush();
                                    listenWhite = false;
                                    break;
                                }

                            }
                        }
                    }
                } else if (listenWhite == false) {
                    try{
                        firstChar = inputStreamReaderBlack.read();
                        if(firstChar != -1){
                            firstLetter = Character.toString(firstChar);
                        }
                        else {
                            blackDisconnected = true;
                            printWriterWhite.println("OneOfPlayersDisconnected");
                            printWriterWhite.flush();
                        }
                    }
                    catch (SocketException e){
                        blackDisconnected = true;
                        printWriterWhite.println("OneOfPlayersDisconnected");
                        printWriterWhite.flush();
                    }
                    while (!bufferedReaderBlack.ready() && bufferedReaderWhite.ready() && !whiteDisconnected && !blackDisconnected) {
                        receivedMessageFromWhite = bufferedReaderWhite.readLine();
                        System.out.printf(
                                "White sent a message but not allowed %s\n", receivedMessageFromWhite);
                        printWriterWhite.println("You have to wait for Black");
                        printWriterWhite.flush();
                        break;
                    }
                    if(bufferedReaderBlack.ready() && !blackDisconnected && !whiteDisconnected) {
                        while ((receivedMessageFromBlack = bufferedReaderBlack.readLine()) != null) {

                                System.out.printf(
                                        " Sent from the Black client: %s\n",
                                        firstLetter+receivedMessageFromBlack);
                                if(receivedMessageFromBlack.contains("$")){
                                    this.saveGameToFile(firstLetter + receivedMessageFromBlack.strip());
                                    goCycle = false;
                                    break;
                                }
                                printWriterWhite.println(firstLetter+receivedMessageFromBlack);
                                printWriterWhite.flush();
                                listenWhite = true;
                                break;
                        }
                    }
                }
                if (blackDisconnected || whiteDisconnected){
                    if(blackDisconnected){
                        String message = bufferedReaderWhite.readLine();
                        this.saveGameToFile(message);
                    }
                    else {
                        String message = bufferedReaderBlack.readLine();
                        this.saveGameToFile(message);
                    }
                    blackDisconnected = false;
                    whiteDisconnected = false;
                    goCycle = false;
                }
            }
        }
        catch(IOException e){
            Logger.log(TwoClientsHandler.class, "run",e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveGameToFile(String game) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("games.txt", true));
        if (game != null) {
            bw.write(game);
            bw.newLine();
            bw.close();
        }
    }
}
