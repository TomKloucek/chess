package cz.cvut.fel.pjv.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    private static final int TIMEOUT_1M = 6000;
    private Socket clientSocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private BufferedReader systemIn = null;

    public void connectToServer() {
        try {
            this.clientSocket = new Socket("127.0.1.1", 7165);
        }
        catch (UnknownHostException ex) {
            // Nepodarilo se najit (DNS, NIS atp.) hostitele
            System.exit(-1);
        }
        catch (IOException ex) {
            // Nepodarilo se spojit s hostitelem
            System.exit(-1);
        }
        // parametry spojeni - vyprseni (pri cteni ze socketu)
        try {
            clientSocket.setSoTimeout(TIMEOUT_1M);
        } catch (SocketException ex) {
            // Nepodarilo se nastavit timeout
        }
        // otevreni proudu, spojeni
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.systemIn = new BufferedReader(new InputStreamReader(System.in));
        }
        catch (IOException ex) {
            System.exit(-1);
        }
    }

    public void sendToServer(String message) {
        out.println(message);
    }

    public String readFromServer() throws IOException {
        return in.readLine();
    }

    public void endConnection() throws IOException {
        in.close();
        out.close();
        systemIn.close();
        clientSocket.close();
    }
}
