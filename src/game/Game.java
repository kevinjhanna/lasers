package game;

import exceptions.InvalidBoardSizeException;
import exceptions.RotationNotSupportedException;
import exceptions.SourceTileEmptyException;
import exceptions.TargetTileNotEmptyException;
import exceptions.TileIsFixedException;
import gameparser.GameParser;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import misc.Position;
import tiles.Tile;

/**
 * Clase que representa un juego de Lasers and Mirrors
 * 
 */
public class Game {

	private Observer observer;
	private Integer boardHeight;
	private Integer boardWidth;
	private Integer score;
	private Board board;
	private Map<Position, Tile> initialTiles;
	
	public static final int MAXHEIGHT = 20;
	public static final int MAXWIDTH = 20;
	public static final int MINHEIGHT = 5;
	public static final int MINWIDTH = 5;

	/**
	 * Crea un nuevo juego a partir de un archivo de tablero
	 * 
	 * @param f
	 * @return Game
	 * @throws IOException
	 */
	public static Game fromBoardFile(File f) throws IOException {
		
		GameParser gameParser = new GameParser(f);
		Game game = null;
		try{
			game = gameParser.parse();
		}
		catch(Exception e){
			
		}
		return game;
	}

	/**
	 * Crea un nuevo juego a partir de un juego guardado
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
	 * Comienza el juego
	 */
	public void start(Observer observer) {
		this.observer = observer;
		populateBoard();
		updateScore();
	}

	/**
	 * Retorna el ancho del tablero
	 * 
	 * @return int
	 */
	public int getBoardWidth() {
		return boardWidth;
	}

	/**
	 * Retorna el alto del tablero
	 * 
	 * @return int
	 */
	public int getBoardHeight() {
		return boardHeight;
	}

	/**
	 * Obtiene la celda del tablero ubicada en la posici—n dada
	 * 
	 * @param row
	 * @param column
	 * @return tile
	 */
	public Tile getTile(int row, int column) {
		return board.getTile(new Position(row, column));
	}

	/**
	 * Mueve una celda en el tablero e informa al observer del cambio
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

		if (getTile(sourceRow, sourceColumn).isFixed()) {
			throw new TileIsFixedException();
		}
		if (getTile(sourceRow, sourceColumn).isEmpty()) {
			throw new SourceTileEmptyException();
		}
		if (!getTile(targetRow, targetColumn).isEmpty()) {
			throw new TargetTileNotEmptyException();
		}

		Position source = new Position(sourceRow, sourceColumn);
		Position target = new Position(targetRow, targetColumn);
		board.moveTile(source, target);

		observer.onTileMove(sourceRow, sourceColumn, targetRow, targetColumn,
				getTile(targetRow, targetColumn));
	}

	/**
	 * Rota una pieza en el tablero e informa al observer del cambio
	 * 
	 * @param row
	 * @param column
	 * @throws RotationNotSupportedException
	 */
	public void rotate(int row, int column)
			throws RotationNotSupportedException {

		board.getTile(new Position(row, column)).rotate();
		observer.onTileRotated(row, column, getTile(row, column));
	}

	/**
	 * Guarda el juego en el archivo dado
	 * 
	 * @param f
	 */
	public void save(File f) {
		System.out.println("Archivo guardado en " + f.getName());
	}

	/**
	 * Actualiza el puntaje y notifica al Observer del cambio
	 */
	private void updateScore() {
		Integer old = score;
		score = calculateScore();

		if (old != score) {
			observer.onScoreChange(score);
		}
	}

	/**
	 * Recalcula el puntaje actual
	 * 
	 * @return int
	 */
	private int calculateScore() {
		return 42;
	}

	/**
	 * Constructor interno para crear un juego de las dimensiones dadas con la
	 * disposici—n de celdas provista
	 * 
	 * @param boardHeight
	 * @param boardWidth
	 * @param initialTiles
	 */
	public Game(int boardHeight, int boardWidth,
			Map<Position, Tile> initialTiles) {
		
		if (boardHeight > MAXHEIGHT || boardHeight < MINHEIGHT || boardWidth > MAXWIDTH || boardWidth < MINWIDTH){
			throw new InvalidBoardSizeException();
		}

		this.boardHeight = boardHeight;
		this.boardWidth = boardWidth;
		this.initialTiles = initialTiles;

		board = new Board(boardHeight, boardWidth);
	}

	/**
	 * Completa el tablero con las celdas provistas inicialmente
	 */
	private void populateBoard() {
		// TODO deberia chequiar que esten las position dentro del rango del board, se usa tanto cuando se carga del parser, o de juego cargado
		for (Map.Entry<Position, Tile> e : initialTiles.entrySet()) {
			board.setTile(e.getKey(), e.getValue());

			observer.onTileSet(e.getKey().row, e.getKey().column, e.getValue());
		}
	}

}
