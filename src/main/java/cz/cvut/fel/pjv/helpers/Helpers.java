package cz.cvut.fel.pjv.helpers;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;
import cz.cvut.fel.pjv.models.State;
import cz.cvut.fel.pjv.pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Helpers {
    public static String XTranslate(int x) {
        return switch (x + 1) {
            case 1 -> "a";
            case 2 -> "b";
            case 3 -> "c";
            case 4 -> "d";
            case 5 -> "e";
            case 6 -> "f";
            case 7 -> "g";
            case 8 -> "h";
            default -> "";
        };
    }

    public static int randomNumber(int start, int end) {
        Random random = new Random();
        return random.nextInt(start,end);
    }

    public static int XTranslateBack(String x) {
        return switch (x) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            case "f" -> 5;
            case "g" -> 6;
            case "h" -> 7;
            default -> -1;
        };
    }

    public static boolean MoveInBoard(int x, int y) {
        return x <= 7 && x >= 0 && y <= 7 && y >= 0;
    }
    public static int checkSquareCoordinates(int actualCoordinateX, int additionToCoordinateX, int actualCoordinateY, int additionToCoordinateY, String coordinate){
        int requiredCoordinateX =  actualCoordinateX + additionToCoordinateX;
        int requiredCoordinateY = actualCoordinateY + additionToCoordinateY;
        if (coordinate.equals("x")) {
            if ((-1 < requiredCoordinateX && requiredCoordinateX < 8) && (-1 < requiredCoordinateY && requiredCoordinateY < 8)) {
                return requiredCoordinateX;
            } else {
                return actualCoordinateX;
            }
        }
        else{
            if ((-1 < requiredCoordinateX && requiredCoordinateX < 8) && (-1 < requiredCoordinateY && requiredCoordinateY < 8)) {
                return requiredCoordinateY;
            } else {
                return actualCoordinateY;
            }
        }
    }

    public static Color getOtherColor(Color color) {
        if (color == Color.WHITE) {
            return Color.BLACK;
        }
        if (color == Color.BLACK) {
            return Color.WHITE;
        }
        return color;
    }

    public static  <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public static Image getPieceImage(Square square, Board board) {
        Piece piece = square.getPiece();
        if (piece == null) {
            return null;
        }
        try {
            if (piece instanceof Pawn) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/white_pawn.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/black_pawn.png"));
                }
            }
            if (piece instanceof Knight) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/white_knight.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/black_knight.png"));
                }
            }
            if (piece instanceof Bishop) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/white_bishop.png"));
                } else {

                    return ImageIO.read(new File("resources/pieces/black_bishop.png"));
                }
            }
            if (piece instanceof Rook) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/white_rook.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/black_rook.png"));
                }
            }
            if (piece instanceof Queen) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/white_queen.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/black_queen.png"));
                }
            }
            if (piece instanceof King) {
                if (piece.getColor() == Color.WHITE) {
                    if (State.getInstance().isWhiteOnMove() && board.whiteInCheck()) {

                        return ImageIO.read(new File("resources/pieces/white_king_in_check.png"));
                    }
                    else {
                        return ImageIO.read(new File("resources/pieces/white_king.png"));
                    }

                } else {
                    if (!State.getInstance().isWhiteOnMove() && board.blackInCheck()){
                        return ImageIO.read(new File("resources/pieces/black_king_in_check.png"));
                    }
                    return ImageIO.read(new File("resources/pieces/black_king.png"));
                }
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void exportBoard(String board) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileWriter myWriter = new FileWriter(file.getAbsolutePath());
            myWriter.write(board);
            myWriter.close();
        }
    }

    public static String importBoard() throws FileNotFoundException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Scanner myReader = new Scanner(file);
            String returnStatement = myReader.nextLine();
            myReader.close();
            return returnStatement;
        }
        return "";
    }
}
