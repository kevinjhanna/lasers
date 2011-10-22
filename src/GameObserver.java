import game.ScoreChangedEvent;
import game.TileMovedEvent;
import game.TileRotatedEvent;
import game.TileSetEvent;
import gui.BoardPanel;

import java.util.Observable;
import java.util.Observer;

public class GameObserver implements Observer {

	private LasersAndMirrors frame;
	private ImageFactory imageFactory;

	public GameObserver(LasersAndMirrors frame) {
		this.frame = frame;
		this.imageFactory = ImageFactory.getInstance();
	}


	public void update(Observable o, TileMovedEvent e) {
		BoardPanel bp = frame.getBoardPanel();
		bp.clearImage(e.getSourceRow(), e.getSourceColumn());
		bp.setImage(e.getTargetRow(), e.getTargetColumn(),
				imageFactory.tile(e.getTile()));
		bp.repaint();
	}

	public void update(Observable o, TileRotatedEvent e) {
		BoardPanel bp = frame.getBoardPanel();
		bp.setImage(e.getRow(), e.getColumn(), imageFactory.tile(e.getTile()));
		bp.repaint();
	}

	public void update(Observable o, TileSetEvent e) {
		BoardPanel bp = frame.getBoardPanel();
		bp.setImage(e.getRow(), e.getColumn(), imageFactory.tile(e.getTile()));
		bp.repaint();
	}
	
	public void update(Observable o, ScoreChangedEvent e) {
		frame.setScore(e.getScore());
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Limpiar esto que est‡ horrible, deberia funcionar con polimorfismo
		if (arg instanceof TileSetEvent) {
			update(o, (TileSetEvent)arg);
		}
		if (arg instanceof TileRotatedEvent) {
			update(o, (TileRotatedEvent)arg);
		}
		if (arg instanceof TileMovedEvent) {
			update(o, (TileMovedEvent)arg);
		}
		if (arg instanceof ScoreChangedEvent) {
			update(o, (ScoreChangedEvent)arg);
		}
	}
}
