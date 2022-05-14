package unit;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import cz.cvut.fel.pjv.models.Square;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import cz.cvut.fel.pjv.models.pieces.Pawn;
import cz.cvut.fel.pjv.models.pieces.Rook;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
public class UnitTests {
    @Test
    public void FormatTime_FormatTimeWithAddedZero_FormattedTime() {
        //ARRANGE
        String expectedFormattedTime = "09:04";
        long minutes = 9;
        long seconds = 4;
        //ACT
        String result = Helpers.formatTime(minutes, seconds);
        //ASSERT
        Assertions.assertEquals(expectedFormattedTime, result);
    }
    @Test
    public void FormatTime_FormatTimeWithoutAddedZero_FormattedTime() {
        //ARRANGE
        String expectedFormattedTime = "10:11";
        long minutes = 10;
        long seconds = 11;
        //ACT
        String result = Helpers.formatTime(minutes, seconds);
        //ASSERT
        Assertions.assertEquals(expectedFormattedTime, result);
    }
    @Test
    public void InitializeBoard_InitializeNewBoard_NewBoardInitialized(){
        //ARRANGE
        Board board = new Board(GameType.PVP);
        int expectedCountOfWhitePieces = 16;
        int expectedCountOfBlackPieces = 16;
        int expectedPointsOnSquareX3Y0 = 9;
        int expectedPointsOnSquareX5Y7 = 3;

        //ACT
        board.initializeBoard();

        //ASSERT
        int resultCountOfWhitePieces = board.getPieces(Color.WHITE).size();
        int resultCountOfBlackPieces = board.getPieces(Color.BLACK).size();
        int resultPointsOnSquareX3Y0 = board.getBoard()[3][0].getPiece().getPoints();
        int resultPointsOnSquareX5Y7 = board.getBoard()[5][7].getPiece().getPoints();

        Assertions.assertEquals(expectedCountOfWhitePieces, resultCountOfWhitePieces);
        Assertions.assertEquals(expectedCountOfBlackPieces, resultCountOfBlackPieces);
        Assertions.assertEquals(expectedPointsOnSquareX3Y0, resultPointsOnSquareX3Y0);
        Assertions.assertEquals(expectedPointsOnSquareX5Y7, resultPointsOnSquareX5Y7);

    }
    public void PieceFromString() {
    }

    public void boardToString() {
    }

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

    @Test
    public void SetMotionToPawns_SetMotionToMovedPawns_MotionSet() {
        //ARRANGE
        ArrayList<IPiece> listWithPawns = new ArrayList<>();
        listWithPawns.add(new Pawn(Color.WHITE, 2, 0));
        IPiece chosen = new Rook(Color.WHITE, 7, 0);
        Board mockedBoard = mock(Board.class);
        boolean expectedResult = false;
        //ACT
        mockedBoard.setMotionToPawns(listWithPawns, chosen);
        //ASSERT
        Pawn evaluatedPawn = (Pawn) listWithPawns.get(0);
        boolean result = evaluatedPawn.getMovedTwoSquares();
        Assertions.assertEquals(expectedResult, result);
    }
        public void putPiece() {
    }


    public void generateAllPossibleWays(int x, int y) {
    }

    public void getPieceImage(Square square, Board board) {
    }

}