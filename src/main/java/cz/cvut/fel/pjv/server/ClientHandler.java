package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.State;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * ClientHandler is class which handles client (online).
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class ClientHandler implements Runnable{
    private final Socket clientSocket;
    public BufferedReader bufferedReader;

    public PrintWriter printWriter;

    /**
     * A constructor of ClientHandler.
     *
     * @param client clients socket color
     * @param bufferedReader serves for reading received messages
     * @param printWriter serves for sending messages
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ClientHandler(Socket client, BufferedReader bufferedReader, PrintWriter printWriter){
        this.clientSocket = client;
        this.bufferedReader = bufferedReader;
        this.printWriter = printWriter;

    }
    /**
     * In case of specific messages does action which is mainly displayed in the view of the game.
     * Such as receiving login and message which informs this player that the other one was disconnected.
     *
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    @Override
    public void run() {
        try {
            String receivedMessage;
            JDialog dialog = openLoadingDialogue();
            while ((receivedMessage = bufferedReader.readLine()) != null){
                if (receivedMessage.equals("White")) {
                    closeLoadingDialogue(dialog);
                    State.getInstance().getGuiRef().openNetworkGame("", Color.WHITE);
                    printWriter.println("White login:" + State.getInstance().getLogin());
                    printWriter.flush();
                }
                else  if (receivedMessage.equals("Black")) {
                    closeLoadingDialogue(dialog);
                    State.getInstance().getGuiRef().openNetworkGame("", Color.BLACK);
                    printWriter.println("Black login:" + State.getInstance().getLogin());
                    printWriter.flush();
                }
                else if (receivedMessage.contains("OneOfPlayersDisconnected")){
                    State.getInstance().getGuiRef().showMainMenu();
                    Color winner = State.getInstance().getGame().getMe().getColor();
                    int win;
                    if (winner == Color.WHITE) {
                        win = 1;
                    }
                    else {
                        win = 2;
                    }
                    printWriter.println(Helpers.gameHistory(State.getInstance().getGame().getMoves(), State.getInstance().getGuiRef().getNameWhite().getText(),State.getInstance().getGuiRef().getNameBlack().getText(),win));
                    printWriter.flush();
                    State.getInstance().getGuiRef().closeGameFrame(true);
                    JOptionPane.showMessageDialog(null,"Bohužel se Váš protihráč odpojil ze hry");
                }
                else if (receivedMessage.contains("White login:")){
                    State.getInstance().getGuiRef().setOpponentLogin(receivedMessage.split(":")[1]);
                }
                else if (receivedMessage.contains("Black login:")){
                    State.getInstance().getGuiRef().setOpponentLogin(receivedMessage.split(":")[1]);
                }
                else {
                    State.getInstance().getGame().updateGame(receivedMessage);
                }

            }
        }
        catch(IOException e){
            Logger.log(ClientHandler.class,"run","Nastal problem pri prijimani informaci od serveru");
            e.printStackTrace();
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.log(ClientHandler.class, "run","Nastala chyba pri uzavirani socketu");
            e.printStackTrace();
        }
    }

    /**
     * This method opens a dialogue which shows waiting screen. Because player has to wait until the second player connects.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public JDialog openLoadingDialogue() {
        JDialog dialog = new JDialog();
        dialog.setLayout(new BorderLayout());
        JLabel label = new JLabel( new ImageIcon("resources/loading.gif") );
        dialog.add(label,BorderLayout.CENTER);
        JLabel text = new JLabel(("Čekejte, hledá se hra"));
        text.setBackground(java.awt.Color.WHITE);
        text.setOpaque(true);
        text.setBorder(new CompoundBorder(text.getBorder(), new EmptyBorder(15,120,5,0)));
        text.setFont(new Font("Roboto", Font.PLAIN, 20));
        dialog.add(text,BorderLayout.NORTH);
        dialog.pack();
        dialog.setDefaultCloseOperation(0);
        dialog.setVisible(true);
        return dialog;
    }

    public void closeLoadingDialogue(JDialog dialog) {
        dialog.dispose();
    }


}
