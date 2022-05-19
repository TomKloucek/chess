package cz.cvut.fel.pjv.helpers;

import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.pieces.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Helpers is a class in which all helping methods are stored.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Helpers {
    /**
     * This method translates a number of x coordinate into its letter representation.
     *
     * @param x is a number of coordinate
     * @return letter representation of number coordinate
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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

    /**
     * This method generates a random number in the given range.
     *
     * @param start start of given range
     * @param end end of given range
     * @return random number in the given range
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static int randomNumber(int start, int end) {
        Random random = new Random();
        return random.nextInt(start,end);
    }
    /**
     * This method translates a letter representation of x coordinate into its number representation.
     *
     * @param x letter representation of given coordinate
     * @return number representation of x coordinate
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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

    /**
     * This method checks if move with the given coordinates is inside the board.
     *
     * @param x x coordinate of move
     * @param y y coordinate of move
     * @return true if x and y are in range between 0 and 7 (including)
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static boolean MoveInBoard(int x, int y) {
        return x <= 7 && x >= 0 && y <= 7 && y >= 0;
    }

    /**
     * This method checks if move of pieces with the given coordinates is inside the board.
     *
     * @param actualCoordinateX x coordinate of piece which will be moved
     * @param actualCoordinateY y coordinate of piece which will be moved
     * @param additionToCoordinateX number which has to be added to x coordinate to check whether the move is inside the board
     * @param additionToCoordinateY number which has to be added to y coordinate to check whether the move is inside the board
     * @param coordinate determines which coordinate has to be returned
     * @return value of actual coordinate if move is not inside the board, or value of wanted coordinate
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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

    /**
     * This method gets the opposite color.
     *
     * @param color color opposite to which has to be found
     * @return opposite color of parameter color
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static Color getOtherColor(Color color) {
        if (color == Color.WHITE) {
            return Color.BLACK;
        }
        if (color == Color.BLACK) {
            return Color.WHITE;
        }
        return color;
    }

    /**
     * This method returns intersection of two lists.
     *
     * @param list1 first list with values which might be intersected with the other list
     * @param list2 second list with values which might be intersected with the other list
     * @return intersection of two lists
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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


    /**
     * This method returns image of piece.
     *
     * @param square square on which the piece is located
     * @param board parameter to help the method to know if king is checked or not
     * @return image of piece on the given square
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static Image getPieceImage(Square square, Board board) {
        IPiece IPiece = square.getPiece();
        String set = State.getInstance().getPiecesSet();
        if (IPiece == null) {
            return null;
        }
        try {
            if (IPiece instanceof Pawn) {
                if (IPiece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/"+set+"/white_pawn.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/"+set+"/black_pawn.png"));
                }
            }
            if (IPiece instanceof Knight) {
                if (IPiece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/"+set+"/white_knight.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/"+set+"/black_knight.png"));
                }
            }
            if (IPiece instanceof Bishop) {
                if (IPiece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/"+set+"/white_bishop.png"));
                } else {

                    return ImageIO.read(new File("resources/pieces/"+set+"/black_bishop.png"));
                }
            }
            if (IPiece instanceof Rook) {
                if (IPiece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/"+set+"/white_rook.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/"+set+"/black_rook.png"));
                }
            }
            if (IPiece instanceof Queen) {
                if (IPiece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("resources/pieces/"+set+"/white_queen.png"));
                } else {
                    return ImageIO.read(new File("resources/pieces/"+set+"/black_queen.png"));
                }
            }
            if (IPiece instanceof King) {
                if (IPiece.getColor() == Color.WHITE) {
                    if (State.getInstance().isWhiteOnMove() && board.whiteInCheck()) {

                        return ImageIO.read(new File("resources/pieces/"+set+"/white_king_in_check.png"));
                    }
                    else {
                        return ImageIO.read(new File("resources/pieces/"+set+"/white_king.png"));
                    }

                } else {
                    if (!State.getInstance().isWhiteOnMove() && board.blackInCheck()){
                        return ImageIO.read(new File("resources/pieces/"+set+"/black_king_in_check.png"));
                    }
                    return ImageIO.read(new File("resources/pieces/"+set+"/black_king.png"));
                }
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            Logger.log(Helpers.class, "getPieceImage","Nepovedlo se najit obrazek");
            return null;
        }
    }

    /**
     * This method exports board to String in file.
     *
     * @param board board which has to be exported
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static void exportBoard(String board) throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            FileWriter myWriter = new FileWriter(file.getAbsolutePath());
            myWriter.write(board);
            myWriter.close();
        }
    }

    /**
     * This method imports String from file.
     *
     * @return String representation of board from file
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
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

    /**
     * This method writes colors and figures settings the client file.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static void writeColorsAndFigures() throws IOException {
            File file = new File("client.txt");
            FileWriter myWriter = new FileWriter(file.getAbsolutePath());
            java.awt.Color white = State.getInstance().getWhite();
            java.awt.Color black = State.getInstance().getBlack();
            myWriter.write("Color:"+ white.getRed()+","+white.getGreen() + "," + white.getBlue() + ":" + black.getRed() + "," + black.getGreen() + "," + black.getBlue()+":"+State.getInstance().getPiecesSet());
            myWriter.close();
    }

    /**
     * This method sets length of the game from given choices.
     *
     * @param value required number of seconds of game length
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static long getGameLength(int value) {
        return switch (value) {
            case 0 -> 60;
            case 1 -> 180;
            case 2 -> 300;
            case 4 -> 1800;
            default -> 600;
        };
    }


    /**
     * This method formats time so the user has better experience.
     *
     * @param minutes count of minutes
     * @param seconds count of seconds
     *
     * @return String with or without added zeroes for better user experience
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static String formatTime(long minutes, long seconds) {
        String result = "";
        if (minutes < 10) {
            result += String.format("%02d", minutes);
        }
        else {
            result += minutes;
        }
        result += ":";
        if (seconds < 10) {
            result += String.format("%02d", seconds);
        }
        else {
            result += seconds;
        }
        return result;
    }

    /**
     * This method sends game history with additional information such as player logins and determination of winner.
     *
     * @param moves moves which were made in the game
     * @param loginWhite login of white player
     * @param loginBlack login of black player
     * @param win determination of winner
     *
     * @return String of the whole game with the additional information
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static String gameHistory(ArrayList<String> moves, String loginWhite, String loginBlack, int win) {
        StringBuilder resultString = new StringBuilder();
        boolean first = true;
        for (String move : moves) {
            if (first) {
               resultString.append(move);
               first = false;
            }
            else {
                resultString.append(";").append(move);
            }
        }
        resultString.append("$").append(loginWhite);
        resultString.append("$").append(loginBlack);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        resultString.append("$").append(formatter.format(date));
        resultString.append("$").append(win);
        return  resultString.toString();
    }

    /**
     * This method translates Strings to games.
     *
     * @param allgames String representation of all games
     *
     * @return individual games to be displayed
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public static ArrayList<GameHistory> gamesFromString(String allgames) {
        ArrayList<GameHistory> result = new ArrayList<>();
        String[] gamesSplit = allgames.split("@");
        for (String game : gamesSplit) {
            String[] data = game.split("\\$");
            String[] moves = data[0].split(";");
            ArrayList<String> movesList = new ArrayList<String>( Arrays.asList( moves ) );
            result.add(new GameHistory(data[1],data[2],movesList,data[3]));
        }
        return result;
    }

}
