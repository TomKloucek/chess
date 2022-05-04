package cz.cvut.fel.pjv.view;// Java program to illustrate the BorderLayout
import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.server.Client;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

// class extends JFrame
public class MainMenu extends JFrame {
    JFrame frame;

    private BoardView bw;

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
        button0.setText("Hra po síti");
        button0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = new Client();
                    State.getInstance().setClient(client);
                    MainMenu.this.hideMainMenu();
                    client.connectToServer();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton button1 = new JButton();
        button1.setBackground(Color.white);
        button1.setForeground(Color.black);
        button1.setText("Hra s počítačem");
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAIGame("", -1);
            }
        });

        JButton button2 = new JButton();
        button2.setBackground(Color.white);
        button2.setForeground(Color.black);
        button2.setText("PvP");
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPvPGame("");
            }
        });

        JPanel etcPanel = new JPanel(gridLayout);

        JButton topten = new JButton();
        topten.setBackground(Color.black);
        topten.setForeground(Color.white);
        topten.setText("TOP 10 her");
        topten.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Zobrazi se 10 her");
            }
        });
        JButton editor = new JButton();
        editor.setBackground(Color.white);
        editor.setForeground(Color.black);
        editor.setText("Editor");
        editor.setCursor(new Cursor(Cursor.HAND_CURSOR));
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditor("");
            }
        });
        JButton loadGame = new JButton();
        loadGame.setBackground(Color.white);
        loadGame.setForeground(Color.black);
        loadGame.setText("Nacti editor ze souboru");
        loadGame.setFont(new Font("Roboto", Font.PLAIN, 10));
        loadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openEditor(Helpers.importBoard());
            }
        });
        JButton settings = new JButton();
        settings.setBackground(Color.black);
        settings.setForeground(Color.white);
        settings.setText("Nastaveni");
        settings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Nastaveni");
            }
        });

        etcPanel.add(topten);
        etcPanel.add(editor);
        etcPanel.add(loadGame);
        etcPanel.add(settings);

        middlePanel.add(button0);
        middlePanel.add(button1);
        middlePanel.add(button2);
        middlePanel.add(etcPanel);


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
        State.getInstance().setGuiRef(this);
    }
    public void hideMainMenu(){
        frame.setVisible(false);
    }
    public void showMainMenu(){
        frame.setVisible(true);
    }

    public void openNetworkGame(String boardString, cz.cvut.fel.pjv.models.Color color) throws IOException {
        if (!State.getInstance().isWhiteOnMove()) {
            State.getInstance().resetMove();
        }
        Board board = new Board(GameType.SERVER);

        board.initializeBoard();

        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }

        Player p1;
        Player p2;
        Game game = null;

        if (color == cz.cvut.fel.pjv.models.Color.WHITE) {
            p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
            p2 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
            game = new Game(p1, p2, board);
        }
        else {
            p1 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
            p2 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
            game = new Game(p2, p1, board);
        }
        game.setMe(p1);

        State.getInstance();
        State.getInstance().setGame(game);

        BoardView mainPanel = new BoardView(board);
        this.bw = mainPanel;

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                MainMenu.this.showMainMenu();
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(930, 679));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void openPvPGame(String boardString) {
        if (!State.getInstance().isWhiteOnMove()) {
            State.getInstance().resetMove();
        }
        Board board = new Board(GameType.PVP);

        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }
        else {
            board.initializeBoard();
        }

        hideMainMenu();

        // COLOR Pick
        int random = Helpers.randomNumber(0,100);

        Player p1;
        Player p2;
        Game game = null;

        if (random % 2 == 0) {
            p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
            p2 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
            game = new Game(p1, p2, board);
        }
        else {
            p1 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
            p2 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
            game = new Game(p2, p1, board);
        }

        game.setMe(p1);

        State.getInstance();
        State.getInstance().setGame(game);
        JLabel nameWhite = null;
        JLabel nameBlack = null;
        nameWhite = new JLabel("Hrac 1");
        nameBlack = new JLabel("Hrac 2");

        BoardView mainPanel = new BoardView(board);
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(760, 679));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel whitePlayerPanel = new JPanel(new BorderLayout());
        JLabel timeWhite = new JLabel("Cas 1");
        Border border = timeWhite.getBorder();
        Border margin = new EmptyBorder(30,30,30,30);
        timeWhite.setBorder(new CompoundBorder(border, margin));
        timeWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setBorder(new CompoundBorder(border, margin));
        whitePlayerPanel.add(nameWhite,BorderLayout.NORTH);
        whitePlayerPanel.add(timeWhite,BorderLayout.SOUTH);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout());
        JLabel timeBlack = new JLabel("Cas 2");
        border = timeBlack.getBorder();
        margin = new EmptyBorder(30,30,30,30);
        timeBlack.setBorder(new CompoundBorder(border, margin));
        timeBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setBorder(new CompoundBorder(border, margin));
        blackPlayerPanel.add(nameBlack,BorderLayout.NORTH);
        blackPlayerPanel.add(timeBlack,BorderLayout.SOUTH);

        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            gamePanel.add(whitePlayerPanel,BorderLayout.SOUTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.NORTH);
        }
        else {
            gamePanel.add(whitePlayerPanel,BorderLayout.NORTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.SOUTH);
        }
        gamePanel.add(new Button("OK, lets go"), BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.EAST);
    }

    public void openAIGame(String boardString, int answer) {
        if (!State.getInstance().isWhiteOnMove()) {
            State.getInstance().resetMove();
        }
        Board board = new Board(GameType.PVE);


        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }
        else {
            board.initializeBoard();
        }

        hideMainMenu();

        // COLOR Pick
        int random = Helpers.randomNumber(0,100);

        Player p1;
        AiPlayer p2;
        Game game = null;

        if (answer != -1) {
            if (answer == 0) {
                p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
                p2 = new AiPlayer(cz.cvut.fel.pjv.models.Color.BLACK, null, board);
                game = new Game(p1, p2, board);
            }
            else {
                p1 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
                p2 = new AiPlayer(cz.cvut.fel.pjv.models.Color.WHITE, null, board);
                game = new Game(p2, p1, board);
            }
        }
        else {
            if (random % 2 == 0) {
                p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
                p2 = new AiPlayer(cz.cvut.fel.pjv.models.Color.BLACK, null, board);
                game = new Game(p1, p2, board);
            } else {
                p1 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
                p2 = new AiPlayer(cz.cvut.fel.pjv.models.Color.WHITE, null, board);
                game = new Game(p2, p1, board);
            }
        }

        game.setMe(p1);

        State.getInstance();
        State.getInstance().setGame(game);
        JLabel nameWhite = null;
        JLabel nameBlack = null;
        if (p2.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            nameWhite = new JLabel("AI");
            nameBlack = new JLabel("Hrac");
            game.playForAi();
        }
        else {
            nameBlack = new JLabel("AI");
            nameWhite = new JLabel("Hrac");
        }

        BoardView mainPanel = new BoardView(board);
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(760, 679));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel whitePlayerPanel = new JPanel(new BorderLayout());
        JLabel timeWhite = new JLabel("Cas 1");
        Border border = timeWhite.getBorder();
        Border margin = new EmptyBorder(30,30,30,30);
        timeWhite.setBorder(new CompoundBorder(border, margin));
        timeWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setBorder(new CompoundBorder(border, margin));
        whitePlayerPanel.add(nameWhite,BorderLayout.NORTH);
        whitePlayerPanel.add(timeWhite,BorderLayout.SOUTH);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout());
        JLabel timeBlack = new JLabel("Cas 2");
        border = timeBlack.getBorder();
        margin = new EmptyBorder(30,30,30,30);
        timeBlack.setBorder(new CompoundBorder(border, margin));
        timeBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setBorder(new CompoundBorder(border, margin));
        blackPlayerPanel.add(nameBlack,BorderLayout.NORTH);
        blackPlayerPanel.add(timeBlack,BorderLayout.SOUTH);

        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            gamePanel.add(whitePlayerPanel,BorderLayout.SOUTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.NORTH);
        }
        else {
            gamePanel.add(whitePlayerPanel,BorderLayout.NORTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.SOUTH);
        }
        gamePanel.add(new Button("OK, lets go"), BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.EAST);
    }

    public void openEditor(String boardString) {
        Board board = new Board(GameType.SERVER);
        board.initializeEditor();

        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }

        hideMainMenu();

        BoardView mainPanel = new BoardView(board);
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                State.getInstance().resetMove();
                showMainMenu();
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(930, 679));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());
        JButton playButton = new JButton("Hraj proti AI");
        playButton.setSize(30,1);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.getKing(cz.cvut.fel.pjv.models.Color.WHITE) != null && board.getKing(cz.cvut.fel.pjv.models.Color.BLACK) != null) {
                    String[] options_color = {"White","Black"};
                    int answer = JOptionPane.showOptionDialog(null, "Výběr barvy",
                            "Za jakou barvu si přejete hrát?",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_color, options_color[0]);
                    openAIGame(board.boardToString(), answer);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    hideMainMenu();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nemůžete spustit hru bez dvou králů");
                }
            }
        });

        JButton pvpButton = new JButton("Hraj proti hraci");
        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.getKing(cz.cvut.fel.pjv.models.Color.WHITE) != null && board.getKing(cz.cvut.fel.pjv.models.Color.BLACK) != null) {
                    openPvPGame(board.boardToString());
                    hideMainMenu();
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    hideMainMenu();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nemůžete spustit hru bez dvou králů");
                }
            }
        });

        JButton exportButton = new JButton("Exportuj sachovnici pro pozdejsi nacteni");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Helpers.exportBoard(board.boardToString());
            }
        });

        gamePanel.add(playButton, BorderLayout.CENTER);
        gamePanel.add(pvpButton, BorderLayout.NORTH);
        gamePanel.add(exportButton, BorderLayout.SOUTH);
        frame.add(gamePanel, BorderLayout.EAST);
    }

    public BoardView getBw() {
        return bw;
    }
}


