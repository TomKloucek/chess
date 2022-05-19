package cz.cvut.fel.pjv.view;// Java program to illustrate the BorderLayout
import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.Client;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * MainMenu is the main class and component of the application. It serves as the main menu of the application.
 * It creates buttons for every client interaction possible and creates listeners with corresponding actions afterwards.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class MainMenu extends JFrame {
    JFrame frame;

    JFrame  settingsFrame = null;

    JFrame game = null;

    private JLabel nameWhite;
    private JLabel nameBlack;

    private Player p1;
    private Player p2;

    private BoardView bw;

    private JTextArea notation;

    /**
     * <p>
     * This method creates the whole application for the client. Creates the GUI.
     * </p>
     */
    public MainMenu() {
        // Set up the look of the application to look on every device the same as we intended.
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            // If user already were in the application and set up already his preferences on pieces set or color of board.
            // This information was read from client.txt file on client's device
            BoardView.readColorsAndFigures();
        } catch (Exception e) {
            Logger.log(MainMenu.class, "Constructor", e.getMessage());
            try {
                Helpers.writeColorsAndFigures();
            } catch (Exception ex) {
                Logger.log(MainMenu.class, "Constructor", ex.getMessage());
            }
        }
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

        // Setting up the first button for playing chess online
        JButton button0 = new JButton();
        button0.setBackground(Color.black);
        button0.setForeground(Color.white);
        button0.setText("Hra po síti");
        button0.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // New client is created, this menu is hidden and client is connected to the server
                    Client client = new Client();
                    MainMenu.this.hideMainMenu();
                    State.getInstance().setClient(client);
                    if (State.getInstance().getLogin() == null) {
                        State.getInstance().setLogin();
                    }
                    client.connectToServer();
                } catch (IOException ex) {
                    // In case if the server is not running, then the user is returned to main menu with dialog about server inability
                    Logger.log(MainMenu.class, "Constructor",ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Bohužel jsme nenašli žádný dostupný server, zkuste to prosím později");
                    showMainMenu();
                }
            }
        });

        // Setting up the first button for playing chess against our artificial intelligence.
        JButton button1 = new JButton();
        button1.setBackground(Color.white);
        button1.setForeground(Color.black);
        button1.setText("Hra s počítačem");
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAIGame("", -1, false);
            }
        });

        // Setting up the first button for playing locally versus other player on the same pc
        JButton button2 = new JButton();
        button2.setBackground(Color.white);
        button2.setForeground(Color.black);
        button2.setText("PvP");
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPvPGame("", false);
            }
        });

        JPanel etcPanel = new JPanel(gridLayout);

        // Button shows user dialog where he can decide if he wants to see his statistics or see every game played on the server.
        JButton topten = new JButton();
        topten.setBackground(Color.black);
        topten.setForeground(Color.white);
        topten.setText("Statistiky");
        topten.setCursor(new Cursor(Cursor.HAND_CURSOR));
        topten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Client client = new Client();
                    // User is presented with option dialog
                    String[] options_color = {"Osobní statistika","Všechny hry"};
                    int answer = JOptionPane.showOptionDialog(null, "Jakou statistiku si přejete zobrazit?",
                            "Jaké statistiky?",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_color, options_color[0]);
                    if (answer == 0) {
                        // Checking if user is already logged
                        if (State.getInstance().getLogin() == null) {
                            State.getInstance().setLogin();
                        }
                        MainMenu.this.openPlayerProfile(client.getPlayerStatistics(State.getInstance().getLogin()));
                    }
                    else {
                        MainMenu.this.openLastPlayedGames(client.getGamesHistory());
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.log(MainMenu.class, "Constructor",ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Bohužel jsme nenašli žádný dostupný server, zkuste to prosím později");
                    showMainMenu();
                }
            }
        });

        // Setting up the button for chess editor
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
        // Setting up the button for importing game from file.
        JButton loadGame = new JButton();
        loadGame.setBackground(Color.white);
        loadGame.setForeground(Color.black);
        loadGame.setText("Nacti editor ze souboru");
        loadGame.setFont(new Font("Roboto", Font.PLAIN, 10));
        loadGame.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openEditor(Helpers.importBoard());
                } catch (FileNotFoundException ex) {
                    Logger.log(MainMenu.class, "Constructor",ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Bohužel se nám nepodařilo hru načíst");
                }
            }
        });
        // Setting up the button for showing user the settings.
        JButton settings = new JButton();
        settings.setBackground(Color.black);
        settings.setForeground(Color.white);
        settings.setText("Nastaveni");
        settings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // If settingsFrame was not already created here it is created.
                if (settingsFrame == null) {
                    setUpSettings();
                }
                hideMainMenu();
                openSettings();
            }
        });

        // Buttons are added in to the frame
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
        // State gets the reference for gui
        State.getInstance().setGuiRef(this);
    }

    /**
     * <p>
     * This method hides the main menu from user.
     * </p>
     */
    public void hideMainMenu(){
        frame.setVisible(false);
    }

    /**
     * <p>
     * This method shows the main menu to the user.
     * </p>
     */
    public void showMainMenu(){
        frame.setVisible(true);
    }


    /**
     * <p>
     * This method creates the gui for network game.
     * </p>
     * @param boardString the string from which the players board can be created
     * @param color the color which the player will be
     */
    public void openNetworkGame(String boardString, cz.cvut.fel.pjv.models.Color color) throws IOException {
        resetState();

        Board board = new Board(GameType.SERVER);
        board.initializeBoard();

        // If board string is not empty then the board is initialized from the string
        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }

        Game game = null;

        // Player object is created depending on the color which is in params. Game object is created with both player objects and the board object previously created
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


        // State is provided with the players game
        State.getInstance().setGame(game);

        // State is provided how long the game will take. Online games are permanently set to 10 minutes.
        State.getInstance().setTimeLeftWhite(600);
        State.getInstance().setTimeLeftBlack(600);

        BoardView mainPanel = new BoardView(board);
        this.bw = mainPanel;

        JFrame frame = new JFrame("Chess");
        this.game = frame;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(820, 720));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        // Setting the login for the user, which he has already written up in the login form.
        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            nameWhite = new JLabel(State.getInstance().getLogin());
            nameBlack = new JLabel("Hrac 2");
        }
        else {
            nameWhite = new JLabel("Hrac 2");
            nameBlack = new JLabel(State.getInstance().getLogin());
        }

        JPanel whitePlayerPanel = new JPanel(new BorderLayout());

        // Setting the times for white player.
        long startMinutesLeftWhite = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
        long startSecondsLeftWhite = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
        JLabel timeWhite = new JLabel(Helpers.formatTime(startMinutesLeftWhite,startSecondsLeftWhite));

        Border border = timeWhite.getBorder();
        Border margin = new EmptyBorder(30,30,30,30);
        timeWhite.setBorder(new CompoundBorder(border, margin));
        timeWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setBorder(new CompoundBorder(border, margin));
        whitePlayerPanel.add(nameWhite,BorderLayout.NORTH);
        whitePlayerPanel.add(timeWhite,BorderLayout.SOUTH);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout());
        // Setting the times for the black player
        long startMinutesLeftBlack = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
        long startSecondsLeftBlack = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
        JLabel timeBlack = new JLabel(Helpers.formatTime(startMinutesLeftBlack,startSecondsLeftBlack));

        border = timeBlack.getBorder();
        margin = new EmptyBorder(30,30,30,30);
        timeBlack.setBorder(new CompoundBorder(border, margin));
        timeBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setBorder(new CompoundBorder(border, margin));
        blackPlayerPanel.add(nameBlack,BorderLayout.NORTH);
        blackPlayerPanel.add(timeBlack,BorderLayout.SOUTH);

        int delay = 500; //milliseconds
        // Listener on time changes
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(State.getInstance().isWhiteOnMove()) {
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
                    // Check if white player have more time than zero seconds, otherwise he loses
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeWhite.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, "Černý vyhrál, bílemu došel čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(true);
                            showMainMenu();
                        }
                    }
                    // White time is updated with the new value.
                    timeWhite.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
                else {
                    // The same for the black player.
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeBlack.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, "Bílý vyhrál, černému došel čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(true);
                            showMainMenu();
                        }
                    }
                    timeBlack.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();


        // User closes the window he is disconnected from the server, he loses the game and main menu is shown to him.
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    State.getInstance().getClient().disconnectFromServer();
                } catch (IOException ex) {
                    Logger.log(MainMenu.class,"Unable to disconnect client from server", ex.getMessage());
                }
                timer.stop();
                MainMenu.this.showMainMenu();
            }
        });

        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            gamePanel.add(whitePlayerPanel,BorderLayout.SOUTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.NORTH);
        }
        else {
            gamePanel.add(whitePlayerPanel,BorderLayout.NORTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.SOUTH);
        }
        notation = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notation);
        notation.setEditable(false);
        notation.setLineWrap(true);
        gamePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.EAST);
    }

    /**
     * <p>
     * This method creates gui for the pvp game.
     * </p>
     * @param boardString the string from which the players board can be created
     * @param fromEditor boolean for telling if the game was started from the editor
     */
    public void openPvPGame(String boardString, boolean fromEditor) {
        resetState();
        Board board = new Board(GameType.PVP);

        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }
        else {
            board.initializeBoard();
        }

        hideMainMenu();

        // Randomly picks the colors for the players
        int random = Helpers.randomNumber(0,100);

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
        this.game = frame;
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(820, 720));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        JPanel whitePlayerPanel = new JPanel(new BorderLayout());

        long startMinutesLeftWhite = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
        long startSecondsLeftWhite = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
        JLabel timeWhite = new JLabel(Helpers.formatTime(startMinutesLeftWhite,startSecondsLeftWhite));

        Border border = timeWhite.getBorder();
        Border margin = new EmptyBorder(30,30,30,30);
        timeWhite.setBorder(new CompoundBorder(border, margin));
        timeWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setBorder(new CompoundBorder(border, margin));
        whitePlayerPanel.add(nameWhite,BorderLayout.NORTH);
        whitePlayerPanel.add(timeWhite,BorderLayout.SOUTH);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout());

        long startMinutesLeftBlack = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
        long startSecondsLeftBlack = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
        JLabel timeBlack = new JLabel(Helpers.formatTime(startMinutesLeftBlack,startSecondsLeftBlack));

        border = timeBlack.getBorder();
        margin = new EmptyBorder(30,30,30,30);
        timeBlack.setBorder(new CompoundBorder(border, margin));
        timeBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameBlack.setBorder(new CompoundBorder(border, margin));
        blackPlayerPanel.add(nameBlack,BorderLayout.NORTH);
        blackPlayerPanel.add(timeBlack,BorderLayout.SOUTH);

        State.getInstance().setupPlayersTimes();

        int delay = 500; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(State.getInstance().isWhiteOnMove()) {
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeWhite.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, "Černý vyhrál, bílemu došel  čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(false);
                            showMainMenu();
                        }
                    }
                    timeWhite.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
                else {
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeBlack.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, " Bílý vyhrál, černému došel čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(false);
                            showMainMenu();
                        }
                    }
                    timeBlack.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
                timer.stop();
            }
        });

        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            gamePanel.add(whitePlayerPanel,BorderLayout.SOUTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.NORTH);
        }
        else {
            gamePanel.add(whitePlayerPanel,BorderLayout.NORTH);
            gamePanel.add(blackPlayerPanel, BorderLayout.SOUTH);
        }
        notation = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notation);
        notation.setEditable(false);
        notation.setLineWrap(true);
        gamePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.EAST);

        // If the game was run from editor we need to check if the game is not already won.
        if (fromEditor) {
            if(checkWin(board, GameType.PVP)){
                timer.stop();
                hideMainMenu();
            }

        }
    }

    /**
     * <p>
     * This method creates gui for ai game.
     * </p>
     * @param boardString the string from which the players board can be created
     * @param answer the answer from dialog which player select which color he will be
     * @param fromEditor boolean for telling if the game was started from the editor
     */
    public void openAIGame(String boardString, int answer, boolean fromEditor) {
        JPanel gamePanel = new JPanel(new BorderLayout());
        notation = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(notation);
        notation.setEditable(false);
        notation.setLineWrap(true);
        resetState();
        Board board = new Board(GameType.PVE);

        if (!Objects.equals(boardString, "")) {
            board.stringToBoard(boardString, false);
        }
        else {
            board.initializeBoard();
        }

        hideMainMenu();

        // Select the colors for the both players
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
        boolean end = false;
        // If AI is white we need to play his move, otherwise we only set the labels.
        if (p2.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            nameWhite = new JLabel("AI");
            nameBlack = new JLabel("Hrac");
            // If black is not in check we play, else we reverse the move and the AI won.
            if (!board.blackInCheck()) {
                game.playForAi();
            }
            else {
               State.getInstance().reverseMove();
            }
        }
        else {
            nameBlack = new JLabel("AI");
            nameWhite = new JLabel("Hrac");
        }

        BoardView mainPanel = new BoardView(board);
        JFrame frame = new JFrame("Chess");
        this.game = frame;

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(820, 720));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);


        gamePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(gamePanel, BorderLayout.EAST);

        JPanel whitePlayerPanel = new JPanel(new BorderLayout());

        long startMinutesLeftWhite = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
        long startSecondsLeftWhite = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
        JLabel timeWhite = new JLabel(Helpers.formatTime(startMinutesLeftWhite,startSecondsLeftWhite));

        Border border = timeWhite.getBorder();
        Border margin = new EmptyBorder(30,30,30,30);
        timeWhite.setBorder(new CompoundBorder(border, margin));
        timeWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setFont(new Font("Roboto", Font.PLAIN, 20));
        nameWhite.setBorder(new CompoundBorder(border, margin));
        whitePlayerPanel.add(nameWhite,BorderLayout.NORTH);
        whitePlayerPanel.add(timeWhite,BorderLayout.SOUTH);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout());

        long startMinutesLeftBlack = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
        long startSecondsLeftBlack = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
        JLabel timeBlack = new JLabel(Helpers.formatTime(startMinutesLeftBlack,startSecondsLeftBlack));

        int delay = 500; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(State.getInstance().isWhiteOnMove()) {
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftWhite());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftWhite());
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeWhite.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, "Černý vyhrál, bílemu došel  čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(false);
                            showMainMenu();
                        }
                    }
                    timeWhite.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
                else {
                    long minutesLeft = State.getInstance().getMinutesLeft(State.getInstance().getTimeLeftBlack());
                    long secondsLeft = State.getInstance().getSecondsLeft(State.getInstance().getTimeLeftBlack());
                    if(minutesLeft <= 0 && secondsLeft <= 0){
                        timeBlack.setText(Helpers.formatTime(0,0));
                        int answer = JOptionPane.showConfirmDialog(null, "Bílý vyhrál, černému došel čas.", "Upozornění", JOptionPane.DEFAULT_OPTION);
                        if(answer == 0 || answer == -1){
                            closeGameFrame(false);
                            showMainMenu();
                        }
                    }
                    timeBlack.setText(Helpers.formatTime(minutesLeft,secondsLeft));
                }
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
                timer.stop();
            }
        });

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

        // If the game is loaded from editor we need to check if the game was not already won.
        if (fromEditor) {
            end = checkWin(board, GameType.PVE);
        }
        if (end) {
            timer.stop();
        }
    }

    /**
     * <p>
     * This method creates gui for the editor.
     * </p>
     * @param boardString the string from which the players board can be created
     */
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
                showMainMenu();
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(950, 720));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        // We set button for playing against AI with actual board we created in the editor.
        JButton playButton = new JButton("Hraj proti AI");
        playButton.setSize(30,1);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if there are both kings to run the game against AI
                if (board.getKing(cz.cvut.fel.pjv.models.Color.WHITE) != null && board.getKing(cz.cvut.fel.pjv.models.Color.BLACK) != null) {
                    String[] options_color = {"White","Black"};
                    // We ask the player for which color he wants to play
                    int answer = JOptionPane.showOptionDialog(null, "Výběr barvy",
                                "Za jakou barvu si přejete hrát?",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_color, options_color[0]);
                    openAIGame(board.boardToString(), answer, true);
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    hideMainMenu();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nemůžete spustit hru bez dvou králů");
                }
            }
        });

        // We set button for playing against player with actual board we created in the editor.
        JButton pvpButton = new JButton("Hraj proti hraci");
        pvpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if there are both kings to run the game against player
                if (board.getKing(cz.cvut.fel.pjv.models.Color.WHITE) != null && board.getKing(cz.cvut.fel.pjv.models.Color.BLACK) != null) {
                    openPvPGame(board.boardToString(), true);
                    hideMainMenu();
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    hideMainMenu();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nemůžete spustit hru bez dvou králů");
                }
            }
        });

        // We set button for exporting current board to file in order to import it other time.
        JButton exportButton = new JButton("Exportuj sachovnici pro pozdejsi nacteni");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Helpers.exportBoard(board.boardToString());
                } catch (IOException ex) {
                    Logger.log(MainMenu.class, "openEditor",ex.getMessage());
                    JOptionPane.showMessageDialog(null,"Bohužel se nám nepodařilo hru uložit");
                }
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

    /**
     * <p>
     * This method sets the login for opponent.
     * </p>
     */
    public void setOpponentLogin(String login) {
        if (p1.getColor() == cz.cvut.fel.pjv.models.Color.WHITE) {
            nameBlack.setText(login);
        }
        else {
            nameWhite.setText(login);
        }
    }

    /**
     * <p>
     * This method updates the gui for the notation.
     * </p>
     */
    public void updateNotation(String moves){
        notation.setText(moves);
    }

    /**
     * <p>
     * This method creates new frame for settings. On close is hidden to not create it every time user clicks the settings button in the main menu.
     * </p>
     */
    public void setUpSettings() {
        this.settingsFrame = new JFrame("Nastavení");
        final JFrame[] figurky = {null};
        settingsFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        settingsFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (figurky[0] != null) {
                    figurky[0].dispose();
                }
                showMainMenu();
            }
        });
        settingsFrame.setMinimumSize(new Dimension(760, 679));
        settingsFrame.setLocationByPlatform(true);

        JPanel settingsPanel = new JPanel(new FlowLayout());

        // Button for user to select his color for white tiles on the board.
        JButton selectColorWhite = new JButton();
        selectColorWhite.setText("Políčka bílé barvy");
        selectColorWhite.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectColorWhite.setSize(100,50);
        selectColorWhite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.getInstance().setWhite(JColorChooser.showDialog(null,"Vyber barvu",State.getInstance().getWhite()));
                setUpColorButtons(selectColorWhite,null);
                try {
                    Helpers.writeColorsAndFigures();
                } catch (Exception error) {
                    Logger.log(MainMenu.class, "Vyber barev", error.getMessage());
                }
            }
        });

        // Button for user to select his color for black tiles on the board.
        JButton selectColorBlack = new JButton();
        selectColorBlack.setText("Políčka černé barvy");
        selectColorBlack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectColorBlack.setSize(50,50);
        selectColorBlack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.getInstance().setBlack(JColorChooser.showDialog(null,"Vyber barvu",State.getInstance().getBlack()));
                setUpColorButtons(selectColorWhite,selectColorBlack);
                try {
                    Helpers.writeColorsAndFigures();
                } catch (Exception error) {
                    Logger.log(MainMenu.class, "Vyber barev", error.getMessage());
                }
            }
        });

        // Button for user to select length of his local games.
        JButton selectGameLength = new JButton();
        selectGameLength.setText("Změni délku hry");
        selectGameLength.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectGameLength.setSize(50,50);
        selectGameLength.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options_color = {"1","3","5","10","30"};
                int answer = JOptionPane.showOptionDialog(null, "Delka hry",
                        "Delka hry",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_color, options_color[0]);
                State.getInstance().setGameLength(answer);
            }
        });

        // Button for user to select his favorite set of pieces to play with.
        JButton selectFiguresSet = new JButton();
        selectFiguresSet.setText("Vybrat vzhled figurek");
        selectFiguresSet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectFiguresSet.setSize(50,50);
        selectFiguresSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    figurky[0] = showPiecesPicker();
                } catch (IOException ex) {
                    Logger.log(MainMenu.class, "Vyber setu figurek", ex.getMessage());
                }
            }
        });

        setUpColorButtons(selectColorWhite,selectColorBlack);

        settingsPanel.add(selectColorWhite,FlowLayout.LEFT);
        settingsPanel.add(selectColorBlack,FlowLayout.CENTER);
        settingsPanel.add(selectGameLength,FlowLayout.RIGHT);
        settingsPanel.add(selectFiguresSet);
        settingsFrame.getContentPane().add(settingsPanel);
    }

    /**
     * <p>
     * This method makes the settings frame visible.
     * </p>
     */
    public void openSettings() {
        settingsFrame.setVisible(true);
    }

    /**
     * <p>
     * This method makes the settings buttons for changing colors in the colors of selected colors.
     * </p>
     * @param white color of white tiles ont the board
     * @param black color of black tiles on the board
     */
    public void setUpColorButtons(JButton white, JButton black) {
        if (black != null) {
            black.setForeground(State.getInstance().getWhite());
            black.setBackground(State.getInstance().getBlack());
        }
        white.setForeground(State.getInstance().getBlack());
        white.setBackground(State.getInstance().getWhite());
    }

    /**
     * <p>
     * This method updates the gui for the notation.
     * </p>
     * @param board board to be checked
     * @param gameType type of the actual game
     * @return returns if the game was won or drawn the returns true, otherwise returns false
     */
    public boolean checkWin(Board board, GameType gameType) {
        // Check if not both players are not in check. Because playing this game would not make sense.
        if (board.whiteInCheck() && board.blackInCheck()){
            JOptionPane.showMessageDialog(null, "Nemůžete spustit hru, když oba králové jsou v šachu.");
            closeGameFrame(false);
            openEditor("");
        }
        if (board.isDraw()){
            JOptionPane.showMessageDialog(null, "Nemůžete spustit hru z remízové pozice.");
            closeGameFrame(false);
            openEditor("");
        }

            else if (board.whiteInCheck()) {
                if (board.mated(cz.cvut.fel.pjv.models.Color.WHITE)) {
                    JOptionPane.showMessageDialog(null, "Černý vyhrál");
                    closeGameFrame(false);
                    openEditor("");
                    State.getInstance().resetMove();
                    return true;
                }
            }
            else if (board.blackInCheck()) {
                if(gameType != GameType.PVE) {
                    State.getInstance().reverseMove();
                }
                if (board.mated(cz.cvut.fel.pjv.models.Color.BLACK)) {
                    JOptionPane.showMessageDialog(null, "Bilý vyhrál");
                    closeGameFrame(false);
                    openEditor("");
                    State.getInstance().resetMove();
                    return true;
                }
            }
            return false;
    }

    /**
     * <p>
     * This method closes the frame with the game.
     * </p>
     * @param network boolean if the game is online or not
     */
    public void closeGameFrame(boolean network) {
        this.game.dispatchEvent(new WindowEvent(game, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * <p>
     * Shows all the games which were played on the server.
     * </p>
     * @param moves the string with all played games in format to be  easily parsed
     */
    public void openLastPlayedGames(String moves) {
        hideMainMenu();
        LastGamesView lgview = new LastGamesView(moves);

        for (GameHistoryButton game : lgview.getButtons()) {
            game.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openGameExplorer((ArrayList<String>) game.getGh().getMoves());
                }
            });
        }
        lgview.addButtons();

        JFrame frame = new JFrame("Vsechny odehrane hry");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
            }
        });
        JScrollPane scrollPane = new JScrollPane(lgview);
        frame.getContentPane().add(scrollPane);
        frame.setMinimumSize(new Dimension(930, 679));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    /**
     * <p>
     * This method opens gameExplorer and allows user to go back and forward in game he selected
     * </p>
     * @param moves all moves from the game user selected
     */
    public void openGameExplorer(ArrayList<String> moves) {
        State.getInstance().resetMove();
        Board board = new Board(GameType.SERVER);

        hideMainMenu();
        final int[] currentMove = {0};
        board.stringToBoard(moves.get(currentMove[0]), false);

        BoardView mainPanel = new BoardView(board);
        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.setMinimumSize(new Dimension(930, 679));
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JPanel gamePanel = new JPanel(new BorderLayout());

        JButton forwardButton = new JButton("Dále");
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMove[0] + 2 > moves.size()) {
                    JOptionPane.showMessageDialog(null, "Žádný tah potom neexistuje");
                }
                else {
                    State.getInstance().reverseMove();
                    currentMove[0] += 1;
                    board.stringToBoard(moves.get(currentMove[0]), true);
                    mainPanel.repaintBoard();
                }
            }
        });

        JButton backButton = new JButton("Zpět");
        backButton.setSize(100,100);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMove[0] - 1 < 0) {
                    JOptionPane.showMessageDialog(null, "Žádný tah předtím neexistuje");
                }
                else {
                    State.getInstance().reverseMove();
                    currentMove[0] -= 1;
                    board.stringToBoard(moves.get(currentMove[0]), true);
                    mainPanel.repaintBoard();
                }
            }
        });

        JPanel middlePanel = new JPanel(new BorderLayout());

        EmptyBorder margin = new EmptyBorder(128,80,128,80);
        forwardButton.setBorder(new CompoundBorder(forwardButton.getBorder(), margin));
        backButton.setBorder(new CompoundBorder(backButton.getBorder(), margin));

        forwardButton.setFont(new Font("Serif", Font.PLAIN, 40));
        backButton.setFont(new Font("Serif", Font.PLAIN, 40));

        middlePanel.add(forwardButton,BorderLayout.NORTH);
        middlePanel.add(backButton,BorderLayout.SOUTH);
        gamePanel.add(middlePanel,BorderLayout.CENTER);
        gamePanel.setAlignmentX(50);

        frame.add(gamePanel,BorderLayout.EAST);
    }

    /**
     * <p>
     * This method opens current logged in player profile with his statistics online.
     * </p>
     * @param results string with statistic data for the player
     */
    public void openPlayerProfile(String results) {
        hideMainMenu();
        String[] values = results.split(",");

        JFrame frame = new JFrame("Chess");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(930, 679));
        frame.pack();
        frame.setLocationByPlatform(true);
        if (State.getInstance().getLogin() == null) {
            State.getInstance().setLogin();
        }
        frame.setVisible(true);

        frame.setLayout(new BorderLayout());

        JLabel profile = new JLabel();
        EmptyBorder margin = new EmptyBorder(50,320,0,60);
        profile.setBorder(new CompoundBorder(profile.getBorder(), margin));
        profile.setIcon(new ImageIcon(new ImageIcon("resources/profile.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT)));
        frame.add(profile, BorderLayout.NORTH);

        JLabel name = new JLabel(State.getInstance().getLogin());
        name.setBorder(new CompoundBorder(name.getBorder(), new EmptyBorder(0,320,10,60)));
        name.setFont(new Font("Roboto", Font.PLAIN, 40));
        frame.add(name,BorderLayout.CENTER);

        JLabel stats = new JLabel("Počet odehraných her: "+values[0]+"    Výher: "+values[1]+"     Proher: "+values[2]+"    Remízy: "+values[3]);
        stats.setBorder(new CompoundBorder(stats.getBorder(), new EmptyBorder(0,120,60,60)));
        stats.setFont(new Font("Roboto", Font.PLAIN, 25));
        frame.add(stats,BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                showMainMenu();
            }
        });
    }

    public JLabel getNameWhite() {
        return nameWhite;
    }

    public JLabel getNameBlack() {
        return nameBlack;
    }

    /**
     * <p>
     * This method creates the picker for picking pieces sets with pictures how the pieces will look.
     * </p>
     * @return returns JFrame for easier manipulation and closing the frame from other method.
     */
    public JFrame showPiecesPicker() throws IOException {
        JFrame frame = new JFrame("Figurky");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setMinimumSize(new Dimension(250, 120));
        frame.setMaximumSize(new Dimension(250, 120));
        frame.setLocationByPlatform(true);
        frame.setVisible(true);

        JButton setOneButton = new JButton(new ImageIcon(new ImageIcon("resources/pieces/set1/black_pawn.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        JButton setTwoButton = new JButton(new ImageIcon(new ImageIcon("resources/pieces/set2/black_pawn.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));
        JButton setThreeButton = new JButton(new ImageIcon(new ImageIcon("resources/pieces/set3/black_pawn.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)));

        setOneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.getInstance().setPiecesSet("set1");
                try {
                    Helpers.writeColorsAndFigures();
                } catch (IOException ex) {
                    Logger.log(MainMenu.class, "Vyber setu figurek", ex.getMessage());
                }
                frame.dispose();
            }
        });
        setTwoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.getInstance().setPiecesSet("set2");
                try {
                    Helpers.writeColorsAndFigures();
                } catch (IOException ex) {
                    Logger.log(MainMenu.class, "Vyber setu figurek", ex.getMessage());
                }
                frame.dispose();
            }
        });
        setThreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                State.getInstance().setPiecesSet("set3");
                try {
                    Helpers.writeColorsAndFigures();
                } catch (IOException ex) {
                    Logger.log(MainMenu.class, "Vyber setu figurek", ex.getMessage());
                }
                frame.dispose();
            }
        });

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(setOneButton,BorderLayout.WEST);
        frame.getContentPane().add(setTwoButton, BorderLayout.CENTER);
        frame.getContentPane().add(setThreeButton, BorderLayout.EAST);

        return frame;
    }

    /**
     * <p>
     * This method resets from previous games which could be still in State
     * </p>
     */
    public void resetState() {
        State.getInstance().resetTimers();
        if (!State.getInstance().isWhiteOnMove()) {
            State.getInstance().resetMove();
        }
    }
}


