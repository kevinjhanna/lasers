package game;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import misc.Pair;
import misc.Position;
import tiles.Source;
import tiles.Tile;
import exceptions.InvalidBoardFileException;
import exceptions.RotationNotSupportedException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;
import gameparser.GameParser;

/**
 * Class that models a Lasers and Mirrors game
 */
public class Game implements Serializable{

	public static final int MIN_HEIGHT = 5;
	public static final int MIN_WIDTH = 5;
	public static final int MAX_HEIGHT = 20;
	public static final int MAX_WIDTH = 20;

	private transient Observer observer;
	private Integer boardHeight;
	private Integer boardWidth;
	private Integer score;
	private transient Board board;
	private transient List<Pair<Position, Tile>> initialTiles;
	private transient Map<Source, Position> sources;

	/**
	 * Instantiates a new game from a board file
	 * 
	 * @param f
	 *            The .board file
	 * @return Game The new game
	 * @throws IOException
	 *             in case there is a problem reading the file
	 * @throws InvalidBoardFileException
	 */
	public static Game fromBoardFile(File f) throws IOException,
			InvalidBoardFileException {
		GameParser parser = new GameParser(f);
		return parser.parse();
	}

	/**
	 * Creates a new game from a saved game file
	 * 
	 * @param f
	 * @return Game
	 * @throws IOException
	 */
	public static Game fromSaveFile(File f) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Starts the game
	 */
	public void start(Observer observer) {
		this.observer = observer;
		populateBoard();
		updateScore();
	}

	/**
	 * Restarts the game
	 */
	public void restart() {
		score = 0;
		board = new Board(boardWidth, boardHeight);
		populateBoard();
	}

	/**
	 * Returns the width of the game board
	 * 
	 * @return Integer
	 */
	public Integer getBoardWidth() {
		return boardWidth;
	}

	/**
	 * Returns the height of the game board
	 * 
	 * @return Integer
	 */
	public Integer getBoardHeight() {
		return boardHeight;
	}

	/**
	 * Returns the current game score
	 * 
	 * @return Integer
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * Returns the tile located at the given row and column
	 * 
	 * @param row
	 * @param column
	 * @return tile
	 */
	private Tile getTile(int row, int column) {
		return board.getTile(new Position(row, column));
	}

	/**
	 * Moves a tile from the source position to the target position
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @throws SourceTileEmptyException
	 * @throws TargetTileNotEmptyException
	 */
	public void move(int sourceRow, int sourceColumn, int targetRow,
			int targetColumn) throws SourceTileEmptyException,
			TargetTileNotEmptyException, TileIsFixedException {

		Position source = new Position(sourceRow, sourceColumn);
		Position target = new Position(targetRow, targetColumn);
		board.moveTile(source, target);

		observer.onTileMove(sourceRow, sourceColumn, targetRow, targetColumn,
				getTile(targetRow, targetColumn));

		propagateBoard();
	}

	/**
	 * Rotates the tile at the given position
	 * 
	 * @param row
	 * @param column
	 * @throws RotationNotSupportedException
	 */
	public void rotate(int row, int column)
			throws RotationNotSupportedException {

		board.getTile(new Position(row, column)).rotate();
		observer.onTileRotated(row, column, getTile(row, column));
		propagateBoard();
	}

	/**
	 * Saves the game in the file given
	 * 
	 * @param f
	 *            The file to save the game into
	 * @throws IOException 
	 * @throws  
	 */
	public void save(File f) throws IOException {
		// tiene que tirar IOException en realidad
		ObjectOutputStream file = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
		try{			
		}

		finally{
			file.close();
		}
		
		System.out.println("Archivo guardado en " + f.getName());
	}

	/**
	 * Updates the score and notifies the observer
	 */
	private void updateScore() {
		Integer old = score;
		score = calculateScore();

		if (old != score) {
			observer.onScoreChange(score);
		}
	}

	/**
	 * Recalculate game score
	 * 
	 * @return int
	 */
	private int calculateScore() {
		return 42;
	}

	/**
	 * Creates a new game with a board of the dimensions given and a set of
	 * initial tiles. Does not populate the board with the tiles to enable the
	 * setup of an observer to track board changes
	 * 
	 * @param boardHeight
	 * @param boardWidth
	 * @param initialTiles
	 */
	public Game(int boardHeight, int boardWidth,
			List<Pair<Position, Tile>> initialTiles) {

		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.initialTiles = initialTiles;

		board = new Board(boardHeight, boardWidth);
		sources = getSources(initialTiles);

	}

	private Map<Source, Position> getSources(List<Pair<Position, Tile>> tiles) {

		Map<Source, Position> sources = new HashMap<Source, Position>();

		for (Pair<Position, Tile> entry : tiles) {
			if (entry.getSecond() instanceof Source) {
				sources.put((Source) entry.getSecond(), entry.getFirst());
			}
		}

		return sources;
	}

	/**
	 * Populates the board with the initial set of tiles and their corresponding
	 * locations
	 */
	private void populateBoard() {
		for (Pair<Position, Tile> p : initialTiles) {
			board.setTile(p.getFirst(), p.getSecond());

			observer.onTileSet(p.getFirst().row, p.getFirst().column,
					p.getSecond());
		}
	}

	// TODO WHEN MOVING A MOVABLE SOURCE WE NEED TO UPDATE THE SOURCES
	private void propagateBoard() {
		List<Ray> initialRays = new LinkedList<Ray>();// maybe an instance
														// variable??

		for (Map.Entry<Source, Position> entry : sources.entrySet()) {
			// Creates the ray one tile ahead of the source			
			Ray ray = new Ray(entry.getValue(), entry.getKey().getDirection(), entry.getKey().getColor());
			ray.moveStraight();
			initialRays.add(ray);
			System.out.println("Source position = " + entry.getKey());
			System.out.println("Ray position = " + entry.getKey());
		}

		for (Ray ray : initialRays) {
			spread(ray);
		}
	}

	private void spread(Ray ray) {
		while (ray.canReact()) {
			if (insideBounds(ray.getPosition())) {
				System.out.println(ray.getPosition());
				// should we have another board with ray position as to be able
				// to change ray colors when they collide?
				board.getTile(ray.getPosition()).react(ray);
				if (ray.hasGeneratedRay()) {
					// HERE WE SHOULD PROCESS THE GENERATED RAY onda recursiva?
					spread(ray.getGeneratedRay());
				}
			} else {
				ray.stopMovement();
			}
		}
	}

	// TODO the board should be less "dumb" maybe move this along with MAX
	// constants to the Board
	private boolean insideBounds(Position p) {
		return (p.row < boardHeight && p.row >= 0 && p.column < boardWidth && p.column >= 0);
	}
	
	
	

}
