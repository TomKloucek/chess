package process;

import cz.cvut.fel.pjv.models.*;
import cz.cvut.fel.pjv.models.pieces.King;
import cz.cvut.fel.pjv.models.pieces.Pawn;
import cz.cvut.fel.pjv.models.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import org.junit.jupiter.api.Assertions;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.BlockingDeque;

import static org.mockito.Mockito.*;

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
        String expectedResult = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";
        Assertions.assertEquals(expectedResult,board.boardToString());
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
        String expectedResultString = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";
        Assertions.assertEquals(expectedResultString,board.boardToString());

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
        String expectedResultString = "RBa8,NBb8,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X,Bd7X,Be7X,Bf7X,Bg7X,Bh7X, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,Wg2X,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1,BWf1,NWg1,RWh1";
        Assertions.assertEquals(expectedResultString,board.boardToString());
    }

    @Test
    public void ShortCastleWithWhite_Test() {
        //ARRANGE
        Board board = new Board(GameType.PVP);
        board.initializeBoard();
        board.stringToBoard("RBa8, ,BBc8,QBd8,KBe8,BBf8,NBg8,RBh8,Ba7X,Bb7X,Bc7X, , ,Bf7X,Bg7X,Bh7X, , ,NBc6,Bd6X, , , , , , , , ,Be5 , , , , , , , , , ,Wg4X, , , , , , ,NWf3, , ,Wa2X,Wb2X,Wc2X,Wd2X,We2X,Wf2X,BWg2,Wh2X,RWa1,NWb1,BWc1,QWd1,KWe1, , ,RWh1", false);

        Square withKing = board.getBoard()[4][0];
        Square withRook = board.getBoard()[7][0];

        Assertions.assertTrue(board.getKing(Color.WHITE).possibleMovement(board).contains(withRook));

        // CASTLE
        board.movePiece(board.pickPiece(withKing.getX(), withKing.getY()),7,0 );

        Assertions.assertTrue(board.getBoard()[6][0].getPiece() instanceof King);
        Assertions.assertTrue(board.getBoard()[6][0].getPiece().getColor() == Color.WHITE);

        Assertions.assertTrue(board.getBoard()[5][0].getPiece() instanceof Rook);
        Assertions.assertTrue(board.getBoard()[5][0].getPiece().getColor() == Color.BLACK);
    }

    @Test
    public void GetEveryXrayMove_PieceInXrayMoves_PieceInXrayMoves(){
        //ARRANGE
        Board board = new Board(GameType.PVP);
        board.initializeBoard();
        IPiece pawnInFrontOfWhiteKing = board.getPieces(Color.WHITE).get(12);

        IPiece pawnInFrontOfBlackKing = board.getPieces(Color.BLACK).get(4);
        IPiece blackQueen = board.getPieces(Color.BLACK).get(11);
        Player p1 = new Player(cz.cvut.fel.pjv.models.Color.WHITE, null);
        Player p2 = new Player(cz.cvut.fel.pjv.models.Color.BLACK, null);
        Game game = new Game(p1, p2, board);

        State.getInstance().setGame(game);
        //ACT
        board.movePiece(pawnInFrontOfWhiteKing, 4, 2);
        board.movePiece(pawnInFrontOfBlackKing, 4, 4);
        board.movePiece(pawnInFrontOfWhiteKing, 4, 3);
        board.movePiece(blackQueen, 7, 3);
        IPiece pawnWhichMovesAreBlocked = board.getPieces(Color.WHITE).get(5);

        //ASSERT
        boolean expectedOccurrence = true;
        boolean resultOccurrence = board.getEveryXRayMove(board.getPieces(Color.BLACK)).contains(
                board.getBoard()[pawnWhichMovesAreBlocked.getX()][pawnWhichMovesAreBlocked.getY()]);

        Assertions.assertEquals(expectedOccurrence, resultOccurrence);
    }
}
