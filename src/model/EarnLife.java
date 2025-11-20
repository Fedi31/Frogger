package model;

public class EarnLife {
	private static final int HEART_VALUE = 1;
	
	private final Position position;
	
	public EarnLife(Position position) {
		this.position = position;
	}
	
	public EarnLife(int x, int y) {
		this(new Position(x,y));
	}

	public Position getPosition() {
		return position;
	}
	
	public int getX() {
		return position.getX();
	}
	
	public int getY() {
		return position.getY();
	}
}
