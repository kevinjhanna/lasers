package parser;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import misc.Direction;
import misc.Position;
import tiles.DoubleMirror;
import tiles.Filter;
import tiles.MoveableSource;
import tiles.SimpleMirror;
import tiles.Source;
import tiles.SplitMirror;
import tiles.Target;
import tiles.Tile;
import tiles.Wall;
import exceptions.InvalidBoardFileException;
import exceptions.InvalidBoardSizeException;
import game.Board;
import game.Game;

/**
 * Board file parser
 */
public class GameParser {

	private File file;
	private Scanner stream;
	private int width;
	private int height;

	/**
	 * Creates a new parser for the given file
	 * 
	 * @param file
	 *            The file to parse
	 * @throws IOException
	 *             In case the file exists
	 */
	public GameParser(File file) throws FileNotFoundException {
		if (file.exists() && file.isFile()) {
			this.file = file;
		} else {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Parses the file and returns a new game
	 * 
	 * @return Game
	 * @throws IOException
	 *             In case there is a problem reading the file
	 * @throws InvalidBoardFileException
	 * @throws InvalidBoardSizeException
	 */
	public Game parse() throws IOException, InvalidBoardFileException,
			InvalidBoardSizeException {

		Map<Tile, Position> tiles = new HashMap<Tile, Position>();

		try {
			stream = new Scanner(new BufferedInputStream(new FileInputStream(
					file)));

			boolean hasSize = false;
			while (stream.hasNextLine()) {
				/* leave out comments (starting with #) and whitespaces */
				String aLine = stream.nextLine().replaceAll("(#.*|\\s)", "");
				if (aLine.length() > 0) {
					/* do not process empty lines */
					if (!hasSize) {
						processSize(aLine);
						hasSize = true;
					} else {
						processTile(aLine, tiles);
					}
				}
			}
			return new Game(width, height, tiles);

		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	/**
	 * Processes the board size
	 * 
	 * @param aLine
	 * @throws InvalidBoardFileException
	 */
	private void processSize(String aLine) throws InvalidBoardFileException,
			InvalidBoardSizeException {
		if (!aLine.matches("\\d*,\\d*")) {
			throw new InvalidBoardFileException();
		}

		Scanner lineScanner = new Scanner(aLine);
		lineScanner.useDelimiter(",");

		width = Integer.parseInt(lineScanner.next());
		height = Integer.parseInt(lineScanner.next());

		if (!Board.validSize(height, width)) {
			throw new InvalidBoardSizeException();
		}

	}

	/**
	 * Processes a single tile
	 * 
	 * @param aLine
	 * @param tiles
	 * @throws InvalidBoardFileException
	 */
	private void processTile(String aLine, Map<Tile, Position> tiles)
			throws InvalidBoardFileException {
		if (!aLine.matches("(\\d*,){6}\\d*")) {
			throw new InvalidBoardFileException();
		}

		Scanner lineScanner = new Scanner(aLine);
		lineScanner.useDelimiter(",");

		// Board Size
		Integer row = Integer.parseInt(lineScanner.next());
		Integer column = Integer.parseInt(lineScanner.next());
		
		Position position = new Position(row, column);

		int type = Integer.parseInt(lineScanner.next());
		TileValue mockTile = TileValue.fromInt(type);
		if (mockTile == null) {
			throw new InvalidBoardFileException();
		}

		int rotation = Integer.parseInt(lineScanner.next());
		if (!mockTile.validDirection(rotation)) {
			throw new InvalidBoardFileException();
		}

		// Colors
		int rgb[] = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = Integer.parseInt(lineScanner.next());
			if (!mockTile.validColor(rgb[i])) {
				throw new InvalidBoardFileException();
			}
		}

		// Direction
		Direction direction = Direction.fromInteger(rotation);

		// Create the tile
		Tile realTile = null;

		Color color = new Color(rgb[0], rgb[1], rgb[2]);

		switch (mockTile) {
		case SOURCE:
			realTile = new Source(color, direction);
			break;
		case MOVEABLESOURCE:
			realTile = new MoveableSource(color, direction);
			break;
		case TARGET:
			realTile = new Target(color);
			break;
		case SIMPLEMIRROR:
			realTile = new SimpleMirror(direction);
			break;
		case DOUBLEMIRROR:
			realTile = new DoubleMirror(direction);
			break;
		case SPLITMIRROR:
			realTile = new SplitMirror(direction);
			break;
		case WALL:
			realTile = new Wall();
			break;
		case FILTER:
			realTile = new Filter(color, direction);
			break;
		}

		tiles.put(realTile, position);
	}

}
