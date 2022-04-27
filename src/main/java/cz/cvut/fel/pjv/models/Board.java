package cz.cvut.fel.pjv.models;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.pieces.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private Square[][] board;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    public Board() {
        this.board = new Square[8][8];
        this.blackPieces = new ArrayList<>();
        this.whitePieces = new ArrayList<>();
    }

    public ArrayList<Piece> getPieces(Color color) {
        if (color == Color.WHITE) {
            return whitePieces;
        }
        else {
            return blackPieces;
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public void initializeEditor() {
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j,i,null);
            }
        }
    }

    public void initializeBoard(){

        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j,i,null);
            }
        }

        // WHITE PLAYER
        putPiece(0,0,"Rook",Color.WHITE);
        putPiece(1,0,"Knight",Color.WHITE);
        putPiece(2,0,"Bishop",Color.WHITE);
        putPiece(3,0,"Queen",Color.WHITE);
        putPiece(4,0,"King",Color.WHITE);
        putPiece(5,0,"Bishop",Color.WHITE);
        putPiece(6,0,"Knight",Color.WHITE);
        putPiece(7,0,"Rook",Color.WHITE);

        for (int i = 0; i < 8; i++) {
            putPiece(i,1,"Pawn",Color.WHITE);
        }

        // BLACK PLAYER
        for (int i = 0; i < 8; i++) {
            putPiece(i,6,"Pawn",Color.BLACK);
        }

        putPiece(0,7,"Rook",Color.BLACK);
        putPiece(1,7,"Knight",Color.BLACK);
        putPiece(2,7,"Bishop",Color.BLACK);
        putPiece(3,7,"Queen",Color.BLACK);
        putPiece(4,7,"King",Color.BLACK);
        putPiece(5,7,"Bishop",Color.BLACK);
        putPiece(6,7,"Knight",Color.BLACK);
        putPiece(7,7,"Rook",Color.BLACK);
    }

    public void printBoard(){
        System.out.println("---------------------------------------------------------");
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i].getPiece() != null) {
                    if (j == 0) {
                        System.out.print("| "+board[j][i].getPiece()+" |");
                    }
                    else {
                        System.out.print(" "+board[j][i].getPiece()+" |");
                    }
                }
                else {
                    if (j == 0) {
                        System.out.print("|     |");
                    }
                    else {
                        System.out.print("      |");
                    }
                }
            }
            System.out.println("\n---------------------------------------------------------");
        }
    }

    public void putPiece(int x, int y, String type, Color color) {
        Piece piece = switch (type) {
            case "Pawn" -> new Pawn(color, x, y);
            case "Knight" -> new Knight(color, x, y);
            case "Queen" -> new Queen(color, x, y);
            case "King" -> new King(color, x, y);
            case "Rook" -> new Rook(color, x, y);
            case "Bishop" -> new Bishop(color, x, y);
            default -> null;
        };
        if (color == Color.WHITE) {
            whitePieces.add(piece);
        }
        else {
            blackPieces.add(piece);
        }
        board[x][y].setPiece(piece);
    }

    public void putPiece(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            whitePieces.add(piece);
        }
        else {
            blackPieces.add(piece);
        }
    }

    public Piece pickPiece(int x, int y) {
        if (this.board[x][y].getPiece() == null){
            return null;
        }
        else {
            return this.board[x][y].getPiece();
        }
    }

    public void stringToBoard(String s) {
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                board[j][i] = new Square(j,i,null);
            }
        }
        int counter = 0;
        String[] boardArray = s.split(",");
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = pieceFromString(boardArray[counter]);
                if (piece != null) {
                    putPiece(piece);
                }
                board[j][i].setPiece(piece);
                counter++;
            }
        }
    }

    public Piece pieceFromString(String piece) {
        piece = piece.trim();
        char[] chars = piece.toCharArray();
        if (chars.length == 0) {
            return null;
        }
        if (chars.length == 3) {
            int y = Character.getNumericValue(chars[2])-1;
            if (chars[0] == 'W') {
                return new Pawn(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[1])),y,true);
            }
            else {
                return new Pawn(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[1])),y,true);
            }
        }
        else if ((chars[0] == 'W' || chars[0] == 'B') && chars[3] == 'X') {
            int y = Character.getNumericValue(chars[2])-1;
            if (chars[0] == 'W') {
                return new Pawn(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[1])),y,false);
            }
            else {
                return new Pawn(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[1])),y,false);
            }
        }
        else {
            int y = Character.getNumericValue(chars[3])-1;
            if (chars[0] == 'K') {
                if (chars[1] == 'W') {
                    return new King(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
                else {
                    return new King(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
            }
            else if (chars[0] == 'R') {
                if (chars[1] == 'W') {
                    return new Rook(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
                else {
                    return new Rook(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
            }
            else if (chars[0] == 'Q') {
                if (chars[1] == 'W') {
                    return new Queen(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
                else {
                    return new Rook(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
            }
            else if (chars[0] == 'N') {
                if (chars[1] == 'W') {
                    return new Knight(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
                else {
                    return new Knight(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
            }
            else if (chars[0] == 'B') {
                if (chars[1] == 'W') {
                    return new Bishop(Color.WHITE, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
                else {
                    return new Bishop(Color.BLACK, Helpers.XTranslateBack(String.valueOf(chars[2])),y);
                }
            }
        }
        return null;
    }

    public void setMotionToPawns(ArrayList<Piece> pieces, Piece chosen) {
        for (Piece piece: pieces) {
            if (chosen != piece && piece instanceof Pawn){
                ((Pawn) piece).movedTwoSquares = false;
            }
        }
    }
    public boolean movePiece(Piece chosen, int x, int y) {
        setMotionToPawns(this.getPieces(chosen.getColor()), chosen);
        if (chosen.possibleMovement(this).contains(board[x][y])) {
            if ((y == 0 || y == 7) && chosen instanceof Pawn) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                if (y == 0 && chosen.getColor() == Color.BLACK) {
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
                    board[x][y].getPiece().Move(x,y);
                    blackPieces.remove(chosen);
                    blackPieces.add(board[x][y].getPiece());
                    refillPiecesLists();
                }
                if (y == 7 && chosen.getColor() == Color.WHITE) {
                    this.board[x][y].setPiece(null);
                    board[x][y].setPiece(PromoteTo((Pawn) chosen));
                    this.board[x][y].getPiece().Move(x,y);
                    whitePieces.remove(chosen);
                    whitePieces.add(board[x][y].getPiece());
                    refillPiecesLists();
                }
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
            }
            // EN PASSANT
            else if(chosen instanceof Pawn && (board[x][y-1].getPiece() instanceof Pawn && board[x][y-1].getPiece().getColor() == Color.BLACK) && board[x][y].getPiece() == null) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y-1].setPiece(null);
                this.board[x][y].setPiece(chosen);
                board[x][y].getPiece().Move(x,y);
                refillPiecesLists();
                return true;
            }
            else if(chosen instanceof Pawn && (board[x][y+1].getPiece() instanceof Pawn && board[x][y+1].getPiece().getColor() == Color.WHITE) && board[x][y].getPiece() == null) {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y+1].setPiece(null);
                this.board[x][y].setPiece(chosen);
                board[x][y].getPiece().Move(x,y);
                refillPiecesLists();
                return true;
            }
            // CASTLE
            else if (chosen instanceof King && board[x][y].getPiece() instanceof Rook && board[x][y].getPiece().getColor()==chosen.getColor()) {
                if (x == 0 && y == 0) {
                    System.out.println("1");
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook )this.board[0][0].getPiece();
                    this.board[0][0].setPiece(null);
                    this.board[3][0].setPiece(rook);
                    this.board[2][0].setPiece(chosen);
                    rook.Move(3,0);
                    chosen.Move(2,0);
                }
                if (x == 7 && y == 0) {
                    System.out.println("2");
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook )this.board[7][0].getPiece();
                    this.board[7][0].setPiece(null);
                    this.board[5][0].setPiece(rook);
                    this.board[6][0].setPiece(chosen);
                    rook.Move(5,0);
                    chosen.Move(6,0);
                }
                if (x == 0 && y == 7) {
                    System.out.println("3");
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook )this.board[0][7].getPiece();
                    this.board[0][7].setPiece(null);
                    this.board[3][7].setPiece(rook);
                    this.board[2][7].setPiece(chosen);
                    rook.Move(3,7);
                    chosen.Move(2,7);
                }
                if (x == 7 && y == 7) {
                    System.out.println("4");
                    this.board[chosen.getX()][chosen.getY()].setPiece(null);
                    Rook rook = (Rook )this.board[7][7].getPiece();
                    this.board[7][7].setPiece(null);
                    this.board[5][7].setPiece(rook);
                    this.board[6][7].setPiece(chosen);
                    rook.Move(5,7);
                    chosen.Move(6,7);
                }
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
            }
            else {
                this.board[chosen.getX()][chosen.getY()].setPiece(null);
                this.board[x][y].setPiece(chosen);
                chosen.Move(x, y);
                refillPiecesLists();
                State.getInstance().reverseMove();
                State.getInstance().getGame().playForAi();
                return true;
            }
        }
        else {
            System.out.println("Nezadal jsi spravne hodnoty");
            return false;
        }
        return false;
    }

    private Piece PromoteTo(Pawn pawn) {
        String[] options = {"Rook", "Queen", "Bishop", "Knight"};
        int answer;
        if((State.getInstance().isWhiteOnMove() && State.getInstance().getGame().getPlayerWhite() instanceof AiPlayer)
        || (!State.getInstance().isWhiteOnMove() && State.getInstance().getGame().getPlayerBlack() instanceof AiPlayer)){
            answer = 1;
        }

        else {
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

    public Piece getKing(Color color) {
        if (color == Color.WHITE) {
            for (Piece piece: this.getPieces(Color.WHITE)) {
                if (piece.getPoints() == Integer.MAX_VALUE) {
                    return piece;
                }
            }
        }
        else {
            for (Piece piece: this.getPieces(Color.BLACK)) {
                if (piece.getPoints() == Integer.MAX_VALUE) {
                    return piece;
                }
            }
        }
        return null;
    }

    public void refillPiecesLists() {
        ArrayList<Piece> whitePieces = new ArrayList<>();
        ArrayList<Piece> blackPieces = new ArrayList<>();
        for (int r = 7; r >= 0; r --) {
            for (int f = 0; f < 8; f++) {
                Piece piece = board[f][r].getPiece();
                if (piece != null) {
                    if (piece.getColor() == Color.WHITE) {
                        whitePieces.add(piece);
                    }
                    else {
                        blackPieces.add(piece);
                    }
                }
            }
        }
        this.whitePieces = whitePieces;
        this.blackPieces = blackPieces;
    }

    public ArrayList<Square> getEveryPossibleMoves(ArrayList<Piece> pieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (Piece piece : pieces) {
            moves.addAll(piece.possibleMovement(this));
        }
        return moves;
    }
    public ArrayList<Square> getEveryPossibleMovesWithCover(ArrayList<Piece> pieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (Piece piece : pieces) {
            moves.addAll(piece.getAttackMovesForKingMove(this));
        }
        return moves;
    }
    public ArrayList<Square> getEveryXRayMove(ArrayList<Piece> pieces) {
        ArrayList<Square> moves = new ArrayList<>();
        for (Piece piece : pieces) {
            if (piece instanceof Queen){
                moves.addAll(((Queen) piece).getXRayedMoves(this));
            }
            if (piece instanceof Rook){
                moves.addAll(((Rook) piece).getXRayedMoves(this));
            }
            else if(piece instanceof Bishop){
                moves.addAll(((Bishop) piece).getXRayedMoves(this));
            }

        }
        return moves;
    }

    public ArrayList<Square> getSquaresToBlock(ArrayList<Piece> pieces) {
        ArrayList<Square> squaresToBlock = new ArrayList<>();
        Square kingSquare = this.board[getKing(Helpers.getOtherColor((pieces.get(0)).getColor())).getX()][getKing(Helpers.getOtherColor((pieces.get(0)).getColor())).getY()];
        for (Piece piece: pieces) {
            if (piece instanceof Knight || piece instanceof Pawn) {
                if (piece.possibleMovement(this).contains(kingSquare)) {
                    squaresToBlock.add(this.board[piece.getX()][piece.getY()]);
                }
            } else if (piece instanceof Bishop) {
                if (piece.possibleMovement(this).contains(kingSquare) || ((Bishop) piece).getXRayedMoves(this).size() !=0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(piece.getColor())).getX(), getKing(Helpers.getOtherColor(piece.getColor())).getY()};
                    int[] bishopPosition = {piece.getX(), piece.getY()};
                    int x = piece.getX();
                    int y = piece.getY();
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
            } else if (piece instanceof Rook) {
                if (piece.possibleMovement(this).contains(kingSquare) || ((Rook) piece).getXRayedMoves(this).size() !=0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(piece.getColor())).getX(), getKing(Helpers.getOtherColor(piece.getColor())).getY()};
                    int[] rookPosition = {piece.getX(), piece.getY()};
                    int x = piece.getX();
                    int y = piece.getY();
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

            }
            else if (piece instanceof Queen){
                if (piece.possibleMovement(this).contains(kingSquare) || ((Queen) piece).getXRayedMoves(this).size() !=0) {
                    int[] kingPosition = {getKing(Helpers.getOtherColor(piece.getColor())).getX(), getKing(Helpers.getOtherColor(piece.getColor())).getY()};
                    int[] queenPosition = {piece.getX(), piece.getY()};
                    int x = piece.getX();
                    int y = piece.getY();
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
                    }
                    else if (kingPosition[0] > queenPosition[0] && kingPosition[1] == queenPosition[1]){
                        do {
                            squaresToBlock.add(this.board[x][y]);
                            x++;
                        }
                        while (x != kingPosition[0] && y == kingPosition[1]);
                    }
                    else if (kingPosition[0] > queenPosition[0] && kingPosition[1] > queenPosition[1]) {

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



    public boolean whiteInCheck() {
        Piece king = getKing(Color.WHITE);
        return getEveryPossibleMoves(this.getPieces(Color.BLACK)).contains(getBoard()[king.getX()][king.getY()]);
    }

    public boolean blackInCheck() {
        Piece king = getKing(Color.BLACK);
        return getEveryPossibleMoves(this.getPieces(Color.WHITE)).contains(getBoard()[king.getX()][king.getY()]);
    }

    public boolean Mated(Color color) {
        if (color == Color.WHITE) {
            for (Piece piece : whitePieces) {
                if (canBlockOrEscapeFromCheck(piece)) {
                    return false;
                }
            }
        }
        else {
            for (Piece piece : blackPieces) {
                if (canBlockOrEscapeFromCheck(piece)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean inCheck() {
        if (blackInCheck()) {
            System.out.println("Cerny je v checku");
            return true;
        }
        if (whiteInCheck()) {
            System.out.println("Bily je v checku");
            return true;
        }
        return false;
    }

    public boolean willBeChecked(Color color,int x, int y) {
        if (color == Color.WHITE) {
            Piece king = getKing(Color.WHITE);
            System.out.println(getEveryPossibleMovesWithCover(this.getPieces(Color.BLACK)));
            return getEveryPossibleMovesWithCover(this.getPieces(Color.BLACK)).contains(getBoard()[x][y]);
        }
        else {
            Piece king = getKing(Color.BLACK);
            System.out.println(getEveryPossibleMovesWithCover(this.getPieces(Color.WHITE)));
            return getEveryPossibleMovesWithCover(this.getPieces(Color.WHITE)).contains(getBoard()[x][y]);
        }
    }

    public String boardToString() {
        StringBuilder game = new StringBuilder();
        for (int i = 7; i > -1; i--) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.board[j][i].getPiece();
                if (piece == null) {
                    game.append(" ");
                }
                else {
                    game.append(piece);
                }
                game.append(",");
            }
        }
        game.deleteCharAt(game.length() - 1);
        return game.toString();
    }

    public boolean canBlockOrEscapeFromCheck(Piece piece){
        if(piece instanceof King){
            if (piece.possibleMovement(this).isEmpty()){
                return false;
            }
            return true;
        }
        else {
            ArrayList<Square> squaresToBlock = getSquaresToBlock(this.getPieces(Helpers.getOtherColor(piece.getColor())));
            ArrayList<Square> piecePossibleMovements = piece.possibleMovement(this);
            if (!Collections.disjoint(squaresToBlock, piecePossibleMovements)){
                return true;
            }
            return false;
        }
    }
    public ArrayList<Square> possibleMovesToUncheck(Piece piece){
        ArrayList<Square> squaresToBlock = getSquaresToBlock(this.getPieces(Helpers.getOtherColor(piece.getColor())));
        ArrayList<Square> piecePossibleMovements = piece.possibleMovement(this);
        return (ArrayList<Square>) Helpers.intersection(squaresToBlock, piecePossibleMovements);
    }
}
