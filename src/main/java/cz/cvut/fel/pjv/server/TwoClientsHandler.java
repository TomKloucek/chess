package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

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
                        System.out.println((char)firstChar);
                        if(firstChar != -1){
                            firstLetter = Character.toString(firstChar);
                        }
                        else {
                            whiteDisconnected = true;
                            System.out.println("Bily odpojen");
                            printWriterBlack.println("OneOfPlayersDisconnected");
                            printWriterBlack.flush();
                        }
                    }
                    catch (SocketException e){
                        whiteDisconnected = true;
                        System.out.println("Bily odpojen");
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
                                if(listenForLogin == true){
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
                            System.out.println("Cerny odpojen");
                            printWriterWhite.println("OneOfPlayersDisconnected");
                            printWriterWhite.flush();
                        }
                    }
                    catch (SocketException e){
                        blackDisconnected = true;
                        System.out.println("Cerny odpojen");
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
                    if(bufferedReaderBlack.ready()) {
                        while ((receivedMessageFromBlack = bufferedReaderBlack.readLine()) != null) {

                                System.out.printf(
                                        " Sent from the Black client: %s\n",
                                        firstLetter+receivedMessageFromBlack);
                                printWriterWhite.println(firstLetter+receivedMessageFromBlack);
                                printWriterWhite.flush();
                                listenWhite = true;
                                break;
                        }
                    }

                }
                if (blackDisconnected || whiteDisconnected){
                    if(blackDisconnected){
                        System.out.println(bufferedReaderWhite.readLine());
                    }
                    else if (whiteDisconnected) {
                        System.out.println(bufferedReaderBlack.readLine());
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
}
