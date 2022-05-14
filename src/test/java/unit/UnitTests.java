package unit;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import cz.cvut.fel.pjv.models.Square;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import cz.cvut.fel.pjv.models.pieces.Pawn;
import cz.cvut.fel.pjv.models.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

public class UnitTests {
    public void FormatTimeTest() {
    }
    @ParameterizedTest(name = "Vytvoreni figurky se souradnicemi x:{1} a y:{2}")
    @CsvSource({"RBa3,0,2,BLACK","Wb5,1,4,WHITE"})
    public void pieceFromStringBasic_Test(String piece, int x, int y, String color) {
        Board board = new Board(GameType.PVP);

        IPiece figure = board.pieceFromString(piece);
        if (figure instanceof Rook) {
            Rook rook = new Rook(Color.valueOf(color),x,y);
            Assertions.assertEquals(rook.toString(),figure.toString());
        }
        if (figure instanceof Pawn) {
            Pawn pawn = new Pawn(Color.valueOf(color),x,y);
            Assertions.assertEquals(pawn.toString(),figure.toString());
        }
    }

    @ParameterizedTest(name = "Vytvoreni figurky s nesmyslne zadanym stringem vrati null")
    @CsvSource({"RBx9","null"})
    public void pieceFromStringWithDangerousInputs_Test(String piece) {
        Board board = new Board(GameType.PVP);

        IPiece figure = board.pieceFromString(piece);

        Assertions.assertEquals(null, figure);
    }

    public void setMotionToPawns(ArrayList<IPiece> IPieces, IPiece chosen) {
    }

    // Mocked tests
    public void putPiece() {
    }

    public void generateAllPossibleWays(int x, int y) {
    }

    public void getPieceImage(Square square, Board board) {
    }
}
