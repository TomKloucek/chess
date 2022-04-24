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

            String receivedMessageFromWhite;
            String receivedMessageFromBlack;

            while (true) {
                if (listenWhite == true) {

                    while ((receivedMessageFromWhite = bufferedReaderWhite.readLine()) != null) {
                        System.out.printf(
                                " Sent from the White client: %s\n",
                                receivedMessageFromWhite);
//                        printWriterWhite.println("Welcome to server White");
                        printWriterBlack.println(receivedMessageFromWhite);
                        printWriterBlack.flush();
                        listenWhite = false;
                        break;
                    }
                } else if (listenWhite == false) {
                    while ((receivedMessageFromBlack = bufferedReaderBlack.readLine()) != null) {

                        System.out.printf(
                                " Sent from the Black client: %s\n",
                                receivedMessageFromBlack);
//                        printWriterBlack.println("Welcome to server Black");
                        printWriterWhite.println(receivedMessageFromBlack);
                        printWriterWhite.flush();
                        listenWhite = true;
                        break;

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
