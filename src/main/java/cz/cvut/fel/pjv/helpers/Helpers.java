package cz.cvut.fel.pjv.helpers;

import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.Square;
import cz.cvut.fel.pjv.pieces.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public static Image getPieceImage(Square square) {
        Piece piece = square.getPiece();
        if (piece == null) {
            return null;
        }
        try {
            if (piece instanceof Pawn) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("pieces/white_pawn.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_pawn.svg"));
                }
            }
            if (piece instanceof Knight) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/white_knight.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_knight.svg"));
                }
            }
            if (piece instanceof Bishop) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/white_bishop.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_bishop.svg"));
                }
            }
            if (piece instanceof Rook) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/white_rook.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_rook.svg"));
                }
            }
            if (piece instanceof Queen) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/white_queen.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_queen.svg"));
                }
            }
            if (piece instanceof King) {
                if (piece.getColor() == Color.WHITE) {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/white_king.svg"));
                } else {
                    return ImageIO.read(new File("../../../media/gitlab/pieces/svg/black_king.svg"));
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
}
