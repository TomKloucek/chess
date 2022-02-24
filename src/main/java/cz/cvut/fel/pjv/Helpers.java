package cz.cvut.fel.pjv;

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
}
