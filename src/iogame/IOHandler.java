package iogame;

import game.Game;
import exceptions.GameIOException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Every class that implements IOHandler is able to load and save a Game
 */
public interface IOHandler {

	/**
	 * Loads a Game
	 * 
	 * @return Game
	 * @throws GameIOException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Game load() throws GameIOException, FileNotFoundException,
			IOException;

	/**
	 * Saves the given Game
	 * 
	 * @param game
	 *            The game you are trying to save
	 * @throws GameIOException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(Game game) throws FileNotFoundException, IOException;
}