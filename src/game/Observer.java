package game;

import tiles.Drawable;

/**
 * Interfaz con m�todos que son llamados por el juego para actualizar el frontend
 * 
 */
public interface Observer {

	/**
	 * Notificador de cambio de posici�n de una celda del tablero
	 * 
	 * @param sourceRow
	 * @param sourceColumn
	 * @param targetRow
	 * @param targetColumn
	 * @param tile
	 */
	void onTileMove(int sourceRow, int sourceColumn, int targetRow, int targetColumn, Drawable drawable);

	/**
	 * Notificador de rotaci�n de una celda en el tablero
	 * 
	 * @param row
	 * @param column
	 * @param tile
	 */
	void onTileRotated(int row, int column, Drawable drawable);

	/**
	 * Notificador de adici�n de una celda al tablero
	 * 
	 * @param row
	 * @param column
	 * @param tile
	 */
	void onTileSet(int row, int column, Drawable drawable);

	/**
	 * Notificador de actualizaci�n de puntaje
	 * 
	 * @param newScore
	 */
	void onScoreChange(int newScore);
	
}
