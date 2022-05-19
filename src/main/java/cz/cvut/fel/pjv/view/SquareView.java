package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * SquareView is graphical representation of one square in board.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class SquareView extends JPanel {
    private Square square;
    private Board board;
    private final ButtonCoord button;
    private Image dot = null;

    /**
     * <p>
     * This method returns the model of square.
     * </p>
     * @return the square
     */
    public Square getSquare() {
        return square;
    }

    public void setDot(Image dot) {
        this.dot = dot;
    }

    public Image getDot() {
        return dot;
    }

    /**
     * <p>
     * This method creates the SquareView object. Sets up the button for gui and if piece exist on this square, then it add the button to this square.
     * For editor returns different listener on click.
     * </p>
     */
    public SquareView(LayoutManager layout, Square square, Board board, BoardView bw,boolean editor) {
        super(layout);
        this.square = square;
        this.board = board;
        this.button = new ButtonCoord(square.getX()-1, square.getY());

        // If this square is in editor then EditorListener is added, otherwise basic ClickListener is added to play the game.
        if (!editor) {
            button.addActionListener(new ClickListener(square, board, bw));
        }
        else {
            button.addActionListener(new EditorListener(square, board, bw));
            this.add(button);
        }
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        if(square.getPiece()!=null) {
            this.add(button);
        }
    }
    public void setButton(){
        if(square.getPiece()!=null || this.getDot()!=null) {
            this.add(button);
        }
    }
    public ButtonCoord getButton(){
        return this.button;
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Image piece = Helpers.getPieceImage(square, board);
        g.drawImage(piece, 8, 8, this);


        if (dot != null) {
            g.drawImage(dot, 8, 8, this);
        }

    }

}
