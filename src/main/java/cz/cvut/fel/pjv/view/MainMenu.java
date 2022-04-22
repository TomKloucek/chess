package cz.cvut.fel.pjv.view;// Java program to illustrate the BorderLayout
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                showGameDialogue();
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

    public void showGameDialogue() {
            // create a dialog Box
            JDialog dialogBox = new JDialog(frame, "dialog Box");
        dialogBox = new JDialog(frame, "dialog Box");

        // create a label
        JLabel l = new JLabel("this is first dialog box");

        // create a button
        JButton b = new JButton("click me");

        // add Action Listener
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameDialogue();
            }
        });

        // create a panel
        JPanel p = new JPanel();

        p.add(l);
        p.add(b);


        // add panel to dialog
        dialogBox.add(p);

        // setsize of dialog
        dialogBox.setSize(200, 200);

        // set visibility of dialog
        dialogBox.setVisible(true);
    }
}


