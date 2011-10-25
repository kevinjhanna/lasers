package tests;
import exceptions.InvalidBoardSizeException;
import gameparser.GameParser;
import gameparser.InvalidLoadedBoardException;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class GameParserTest {

	@Test(expected=InvalidBoardSizeException.class)
	public void testInvalidBoardSize() throws IOException, InvalidLoadedBoardException {
		GameParser parser = new GameParser(new File("boards/invalidboard.board"));
		parser.parse();
	}
	
}
