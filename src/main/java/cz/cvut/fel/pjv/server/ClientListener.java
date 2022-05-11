package cz.cvut.fel.pjv.server;

import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.State;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientListener implements Runnable{
    private final Socket clientSocket;
    public BufferedReader bufferedReader;

    public ClientListener(Socket client, BufferedReader bufferedReader){
        this.clientSocket = client;
        this.bufferedReader = bufferedReader;

    }

    @Override
    public void run() {
        try {
            String receivedMessage;
            JDialog dialog = openLoadingDialogue();
            while ((receivedMessage = bufferedReader.readLine()) != null){
                if (receivedMessage.equals("White")) {
                    closeLoadingDialogue(dialog);
                    State.getInstance().getGuiRef().openNetworkGame("", Color.WHITE);
                }
                else  if (receivedMessage.equals("Black")) {
                    closeLoadingDialogue(dialog);
                    State.getInstance().getGuiRef().openNetworkGame("", Color.BLACK);
               // } else if (receivedMessage.contains("login")) {
                 //   State.getInstance().getGuiRef().setOpponentLogin(receivedMessage.split(":")[1]);
                }
                else if (receivedMessage.equals("OneOfPlayersDisconnected")){
                    System.out.println("Ano dostal jsem");
                    State.getInstance().getGuiRef().showMainMenu();
                    State.getInstance().getGuiRef().closeGameFrame();
                    JOptionPane.showMessageDialog(null,"Bohužel se Váš protihráč odpojil ze hry");
                }
                else {
                    System.out.println(receivedMessage);
                    State.getInstance().getGame().updateGame(receivedMessage);
                }
            }
        }
        catch(IOException e){
            Logger.log(ClientListener.class,"run","Nastal problem pri prijimani informaci od serveru");
            e.printStackTrace();
        }

        // closing the connection
        try {
            clientSocket.close();
        } catch (IOException e) {
            Logger.log(ClientListener.class, "run","Nastala chyba pri uzavirani socketu");
            e.printStackTrace();
        }
    }

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
