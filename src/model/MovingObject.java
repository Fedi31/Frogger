package model;

public class MovingObject{
	private static final int START_X_LEFT = -100;
    private static final int START_X_RIGHT = 900;

    private MovingObjectType type;
    private Direction direction;
    private final int speed = 1;
    private Position position;
    private Size size;
    private HitBox hitBox;
    private int screenWidth;

    public MovingObject(Position position, Size size, MovingObjectType type, Map map) {
        this.position = position;
        this.size = size;
        this.type = type;
        this.screenWidth = map.getWidth();
        this.hitBox = new HitBox(position.getX(), position.getY(),
                                 size.getWidth(), size.getHeight());

        //Imposto la direzione in base al tipo
        switch (type) {
            case CAR:
            case TRUCK:
                this.direction = Direction.RIGHT;
                break;
            case TURTLE:
            case TRUNK:
                this.direction = Direction.LEFT;
                break;
            default:
                throw new IllegalArgumentException("Tipo non riconosciuto: " + type);
        }
    }

    public void setPosition(Position position) {
        this.position = position;
        updateHitBox();
    }

    private void updateHitBox() {
        hitBox.x = position.getX();
        hitBox.y = position.getY();
        hitBox.width = size.getWidth();
        hitBox.height = size.getHeight();
    }

    public void updatePosition() {
        if (direction == Direction.RIGHT) {
            position.setX(position.getX() + speed);
        } else {
            position.setX(position.getX() - speed);
        }
        updateHitBox();

        if (isOutOfBounds()) {
            resetPositionAtStart();
        }
    }

    private boolean isOutOfBounds() {
        if (direction == Direction.RIGHT) {
            return position.getX() > screenWidth;
        } else {
            return position.getX() + size.getWidth() < 0;
        }
    }

    private void resetPositionAtStart() {
        if (direction == Direction.RIGHT) {
            position.setX(START_X_LEFT);
        } else {
            position.setX(START_X_RIGHT);
        }
        updateHitBox();
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public MovingObjectType getType() {
        return type;
    }

}
