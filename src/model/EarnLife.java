package model;

public class EarnLife {
	private static final int HEART_VALUE = 1;
	
	private final Position position;
	private HitBox hitBox;
	private Size size;
	
	public EarnLife(Position position, Size size) {
		this.position = position;
		
		this.hitBox = new HitBox(position.getX(), position.getY(),
                size.getWidth(), size.getHeight());
	}
	
	public EarnLife(int x, int y, Size size) {
		this(new Position(x,y), size);
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
	
	public HitBox getHitBox() {
        return hitBox;
    }

}
