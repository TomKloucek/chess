package unit;

import cz.cvut.fel.pjv.helpers.Helpers;
import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import cz.cvut.fel.pjv.models.pieces.King;
import cz.cvut.fel.pjv.models.pieces.Knight;
import cz.cvut.fel.pjv.models.pieces.Pawn;
import cz.cvut.fel.pjv.models.pieces.Rook;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedConstruction;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
public class UnitTests {

    @Test
    public void ReverseMove_WhiteOnMove_BlackOnMove(){
        //ARRANGE
        Board board = new Board(GameType.PVP);
        Player p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
        Player p2 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
        Game game = new Game(p1, p2, board);
        State.getInstance().setGame(new Game(p1, p2, board));

        //ACT && ASSERT
        boolean expectedWhiteOnMove = true;
        boolean resultOnMove = State.getInstance().isWhiteOnMove();
        Assertions.assertEquals(expectedWhiteOnMove, resultOnMove);

        State.getInstance().reverseMove();

        expectedWhiteOnMove = false;
        resultOnMove = State.getInstance().isWhiteOnMove();
        Assertions.assertEquals(expectedWhiteOnMove, resultOnMove);
    }

    @Test
    public void StringToBoard_ConversionStringToBoard_Board(){
        //ARRANGE
        Board board = new Board(GameType.PVP);
        String boardString = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";

        //ACT
        board.stringToBoard(boardString, false);
        IPiece expectedPieceOnX2Y3 = new Pawn(Color.WHITE, 2, 1);
        IPiece resultPieceOnX2Y3 = board.getBoard()[2][1].getPiece();
        //ASSERT
        Assertions.assertEquals(expectedPieceOnX2Y3.toString(), resultPieceOnX2Y3.toString());
    }
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
    @CsvSource({"-5,0","6,5","1,6"})
    public void checkSquareCoordinates_Test(int addition, int expected){
        int i = addition;
        int x = 5;
        int y = 5;

        Assertions.assertEquals(expected,Helpers.checkSquareCoordinates(x,i, y,i, "x"));
    }


    // Mocked tests

    @Test
    public void InitializeBoard_InitializeNewEditor(){
        //ARRANGE
        Board board = new Board(GameType.PVP);
        int expectedCountOfWhitePieces = 0;
        int expectedCountOfBlackPieces = 0;

        //ACT
        try (MockedConstruction<Pawn> pawnsExpected = mockConstruction(Pawn.class)) {
            // ACT
            board.initializeEditor();

            // ASSERT
            Assertions.assertEquals(0,pawnsExpected.constructed().size());
        }
        try (MockedConstruction<King> kingsExpected = mockConstruction(King.class)) {
            // ACT
            board.initializeEditor();

            // ASSERT
            Assertions.assertEquals(0,kingsExpected.constructed().size());
        }

        //ASSERT
        int resultCountOfWhitePieces = board.getPieces(Color.WHITE).size();
        int resultCountOfBlackPieces = board.getPieces(Color.BLACK).size();

        Assertions.assertEquals(expectedCountOfWhitePieces, resultCountOfWhitePieces);
        Assertions.assertEquals(expectedCountOfBlackPieces, resultCountOfBlackPieces);
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
        try (MockedConstruction<Pawn> pawnsExpected = mockConstruction(Pawn.class)) {
            // ACT
            board.initializeBoard();

            // ASSERT
            Assertions.assertEquals(16,pawnsExpected.constructed().size());
        }


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

    @ParameterizedTest(name = "Vytvoreni figurky s nesmyslne zadanym stringem vrati null")
    @CsvSource({"RBx11","RBa9","RBk5"})
    public void pieceFromStringWithDangerousInputs_Test(String piece) {
        Board board = new Board(GameType.PVP);

        try (MockedConstruction<Rook> rooksExpected = mockConstruction(Rook.class)) {
            // ACT
            IPiece figure = board.pieceFromString(piece);

            // ASSERT
            Assertions.assertEquals(0,rooksExpected.constructed().size());
        }

    }


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
        verify(mockedBoard, times(1)).setMotionToPawns(listWithPawns, chosen);
    }
    @Test
    public void PieceFromString_KnightOnX6Y4_TranslatedPiece() {
        //ARRANGE
        Board mockedBoard = mock(Board.class);
        Knight expectedKnight = new Knight(Color.WHITE, 6, 4);
        when(mockedBoard.pieceFromString("NWg3")).thenReturn(expectedKnight);
        //ACT
        Knight resultKnight = (Knight)mockedBoard.pieceFromString("NWg3");
        //ASSERT
        Assertions.assertEquals(expectedKnight, resultKnight);
        verify(mockedBoard, times(1)).pieceFromString("NWg3");
    }


}