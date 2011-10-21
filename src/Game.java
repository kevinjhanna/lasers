import java.io.File;


public class Game {

	private Board board;
	
	public Game(File f) {
		System.out.println("Archivo cargado de " +  f.getName());
	}
	
	public void save(File f) {
		System.out.println("Archivo guardado en " + f.getName());
	}
}
