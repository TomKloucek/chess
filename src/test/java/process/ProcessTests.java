package process;

import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.pieces.Pawn;
import cz.cvut.fel.pjv.models.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProcessTests {
    @Test
    public void PutPiece_PutWhiteKingToSquareX3Y4_KingOnBoard() {
        //ARRANGE
        int expectedX = 3;
        int expectedY = 4;
        String expectedType = "King";
        Integer expectedPoints = Integer.MAX_VALUE;
        Color expectedColor = Color.WHITE;

        Board board = new Board(GameType.PVP);
        board.initializeBoard();

        //ACT
        board.putPiece(expectedX, expectedY, expectedType, expectedColor);
        ArrayList<IPiece> whitePieces = board.getPieces(Color.WHITE);
        //ASSERT
        int resultX = whitePieces.get(whitePieces.size()-1).getX();
        int resultY = whitePieces.get(whitePieces.size()-1).getY();
        Integer resultPoints = whitePieces.get(whitePieces.size()-1).getPoints();
        Color resultColor = whitePieces.get(whitePieces.size()-1).getColor();

        Assertions.assertEquals(expectedX, resultX);
        Assertions.assertEquals(expectedY, resultY);
        Assertions.assertEquals(expectedPoints, resultPoints);
        Assertions.assertEquals(expectedColor, resultColor);

    }
    @Test
    public void boardToString_NormalGameSetup_Test() {
        Board board = new Board(GameType.PVP);
        board.initializeBoard();

        String expectedResult = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";
        Assertions.assertEquals(expectedResult,board.boardToString());

        // Pocet figurek v nove hre musi odpovidat
        int expectedNumberOfWhitePieces = board.getPieces(Color.WHITE).size();
        Assertions.assertEquals(16,expectedNumberOfWhitePieces);

        int expectedNumberOfBlackPieces = board.getPieces(Color.BLACK).size();
        Assertions.assertEquals(16,expectedNumberOfBlackPieces);

        // Zadna figurka na nove boarde nesmi byt v checku
        Assertions.assertFalse(board.whiteInCheck());
        Assertions.assertFalse(board.blackInCheck());

        // Zadny z hracu nesmi byt v matu na po inicializaci boardy
        Assertions.assertFalse(board.Mated(Color.WHITE));
        Assertions.assertFalse(board.Mated(Color.BLACK));
    }

    @Test
    public void boardToString_EditorSetup_Test() {
        Board board = new Board(GameType.PVP);
        board.initializeEditor();

        String expectedResult = " , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ";
        Assertions.assertEquals(expectedResult,board.boardToString());

        // Pocet figurek v nove hre musi odpovidat
        int expectedNumberOfWhitePieces = board.getPieces(Color.WHITE).size();
        Assertions.assertEquals(0,expectedNumberOfWhitePieces);

        int expectedNumberOfBlackPieces = board.getPieces(Color.BLACK).size();
        Assertions.assertEquals(0,expectedNumberOfBlackPieces);

        // Zadna figurka na nove boarde nesmi byt v checku
        Assertions.assertFalse(board.whiteInCheck());
        Assertions.assertFalse(board.blackInCheck());

        // Zadny z hracu nesmi byt v matu na po inicializaci boardy
        Assertions.assertFalse(board.Mated(Color.WHITE));
        Assertions.assertFalse(board.Mated(Color.BLACK));
    }

    @Test
    public void GetEveryPossibleMoves_AllPossibleMovesOfWhite_PossibleMovesOfPiecesInArray() {
        //ARRANGE
        int expectedCountOfPossibleMoves = 20;
        Board board = new Board(GameType.PVP);
        board.initializeBoard();
        //ACT
        ArrayList<Square> allPossibleMoves = board.getEveryPossibleMoves(board.getPieces(Color.WHITE));
        //ASSERT
        int resultCountOfPossibleMoves = allPossibleMoves.size();
        Assertions.assertEquals(expectedCountOfPossibleMoves, resultCountOfPossibleMoves);

    }

    @Test
    public void BlackInCheck_BlackKingChecked_CheckedBlackKing() {
        //ARRANGE
        Board board = new Board(GameType.PVP);
        board.initializeBoard();

        boolean expectedResult = true;
        //ACT
        board.removePiece(board.getKing(Color.BLACK));
        board.putPiece(1, 4, "King", Color.BLACK);
        board.putPiece(2, 2, "Knight", Color.WHITE);
        boolean result = board.blackInCheck();
        //ASSERT
        Assertions.assertEquals(expectedResult, result);

    }

    @Test
    public void Mated_WhiteMated_WhiteNotMatedButInCheck() {
        //ARRANGE
        Board board = new Board(GameType.PVP);
        board.initializeBoard();
        Color colorOfKing = Color.WHITE;

        boolean expectedResult = false;
        //ACT
        board.putPiece(3, 2, "Knight", Color.BLACK);
        boolean result = board.Mated(colorOfKing);
        //ASSERT
        Assertions.assertEquals(expectedResult, result);

    }
}
