package tiles;


/**
 * Wall tile. Does not support rotation or movement
 */
public class Wall extends Tile {

	private static final long serialVersionUID = 8896857962913603722L;

	public boolean isFixed() {
		return true;
	}

	@Override
	protected DirectionComponent getDirectionComponent() {
		return new NoDirection();
	}

	@Override
	protected PropagationComponent getPropagationComponent(Tile tile) {
		return new BlockPropagation(tile);
	}
	
}
