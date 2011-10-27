package misc;

import java.io.Serializable;

/**
 * Utility class pair to use in the absense of tuples
 */
public class Pair<S, T> implements Serializable{

	private final S first;
	private final T second;
	
	public Pair(S first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public S getFirst() {
		return first;
	}
	
	public T getSecond() {
		return second;
	}
}
