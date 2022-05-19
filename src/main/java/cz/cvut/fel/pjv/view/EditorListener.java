package cz.cvut.fel.pjv.view;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;
import cz.cvut.fel.pjv.models.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EditorListener is ActionListener which is on every button in editor.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class EditorListener implements ActionListener {

    private Square square;
    private Board board;
    private BoardView bw;

    public EditorListener(Square square, Board board, BoardView bw) {
        this.square = square;
        this.board = board;
        this.bw = bw;
    }

    /**
     * <p>
     * After click on any square in editor this method is called. Gives the user choice to pick color and type of figure he wants to place on the square he already clicked/
     * </p>
     * @param e ActionEvent which is click on the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        SquareView[][] squarePanels = bw.getSquarePanels();
        ButtonCoord buttonCoord = (ButtonCoord) e.getSource();
        if (squarePanels[buttonCoord.getX()][buttonCoord.getY()].getDot() == null) {
            int x = buttonCoord.getX();
            int y = buttonCoord.getY();

            String[] options_color = {"White", "Black"};
            String[] options_piece = new String[]{"Rook", "Queen", "Bishop", "Knight", "Pawn"};
            String[] options_piece_king = new String[]{"Rook", "Queen", "Bishop", "Knight", "Pawn", "King"};

            int color_choice = JOptionPane.showOptionDialog(null, "Vyber si barvu figurky",
                    "Vyber si barvu figurky",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_color, options_color[0]);

            Color color;
            if (color_choice == 0) {
                color = Color.WHITE;
            } else {
                color = Color.BLACK;
            }
            int answer;
            if (board.getKing(color) == null) {
                answer = JOptionPane.showOptionDialog(null, "Vyber si figurku",
                        "Vyber si figurku",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_piece_king, options_piece_king[0]);
            } else {
                answer = JOptionPane.showOptionDialog(null, "Vyber si figurku",
                        "Vyber si figurku",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options_piece, options_piece[0]);
            }
            try {
                String type = switch (answer) {
                    case 0 -> "Rook";
                    case 1 -> "Queen";
                    case 2 -> "Bishop";
                    case 3 -> "Knight";
                    case 4 -> "Pawn";
                    case 5 -> "King";
                    default -> throw new IllegalStateException("Unexpected value: " + answer);
                };
                if (board.getBoard()[x][y].getPiece() != null) {
                    if (type.equals("King") && board.getKing(Helpers.getOtherColor(color)) != null &&
                            board.getKing(Helpers.getOtherColor(color)).getAttackMovesForKingMove(board).contains(board.getBoard()[x][y])) {
                        JOptionPane.showMessageDialog(null, "Králové nemohou stát vedle sebe.");
                    } else {
                        board.removePiece(board.getBoard()[x][y].getPiece());
                        board.putPiece(x, y, type, color);
                        bw.repaintBoard();
                    }
                } else if (type.equals("King") && board.getKing(Helpers.getOtherColor(color)) != null &&
                        board.getKing(Helpers.getOtherColor(color)).getAttackMovesForKingMove(board).contains(board.getBoard()[x][y])) {

                    JOptionPane.showMessageDialog(null, "Králové nemohou stát vedle sebe.");
                } else if (type.equals("Pawn") && (y == 0 || y == 7)) {
                    JOptionPane.showMessageDialog(null, "Toto umístění pěšce není možné.");
                } else {
                    board.putPiece(x, y, type, color);
                    bw.repaintBoard();
                }
            } catch (Exception ex) {
                Logger.log(Board.class, "actionPerformed", "Hrac vybral policko ale ne figurku.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Toto pole už nelze editovat.");
        }
    }
}
