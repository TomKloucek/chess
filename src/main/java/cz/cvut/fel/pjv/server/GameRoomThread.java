package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameRoomThread implements Runnable{
    private Socket playerWhite;
    private PrintWriter printWriterWhite;
    private InputStreamReader inputStreamReaderWhite;
    private BufferedReader bufferedReaderWhite;

    private String gameState = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";

    private Socket playerBlack;
    private int whitePlayerId;
    private int blackPlayerId;
    private PrintWriter printWriterBlack;
    private InputStreamReader inputStreamReaderBlack;
    private BufferedReader bufferedReaderBlack;

    public GameRoomThread(Socket playerWhite, Socket playerBlack, int whitePlayerId, int blackPlayerId) {
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
            String receivedMessageFromBlack = bufferedReaderBlack.readLine();
            System.out.println(receivedMessageFromBlack);


            while (((receivedMessageFromWhite = bufferedReaderWhite.readLine())!= null) ||
                    ((receivedMessageFromBlack = bufferedReaderBlack.readLine())!= null)){
                if (receivedMessageFromWhite!=null) {
                    System.out.printf(
                            " Sent from the client: %s\n",
                            receivedMessageFromWhite);
                    printWriterWhite.println("Welcome to server white player with ID: " + whitePlayerId);
                    printWriterWhite.flush();
                }
                else {
                    System.out.printf(
                            " Sent from the client: %s\n",
                            receivedMessageFromBlack);
                    printWriterBlack.println("Welcome to server black player with ID: " + blackPlayerId);
                    printWriterBlack.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }






    }
}
