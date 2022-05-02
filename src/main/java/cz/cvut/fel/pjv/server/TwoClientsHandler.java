package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

            printWriterWhite.println("Welcome to server White");
            printWriterWhite.flush();
            printWriterBlack.println("Welcome to server Black");
            printWriterBlack.flush();

            String receivedMessageFromWhite = null;
            String receivedMessageFromBlack = null;
            while (true) {
                if (listenWhite == true) {
                    while (!bufferedReaderWhite.ready() && bufferedReaderBlack.ready()) {
                        receivedMessageFromBlack = bufferedReaderBlack.readLine();
                        System.out.printf(
                                "Black sent message but not allowed: %s\n", receivedMessageFromBlack);
                        printWriterBlack.println("You have to wait for white");
                        printWriterBlack.flush();
                        break;
                    }
                    if (bufferedReaderWhite.ready()) {
                        while ((receivedMessageFromWhite = bufferedReaderWhite.readLine()) != null) {
                            System.out.printf(
                                    "Sent from the White client: %s\n",
                                    receivedMessageFromWhite);
                            printWriterBlack.println(receivedMessageFromWhite);
                            printWriterBlack.flush();
                            listenWhite = false;
                            break;
                        }
                    }
                } else if (listenWhite == false) {
                    while (!bufferedReaderBlack.ready() && bufferedReaderWhite.ready()) {
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
                                    receivedMessageFromBlack);
                            printWriterWhite.println(receivedMessageFromBlack);
                            printWriterWhite.flush();
                            listenWhite = true;
                            break;
                        }
                    }
                }
//                while ((receivedMessageFromWhite = bufferedReaderWhite.readLine()) != null
//                ||(receivedMessageFromBlack = bufferedReaderBlack.readLine()) != null) {
//                    receivedMessageFromBlack = bufferedReaderBlack.readLine();
//
//
//                        System.out.printf(
//                                " Sent from the White client: %s\n",
//                                receivedMessageFromWhite);
//                        printWriterWhite.println("Welcome to server White");
//                        printWriterWhite.flush();
//
//
//
//                        System.out.printf(
//                                " Sent from the Black client: %s\n",
//                                receivedMessageFromBlack);
//                        printWriterBlack.println("Welcome to server Black");
//                        printWriterBlack.flush();
//
//                }

            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
