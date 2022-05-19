package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.loggers.Logger;
import cz.cvut.fel.pjv.models.pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Board is one of the main classes of the whole project. It has all information of the positions and pieces.
 *
 * @author Tomas Kloucek
 * @author Vladyslav Babyc
 *
 */
public class Board {
    private Square[][] board;

    private ArrayList<IPiece> whiteIPieces;
    private ArrayList<IPiece> blackIPieces;
    private GameType type;

    private String notation ="";

    private int counter = 1;

    private int counterHelper = 0;

    private boolean addFirstNotation = true;


    /**
     * A constructor of Board.
     *
     * @param type type of game which is played
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public Board(GameType type) {
        this.board = new Square[8][8];
        this.blackIPieces = new ArrayList<>();
        this.whiteIPieces = new ArrayList<>();
        this.type = type;
    }

    /**
     * This method returns all pieces of the required color.
     *
     * @param color required color of pieces
     *
     * @return list of pieces of the required color
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<IPiece> getPieces(Color color) {
        if (color == Color.WHITE) {
            return whiteIPieces;
        } else {
            return blackIPieces;
        }
    }

    /**
     * This method returns squares of board.
     *
     * @return list squares
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public Square[][] getBoard() {
        return board;
    }


    /**
     * This method initiates editor of board.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void initializeEditor() {
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j, i, null);
            }
        }
    }


    /**
     * This method initiates board for game.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void initializeBoard() {

        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j, i, null);
            }
        }

        // WHITE PLAYER
        putPiece(0, 0, "Rook", Color.WHITE);
        putPiece(1, 0, "Knight", Color.WHITE);
        putPiece(2, 0, "Bishop", Color.WHITE);
        putPiece(3, 0, "Queen", Color.WHITE);
        putPiece(4, 0, "King", Color.WHITE);
        putPiece(5, 0, "Bishop", Color.WHITE);
        putPiece(6, 0, "Knight", Color.WHITE);
        putPiece(7, 0, "Rook", Color.WHITE);

        for (int i = 0; i < 8; i++) {
            putPiece(i, 1, "Pawn", Color.WHITE);
        }

        // BLACK PLAYER
        for (int i = 0; i < 8; i++) {
            putPiece(i, 6, "Pawn", Color.BLACK);
        }

        putPiece(0, 7, "Rook", Color.BLACK);
        putPiece(1, 7, "Knight", Color.BLACK);
        putPiece(2, 7, "Bishop", Color.BLACK);
        putPiece(3, 7, "Queen", Color.BLACK);
        putPiece(4, 7, "King", Color.BLACK);
        putPiece(5, 7, "Bishop", Color.BLACK);
        putPiece(6, 7, "Knight", Color.BLACK);
        putPiece(7, 7, "Rook", Color.BLACK);
    }

    public void printBoard() {
        System.out.println("---------------------------------------------------------");
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i].getPiece() != null) {
                    if (j == 0) {
                        System.out.print("| " + board[j][i].getPiece() + " |");
                    } else {
                        System.out.print(" " + board[j][i].getPiece() + " |");
                    }
                } else {
                    if (j == 0) {
                        System.out.print("|     |");
                    } else {
                        System.out.print("      |");
                    }
                }
            }
            System.out.println("\n---------------------------------------------------------");
        }
    }


    /**
     * This method puts piece on the board.
     *
     * @param x required x coordinate of piece
     * @param y required y coordinate of piece
     * @param type required type of piece
     * @param color required color of piece
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void putPiece(int x, int y, String type, Color color) {
        IPiece IPiece = switch (type) {
            case "Pawn" -> new Pawn(color, x, y);
            case "Knight" -> new Knight(color, x, y);
            case "Queen" -> new Queen(color, x, y);
            case "King" -> new King(color, x, y);
            case "Rook" -> new Rook(color, x, y);
            case "Bishop" -> new Bishop(color, x, y);
            default -> null;
        };
        if (color == Color.WHITE) {
            whiteIPieces.add(IPiece);
        } else {
            blackIPieces.add(IPiece);
        }
        board[x][y].setPiece(IPiece);
    }

    /**
     * This method puts piece into the list of the same colored pieces.
     *
     * @param IPiece piece to be put into the list
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void putPiece(IPiece IPiece) {
        if (IPiece.getColor() == Color.WHITE) {
            whiteIPieces.add(IPiece);
        } else {
            blackIPieces.add(IPiece);
        }
    }

    /**
     * This method picks piece from the given coordinates.
     *
     * @param x required x from where the piece has to be picked
     * @param y required y from where the piece has to be picked
     *
     * @return piece on the given coordinates
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public IPiece pickPiece(int x, int y) {
        if (this.board[x][y].getPiece() == null) {
            return null;
        } else {
            return this.board[x][y].getPiece();
        }
    }

    /**
     * This method converts string to board.
     *
     * @param s board String which would be converted
     *
     * @return board representation of String
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */

