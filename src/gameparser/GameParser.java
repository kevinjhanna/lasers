package gameparser;

import game.Game;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import misc.Direction;
import misc.Position;
import tiles.*;

public class GameParser {
	private File f;
	private Scanner stream;

	private int width;
	private int height;

	public GameParser(File f) throws IOException {
		if (f.exists() && f.isFile()) {
			this.f = f;
		} else {
			throw new FileNotFoundException();
		}
	}

	public Game parse() throws IOException, InvalidLoadedBoardException {
		Map<Position, Tile> tiles = new HashMap<Position, Tile>();

		try {
			stream = new Scanner(f);

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

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
		return new Game(width, height, tiles);

	}

	private void processSize(String aLine) throws InvalidLoadedBoardException {
		if (!aLine.matches("\\d*,\\d*")) {
			throw new InvalidLoadedBoardException();
		}

		Scanner lineScanner = new Scanner(aLine);
		lineScanner.useDelimiter(",");

		width = Integer.parseInt(lineScanner.next());
		height = Integer.parseInt(lineScanner.next());

	}

	private void processTile(String aLine, Map<Position, Tile> tiles)
			throws InvalidLoadedBoardException {
		if (!aLine.matches("(\\d*,){6}\\d*")) {
			throw new InvalidLoadedBoardException();
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
			throw new InvalidLoadedBoardException();
		}

		// Colors
		int rgb[] = new int[3];
		for (int i = 0; i < 3; i++) {
			rgb[i] = Integer.parseInt(lineScanner.next());
			if (!mockTile.validColor(rgb[i])) {
				throw new InvalidLoadedBoardException();
			}
		}

		// ahora se crea el tile
		Tile realTile = null;
		Direction direction = Direction.NORTH;// TODO pasar un int de rotation a
												// un Direction

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

		tiles.put(position, realTile);
	}

}
