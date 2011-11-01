package misc;

/**
 * An inmutable pair of generic elements.
 */
public class Pair<S, T> {

	private S first;
	private T second;

	/**
	 * Creates a new pair.
	 */
	public Pair(S first, T second) {
		if (first == null || second == null) {
			throw new IllegalArgumentException();
		}
		this.first = first;
		this.second = second;
	}

	/**
	 * Returns the first element of the pair.
	 * 
	 * @return S
	 */
	public S getFirst() {
		return first;
	}

	/**
	 * Returns the second and last element of the pair.
	 * 
	 * @return T
	 */
	public T getSecond() {
		return second;
	}

}
