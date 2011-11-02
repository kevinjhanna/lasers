package tests;

import iogame.IOHandler;
import iogame.IOSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.junit.Test;



import exceptions.GameIOException;

public class IOSerializerTest {

	@Test(expected = FileNotFoundException.class)
	public void testFileNotFound() throws FileNotFoundException, GameIOException, IOException{
		IOHandler io = new IOSerializer(new File("savedgames/tests/FileNotFound"));
		io.load();
	}

	@Test(expected = GameIOException.class)
	public void testCorruptedFile() throws FileNotFoundException, GameIOException, IOException{
		IOHandler io = new IOSerializer(new File("savedgames/tests/corruptedfile"));
		io.load();
	}
}
