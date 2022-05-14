package process;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import cz.cvut.fel.pjv.models.pieces.IPiece;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

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
}
