package gameparser;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import misc.Direction;
import misc.Pair;
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
	 * 		The file to parse
	 * @throws IOException
	 * 		In case the file exists
	 */
	public GameParser(File file) throws IOException {
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
	 * @throws InvalidBoardFileException
	 */
	public Game parse() throws IOException, InvalidBoardFileException {
		
		List<Pair<Position, Tile>> tiles = new ArrayList<Pair<Position, Tile>>();

		try {
			stream = new Scanner(file);

			boolean gotSize = false;
			while (stream.hasNextLine()) {
				// dejamos de lado los comentarios
				String aLine = stream.nextLine().replaceAll("(#.*|\\s)", ""); 
				if (!aLine.matches("^\\s*$")) {
					// no procesamos lineas en blanco CAMBIAR a length=0
					if (!gotSize) {
						processSize(aLine);
						gotSize = true;
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
	private void processSize(String aLine) throws InvalidBoardFileException {
		if (!aLine.matches("\\d*,\\d*")) {
			throw new InvalidBoardFileException();
		}

		Scanner lineScanner = new Scanner(aLine);
		lineScanner.useDelimiter(",");

		width = Integer.parseInt(lineScanner.next());
		height = Integer.parseInt(lineScanner.next());

	}

	/**
	 * Processes a single tile
	 * 
	 * @param aLine
	 * @param tiles
	 * @throws InvalidBoardFileException
	 */
	private void processTile(String aLine, List<Pair<Position, Tile>> tiles)
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
		TileValues mockTile = TileValues.fromInt(type);

		int rotation = Integer.parseInt(lineScanner.next());
		if (!mockTile.possibleRotation(rotation)) {
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

		// ahora se crea el tile
		Tile realTile = null;
		// TODO pasar un int de rotation a un Direction
		Direction direction = Direction.NORTH;

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

		tiles.add(new Pair<Position, Tile>(position, realTile));
	}

}
