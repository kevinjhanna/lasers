package game;

public class ScoreChangedEvent {

	private final int score;
	
	public ScoreChangedEvent(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}
}