    public void stringToBoard(String s, boolean network) {
        this.getPieces(Color.WHITE).clear();
        this.getPieces(Color.BLACK).clear();
        if (!network) {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    board[j][i] = new Square(j, i, null);
                }
            }
        }
        int counter = 0;
        String[] boardArray = s.split(",");
        try {
            for (int i = 7; i > -1; i--) {
                for (int j = 0; j < 8; j++) {
                    IPiece IPiece = pieceFromString(boardArray[counter]);
                    if (IPiece != null) {
                        putPiece(IPiece);
                    }
                    board[j][i].setPiece(IPiece);
                    counter++;
                }
            }
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null,"Tady se nám něco nepovedlo");
            Logger.log(Board.class, "stringToBoard", "Nepodarilo se prevest string do boardu");
            e.printStackTrace();
        }

    }


    /**
     * This method converts string to piece.
     *
     * @param piece piece String which would be converted
     *
     * @return piece representation of String
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public IPiece pieceFromString(String piece) {
        piece = piece.trim();
        char[] chars = piece.toCharArray();
        if (chars.length == 0) {
            return null;
        }
        if (chars.length == 3) {
            int y = Character.getNumericValue(chars[2]) - 1;
            if (y > 7 || Helpers.XTranslateBack(String.valueOf(chars[1])) == -1) {
                return null;
            }
            if (chars[0] == 'W') {
                return new Pawn(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[1])), y, true);
            } else {
                return new Pawn(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[1])), y, true);
            }
        } else if ((chars[0] == 'W' || chars[0] == 'B') && chars[3] == 'X') {
            int y = Character.getNumericValue(chars[2]) - 1;
            if (y > 7 || Helpers.XTranslateBack(String.valueOf(chars[1])) == -1) {
                return null;
            }
            if (chars[0] == 'W') {
                return new Pawn(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[1])), y, false);
            } else {
                return new Pawn(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[1])), y, false);
            }
        } else {
            int y = Character.getNumericValue(chars[3]) - 1;
            if (y > 7 || Helpers.XTranslateBack(String.valueOf(chars[2])) == -1) {
                return null;
            }
            if (chars[0] == 'K') {
                if (chars[1] == 'W') {
                    return new King(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                } else {
                    return new King(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                }
            } else if (chars[0] == 'R') {
                if (chars[1] == 'W') {
                    return new Rook(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                } else {
                    return new Rook(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                }
            } else if (chars[0] == 'Q') {
                if (chars[1] == 'W') {
                    return new Queen(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                } else {
                    return new Queen(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                }
            } else if (chars[0] == 'N') {
                if (chars[1] == 'W') {
                    return new Knight(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                } else {
                    return new Knight(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                }
            } else if (chars[0] == 'B') {
                if (chars[1] == 'W') {
                    return new Bishop(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                } else {
                    return new Bishop(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])), y);
                }
            }
        }
        return null;
    }


    /**
     * This method sets motion to all pawns which were not moved two squares ahead.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void setMotionToPawns(ArrayList<IPiece> IPieces, IPiece chosen) {
        for (IPiece IPiece : IPieces) {
            if (chosen != IPiece && IPiece instanceof Pawn) {
                ((Pawn) IPiece).movedTwoSquares = false;
            }
        }
    }


    /**
     * This method moves piece on the board.
     *
     * @param chosen chosen piece which has to be moved
     * @param x required x of the demanded move
     * @param y required y of the demanded move
     *
     * @return true if the piece was succesfully moved
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean movePiece(IPiece chosen, int x, int y) {
        setMotionToPawns(this.getPieces(chosen.getColor()), chosen);
        if (chosen.possibleMovement(this).contains(board[x][y])) {
            if ((y == 0 || y == 7) && chosen instanceof Pawn) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                if (y == 0 && chosen.getColor() == Color.BLACK) {
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
                    board[x][y].getPiece().Move(x, y);
                    blackIPieces.remove(chosen);
                    blackIPieces.add(board[x][y].getPiece());
                    refillPiecesLists();
                }
                if (y == 7 && chosen.getColor() == Color.WHITE) {
                    this.board[x][y].setPiece(null);
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
                    this.board[x][y].getPiece().Move(x, y);
                    whiteIPieces.remove(chosen);
                    whiteIPieces.add(board[x][y].getPiece());
                    refillPiecesLists();
                }
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
            }
            // EN PASSANT
            else if (chosen instanceof Pawn && (board[x][y - 1].getPiece() instanceof Pawn && board[x][y - 1].getPiece().getColor() == Color.BLACK) && board[x][y].getPiece() == null) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y - 1].setPiece(null);
                this.board[x][y].setPiece(chosen);
                board[x][y].getPiece().Move(x, y);
                refillPiecesLists();
            } else if (chosen instanceof Pawn && (board[x][y + 1].getPiece() instanceof Pawn && board[x][y + 1].getPiece().getColor() == Color.WHITE) && board[x][y].getPiece() == null) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y + 1].setPiece(null);
                this.board[x][y].setPiece(chosen);
                board[x][y].getPiece().Move(x, y);
                refillPiecesLists();
            }
            // CASTLE
            else if (chosen instanceof King && board[x][y].getPiece() instanceof Rook && board[x][y].getPiece().getColor() == chosen.getColor()) {
                if (x == 0 && y == 0) {
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook) this.board[0][0].getPiece();
                    this.board[0][0].setPiece(null);
                    this.board[3][0].setPiece(rook);
                    this.board[2][0].setPiece(chosen);
                    rook.Move(3, 0);
                    chosen.Move(2, 0);
                }
                if (x == 7 && y == 0) {
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook) this.board[7][0].getPiece();
                    this.board[7][0].setPiece(null);
                    this.board[5][0].setPiece(rook);
                    this.board[6][0].setPiece(chosen);
                    rook.Move(5, 0);
                    chosen.Move(6, 0);
                }
                if (x == 0 && y == 7) {
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook) this.board[0][7].getPiece();
                    this.board[0][7].setPiece(null);
                    this.board[3][7].setPiece(rook);
                    this.board[2][7].setPiece(chosen);
                    rook.Move(3, 7);
                    chosen.Move(2, 7);
                }
                if (x == 7 && y == 7) {
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook) this.board[7][7].getPiece();
                    this.board[7][7].setPiece(null);
                    this.board[5][7].setPiece(rook);
                    this.board[6][7].setPiece(chosen);
                    rook.Move(5, 7);
                    chosen.Move(6, 7);
                }
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
            } else {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y].setPiece(chosen);
                chosen.Move(x, y);
                refillPiecesLists();
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
            }
            if (this.getType() == GameType.SERVER) {
                State.getInstance().getClient().printWriter.println(this.boardToString());
                State.getInstance().getClient().printWriter.flush();
                State.getInstance().getGame().addMove(this.boardToString());

            }
            addMoveToNotation(chosen.toStringForNotation());
            if(this.getType() != GameType.TEST){
                State.getInstance().getGuiRef().updateNotation(getNotation());
            }
            State.getInstance().getGame().setGameString(this.boardToString());
            if(this.getType() == GameType.SERVER){
                if (this.mated(Color.WHITE)) {
                    State.getInstance().getClient().printWriter.println(Helpers.gameHistory(State.getInstance().getGame().getMoves(), State.getInstance().getGuiRef().getNameWhite().getText(),State.getInstance().getGuiRef().getNameBlack().getText(),1));
                    State.getInstance().getClient().printWriter.flush();
                }
                else if (this.mated(Color.BLACK)) {
                    State.getInstance().getClient().printWriter.println(Helpers.gameHistory(State.getInstance().getGame().getMoves(), State.getInstance().getGuiRef().getNameWhite().getText(),State.getInstance().getGuiRef().getNameBlack().getText(),2));
                    State.getInstance().getClient().printWriter.flush();
                }
            }
            counterHelper+=1;
            return true;
        } else {
            return false;
        }
    }


    /**
     * This method promotes pawn to chosen piece.
     *
     * @param pawn chosen pawn which has to be promoted
     *
     * @return demanded piece (result of promotion)
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    private IPiece PromoteTo(Pawn pawn) {
        String[] options = {"Rook", "Queen", "Bishop", "Knight"};
        int answer;
        if ((State.getInstance().isWhiteOnMove() && State.getInstance().getGame().getPlayerWhite() instanceof AiPlayer)
                || (!State.getInstance().isWhiteOnMove() && State.getInstance().getGame().getPlayerBlack() instanceof AiPlayer)) {
            answer = 1;
        } else {
            answer = JOptionPane.showOptionDialog(null, "Vyber si novou figurku",
                    "Click a button",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        }
        return switch (answer) {
            case 0 -> new Rook(pawn.getColor(), pawn.getX(), pawn.getY());
            case 1 -> new Queen(pawn.getColor(), pawn.getX(), pawn.getY());
            case 2 -> new Bishop(pawn.getColor(), pawn.getX(), pawn.getY());
            case 3 -> new Knight(pawn.getColor(), pawn.getX(), pawn.getY());
            default -> null;
        };
    }



    /**
     * This method gets piece of the required color on the board.
     *
     * @param color required color
     *
     * @return king of the required color
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public IPiece getKing(Color color) {
        if (color == Color.WHITE) {
            for (IPiece IPiece : this.getPieces(Color.WHITE)) {
                if (IPiece.getPoints() == Integer.MAX_VALUE) {
                    return IPiece;
                }
            }
        } else {
            for (IPiece IPiece : this.getPieces(Color.BLACK)) {
                if (IPiece.getPoints() == Integer.MAX_VALUE) {
                    return IPiece;
                }
            }
        }
        return null;
    }

    public void checkBoard() {
        if (blackInCheck()) {
            if (mated(Color.BLACK)) {
                JOptionPane.showMessageDialog(null, "Bilý vyhrál");
                State.getInstance().resetMove();
            } else {
                if (getKing(Color.BLACK).possibleMovement(this).isEmpty() && getPieces(Color.BLACK).size() == 1) {
                    JOptionPane.showMessageDialog(null, "Remíza");
                }
            }
        }
    }


    /**
     * This method removes piece from the list of pieces.
     *
     * @param IPiece piece which has to be removed
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void removePiece(IPiece IPiece) {
        if (IPiece.getColor() == Color.WHITE) {
            whiteIPieces.remove(IPiece);
        } else {
            blackIPieces.remove(IPiece);
        }
    }


    /**
     * This method refills pieces lists.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void refillPiecesLists() {
        ArrayList<IPiece> whiteIPieces = new ArrayList<>();
        ArrayList<IPiece> blackIPieces = new ArrayList<>();
        for (int r = 7; r >= 0; r--) {
            for (int f = 0; f < 8; f++) {
                IPiece IPiece = board[f][r].getPiece();
                if (IPiece != null) {
                    if (IPiece.getColor() == Color.WHITE) {
                        whiteIPieces.add(IPiece);
                    } else {
                        blackIPieces.add(IPiece);
                    }
                }
            }
        }
        this.whiteIPieces = whiteIPieces;
        this.blackIPieces = blackIPieces;
    }

    public ArrayList<Square> getEveryPossibleMoves(ArrayList<IPiece> IPieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (IPiece IPiece : IPieces) {
            moves.addAll(IPiece.possibleMovement(this));
        }
        return moves;
    }

    /**
     * This method gets all possible moves of all pieces on the board.
     *
     * @param IPieces pieces list, from which all moves have to be calculated
     *
     * @return all possible moves (their square representation)
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> getEveryPossibleMovesWithCover(ArrayList<IPiece> IPieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (IPiece IPiece : IPieces) {
            moves.addAll(IPiece.getAttackMovesForKingMove(this));
        }
        return moves;
    }

    /**
     * This method gets all xray moves of all pieces on the board.
     *
     * @param IPieces pieces list, from which all xray moves have to be calculated
     *
     * @return all possible xray moves (their square representation)
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> getEveryXRayMove(ArrayList<IPiece> IPieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (IPiece IPiece : IPieces) {
            if (IPiece instanceof Queen) {
                moves.addAll(((Queen) IPiece).getXRayedMoves(this));
            }
            if (IPiece instanceof Rook) {
                moves.addAll(((Rook) IPiece).getXRayedMoves(this));
            } else if (IPiece instanceof Bishop) {
                moves.addAll(((Bishop) IPiece).getXRayedMoves(this));
            }

        }
        return moves;
    }


    /**
     * This method gets squares which have to be blocked to avoid check.
     *
     * @param IPieces pieces list, from which all squares to block have to be calculated
     *
     * @return all squares which have to be blocked (their square representation)
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> getSquaresToBlock(ArrayList<IPiece> IPieces) {
        ArrayList<Square> squaresToBlock = new ArrayList<>();
        Square kingSquare = this.board[getKing(Helpers.getOtherColor((IPieces.get(0)).getColor())).getX()][getKing(Helpers.getOtherColor((IPieces.get(0)).getColor())).getY()];
        for (IPiece IPiece : IPieces) {
            if (IPiece instanceof Knight || IPiece instanceof Pawn) {
                if (IPiece.possibleMovement(this).contains(kingSquare)) {
                    squaresToBlock.add(this.board[IPiece.getX()][IPiece.getY()]);
                }
            } else if (IPiece instanceof Bishop) {
                if (IPiece.possibleMovement(this).contains(kingSquare) || ((Bishop) IPiece).getXRayedMoves(this).size() != 0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(IPiece.getColor())).getX(), getKing(Helpers.getOtherColor(IPiece.getColor())).getY()};
                    int[] bishopPosition = {IPiece.getX(), IPiece.getY()};
                    int x = IPiece.getX();
                    int y = IPiece.getY();
                    if (kingPosition[0] > bishopPosition[0] && kingPosition[1] > bishopPosition[1]) {

                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                            y++;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    } else if (kingPosition[0] < bishopPosition[0] && kingPosition[1] > bishopPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                            y++;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);

                    } else if (kingPosition[0] < bishopPosition[0] && kingPosition[1] < bishopPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                            y--;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    } else {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                            y--;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    }
                }
            } else if (IPiece instanceof Rook) {
                if (IPiece.possibleMovement(this).contains(kingSquare) || ((Rook) IPiece).getXRayedMoves(this).size() != 0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(IPiece.getColor())).getX(), getKing(Helpers.getOtherColor(IPiece.getColor())).getY()};
                    int[] rookPosition = {IPiece.getX(), IPiece.getY()};
                    int x = IPiece.getX();
                    int y = IPiece.getY();
                    if (kingPosition[0] == rookPosition[0] && kingPosition[1] > rookPosition[1]) {

                        do {
                            squaresToBlock.add(this.board[x][y]);
                            y++;
                        }
                        while (x == kingPosition[0] && y != kingPosition[1]);
                    } else if (kingPosition[0] < rookPosition[0] && kingPosition[1] == rookPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                        }
                        while (x != kingPosition[0] && y == kingPosition[1]);

                    } else if (kingPosition[0] == rookPosition[0] && kingPosition[1] < rookPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            y--;
                        }
                        while (x == kingPosition[0] && y != kingPosition[1]);
                    } else {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                        }
                        while (x != kingPosition[0] && y == kingPosition[1]);
                    }
                }

            } else if (IPiece instanceof Queen) {
                if (IPiece.possibleMovement(this).contains(kingSquare) || ((Queen) IPiece).getXRayedMoves(this).size() != 0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(IPiece.getColor())).getX(), getKing(Helpers.getOtherColor(IPiece.getColor())).getY()};
                    int[] queenPosition = {IPiece.getX(), IPiece.getY()};
                    int x = IPiece.getX();
                    int y = IPiece.getY();
                    if (kingPosition[0] == queenPosition[0] && kingPosition[1] > queenPosition[1]) {

                        do {
                            squaresToBlock.add(this.board[x][y]);
                            y++;
                        }
                        while (x == kingPosition[0] && y != kingPosition[1]);
                    } else if (kingPosition[0] < queenPosition[0] && kingPosition[1] == queenPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                        }
                        while (x != kingPosition[0] && y == kingPosition[1]);

                    } else if (kingPosition[0] == queenPosition[0] && kingPosition[1] < queenPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            y--;
                        }
                        while (x == kingPosition[0] && y != kingPosition[1]);
                    } else if (kingPosition[0] > queenPosition[0] && kingPosition[1] == queenPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                        }
                        while (x != kingPosition[0] && y == kingPosition[1]);
                    } else if (kingPosition[0] > queenPosition[0] && kingPosition[1] > queenPosition[1]) {

                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                            y++;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    } else if (kingPosition[0] < queenPosition[0] && kingPosition[1] > queenPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                            y++;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);

                    } else if (kingPosition[0] < queenPosition[0] && kingPosition[1] < queenPosition[1]) {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x--;
                            y--;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    } else {
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                            y--;
                        }
                        while (x != kingPosition[0] && y != kingPosition[1]);
                    }
                }
            }
        }
        return squaresToBlock;
    }

    /**
     * This method checks if white king is in check
     *
     * @return true if white king is in check
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean whiteInCheck() {
        IPiece king = getKing(Color.WHITE);
        return getEveryPossibleMoves(this.getPieces(Color.BLACK)).contains(getBoard()[king.getX()][king.getY()]);
    }

    /**
     * This method checks if black king is in check
     *
     * @return true if black king is in check
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean blackInCheck() {
        IPiece king = getKing(Color.BLACK);
        return getEveryPossibleMoves(this.getPieces(Color.WHITE)).contains(getBoard()[king.getX()][king.getY()]);
    }


    /**
     * This method checks if the given color is mated
     *
     * @return true if the given color king is mated
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean mated(Color color) {
        if (color == Color.WHITE) {
            for (IPiece IPiece : whiteIPieces) {
                if (canBlockOrEscapeFromCheck(IPiece)) {
                    return false;
                }
            }
            return whiteInCheck();
        } else {
            for (IPiece IPiece : blackIPieces) {
                if (canBlockOrEscapeFromCheck(IPiece)) {
                    return false;
                }
            }
            return blackInCheck();
        }
    }

    /**
     * This method returns notation of the game.
     *
     * @return string of the whole notation
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String getNotation(){
        return notation;
    }

    /**
     * This method adds move to notation of the game.
     *
     * @param move performed move
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void addMoveToNotation(String move){
        int oldCounter = counter;
        if(counterHelper!= 0 && counterHelper % 2 == 0){
            counter += 1;
            notation += "\n";
            counterHelper = 0;
        }
        if(addFirstNotation || counter != oldCounter ){
            notation += counter+". "+move;
            addFirstNotation = false;
        }
        else {
            notation += " "+move+" ";
        }

    }

    public boolean inCheck() {
        if (blackInCheck()) {
            return true;
        }
        if (whiteInCheck()) {
            return true;
        }
        return false;
    }


    /**
     * This method checks if the given color on the given position will be checked on the given coordinates.
     *
     * @param color given color
     * @param x x position to check if it will be checked
     * @param y x position to check if it will be checked
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean willBeChecked(Color color,int x, int y) {
        if (color == Color.WHITE) {
            IPiece king = getKing(Color.WHITE);
            return getEveryPossibleMovesWithCover(this.getPieces(Color.BLACK)).contains(getBoard()[x][y]);
        }
        else {
            IPiece king = getKing(Color.BLACK);
            return getEveryPossibleMovesWithCover(this.getPieces(Color.WHITE)).contains(getBoard()[x][y]);
        }
    }

    /**
     * This method converts board to String.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public String boardToString() {
        StringBuilder game = new StringBuilder();
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                IPiece IPiece = this.board[j][i].getPiece();
                if (IPiece == null) {
                    game.append(" ");
                }
                else {
                    game.append(IPiece);
                }
                game.append(",");
            }
        }
        game.deleteCharAt(game.length() - 1);
        return game.toString();
    }

    /**
     * This method checks if piece can save king from check or if king could escape.
     *
     * @return true if the conditions mentioned above are fulfilled
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */

    public boolean canBlockOrEscapeFromCheck(IPiece IPiece){
        if(IPiece instanceof King){
            if (IPiece.possibleMovement(this).isEmpty()){
                return false;
            }
            return true;
        }
        else {
            ArrayList<Square> squaresToBlock = getSquaresToBlock(this.getPieces(Helpers.getOtherColor(IPiece.getColor())));
            ArrayList<Square> piecePossibleMovements = IPiece.possibleMovement(this);
            if (!Collections.disjoint(squaresToBlock, piecePossibleMovements)){
                return true;
            }
            return false;
        }
    }


    /**
     * This method returns all possible moves of piece to uncheck the king.
     *
     * @param IPiece given piece to check its possible moves to uncheck
     *
     * @return all possible moves of piece to uncheck the king.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public ArrayList<Square> possibleMovesToUncheck(IPiece IPiece){
        ArrayList<Square> squaresToBlock = getSquaresToBlock(this.getPieces(Helpers.getOtherColor(IPiece.getColor())));
        ArrayList<Square> piecePossibleMovements = IPiece.possibleMovement(this);
        return (ArrayList<Square>) Helpers.intersection(squaresToBlock, piecePossibleMovements);
    }


    /**
     * This method checks if the game is drawn.
     *
     * @return true if all conditions all fulfilled
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean isDraw() {
        if (this.getKing(Color.WHITE).possibleMovement(this).isEmpty() && this.getPieces(Color.WHITE).size() == 1) {
            return true;
        } else if (this.getKing(Color.BLACK).possibleMovement(this).isEmpty() && this.getPieces(Color.BLACK).size() == 1) {
            return true;
        } else if ((isThereOnlyKingOnTheBoard(Color.WHITE) || isThereOnlyKingAndKnightOnTheBoard(Color.WHITE)
                || isThereOnlyKingAndBishopOnTheBoard(Color.WHITE)) &&
                (isThereOnlyKingOnTheBoard(Color.BLACK) || isThereOnlyKingAndKnightOnTheBoard(Color.BLACK)
                || isThereOnlyKingAndBishopOnTheBoard(Color.BLACK))){
            return true;
        }
        return false;
    }

    /**
     * This method checks if there is only king of the given color left on the board.
     *
     * @return true if there is only king
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean isThereOnlyKingOnTheBoard(Color color){
        if(this.getPieces(color).size() == 1 && this.getPieces(color).contains(getKing(color))){
            return true;
        }
        else {
            return false;
        }
    }



    /**
     * This method checks if there is only king and knight of the given color left on the board.
     *
     * @return true if there is only king and one knight
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean isThereOnlyKingAndKnightOnTheBoard(Color color){
        if(this.getPieces(color).size() == 2 && this.getPieces(color).contains(getKing(color))) {
            int countOfKnights = 0;
            for (IPiece piece: this.getPieces(color)) {
                if(piece instanceof Knight){
                    countOfKnights += 1;
                    if (countOfKnights == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * This method checks if there is only king and bishop of the given color left on the board.
     *
     * @return true if there is only king and one bishop
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public boolean isThereOnlyKingAndBishopOnTheBoard(Color color){
        if(this.getPieces(color).size() == 2 && this.getPieces(color).contains(getKing(color))) {
            int countOfBishops = 0;
            for (IPiece piece: this.getPieces(color)) {
                if(piece instanceof Bishop){
                    countOfBishops += 1;
                    if (countOfBishops == 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method is used for count of moves in the game.
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public void addCounterHelper(){
        counterHelper+=1;
    }

    /**
     * This method returns type of game.
     *
     * @return type of the played game
     *
     * @author Tomas Kloucek
     * @author Vladyslav Babyc
     *
     */
    public GameType getType() {
        return type;
    }
}
