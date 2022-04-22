package cz.cvut.fel.pjv.view;// Java program to illustrate the BorderLayout
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Game;
import cz.cvut.fel.pjv.models.Player;
import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.server.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

// class extends JFrame
public class MainMenu extends JFrame {
    JFrame frame;

    public MainMenu() {
        frame = new JFrame();
        BorderLayout borderLayout = new BorderLayout();
        FlowLayout flowLayoutCenter = new FlowLayout(FlowLayout.CENTER);
        frame.setLayout(borderLayout);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel heading = new JLabel("Šachy");
        heading.setFont(new Font("Serif", Font.PLAIN, 40));
        heading.setForeground(Color.white);

        JPanel northPanel = new JPanel(flowLayoutCenter);
        northPanel.setBackground(Color.black);
        northPanel.add(heading);


        GridLayout gridLayout = new GridLayout(2, 2);
        JPanel middlePanel = new JPanel(gridLayout);

        JButton button0 = new JButton();
        button0.setBackground(Color.black);
        button0.setForeground(Color.white);
        button0.setText("Hra s počítačem");
        button0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showGameDialogue();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton button1 = new JButton();
        button1.setBackground(Color.white);
        button1.setForeground(Color.black);
        button1.setText("Hra po síti");
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton button2 = new JButton();
        button2.setBackground(Color.white);
        button2.setForeground(Color.black);
        button2.setText("Editor");
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton button3 = new JButton();
        button3.setBackground(Color.black);
        button3.setForeground(Color.white);
        button3.setText("Nastavení");
        button3.setCursor(new Cursor(Cursor.HAND_CURSOR));

        middlePanel.add(button0);
        middlePanel.add(button1);
        middlePanel.add(button2);
        middlePanel.add(button3);


        JLabel foot = new JLabel("\u00A9 babycvla & kloucto2");
        foot.setForeground(Color.black);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.setBackground(Color.white);
        southPanel.add(foot);

        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);

        frame.setSize(new Dimension(640, 640));

        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void showGameDialogue() throws IOException {
        ImageIcon loading = new ImageIcon("loading.gif");
        frame.add(new JLabel("loading... ", loading, JLabel.CENTER));

        Board board = new Board();

        Player p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
        Player p2 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
        Game game = new Game(p1, p2, board);


        Client client = new Client();
        client.connectToServer();
        client.listenFromServer();

        cz.cvut.fel.pjv.models.Color color = cz.cvut.fel.pjv.models.Color.WHITE;

        if (color == cz.cvut.fel.pjv.models.Color.WHITE) {
            game.setMe(p1);
        }
        else {
            game.setMe(p2);
        }

        game.setClient(client);

        State.getInstance();
        State.getInstance().setGame(game);

        board.initializeBoard();
        BoardView mainPanel = new BoardView(board);

        System.out.println(game.boardToString());

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(800, 679));
//            frame.setMaximumSize(new Dimension(800, 540));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}


