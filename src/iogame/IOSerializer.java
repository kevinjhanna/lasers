package iogame;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.GameIOException;
import game.Game;

/**
 * This class handles game saving and loading through serialization.
 */
public class IOSerializer implements IOHandler{
	private File file;

	/**
	 * Creates a new IOSerializer for the given file
	 * 
	 * @param file
	 *            The file to handle
	 */
	public IOSerializer(File f) {
		this.file = f;
	}

	/**
	 * Creates a new game from a saved game file.
	 * 
	 * @return Game
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws InvalidLoadedFileException
	 */
	public Game load() throws GameIOException, FileNotFoundException,
			IOException {
		ObjectInputStream stream = null;
		try {
			stream = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(file)));
			return (Game) stream.readObject();
		} catch (ClassCastException e){
			// TODO preguntar si esta bueno atrapar esta excepcion o catchear Exception
			throw new GameIOException();
		} catch (ClassNotFoundException e) {
			throw new GameIOException();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	/**
	 * Saves the game in the specified file.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(Game game) throws FileNotFoundException, IOException {
		ObjectOutputStream stream = null;
		try {
			stream = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(file)));
			stream.writeObject(game);

		} finally {
			if (stream != null) {
				stream.close();
			}
		}

	}
}
