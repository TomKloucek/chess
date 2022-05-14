package process;

import cz.cvut.fel.pjv.models.Board;
import cz.cvut.fel.pjv.models.Color;
import cz.cvut.fel.pjv.models.GameType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProcessTests {
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
}
