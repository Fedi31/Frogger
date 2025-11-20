package model;

import java.util.LinkedList;

public class Frog {

    public static final int DEFAULT_LIVES = 2;
    public static final int MOVE_STEP = 1;

    private final String name;
    private Direction direction;
    private final Size size;
    private Position position;
    private final Map map;
    private int lives;
    private HitBox hitBox;
    private final int screenWidth;
    private final int screenHeight;
    private LinkedList<MovingObject> movingObjects;

    public Frog(String name, Direction direction, Size size, Position position, Map map, int lives) {
        this.name = name;
        this.direction = direction;
        this.size = size;
        this.position = position;
        this.map = map;
        this.screenWidth = map.getWidth();
        this.screenHeight = map.getHeight();
        this.lives = lives;
        this.hitBox = new HitBox(position.getX(), position.getY(), size.getWidth(), size.getHeight());
        this.movingObjects = new LinkedList<>();
    }

    //getters
    public String getName() {
        return name;
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

    public Direction getDirection() {
        return direction;
    }

    public int getLives() {
        return lives;
    }

    public int getWidth() {
        return size.getWidth();
    }

    public int getHeight() {
        return size.getHeight();
    }
    
    //setters
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        this.lives--;
    }

    public void resetLives() {
        this.lives = DEFAULT_LIVES;
    }

    public boolean isDead() {
        return this.lives <= 0;
    }

    //movement
    public void moveUp() {
        this.position.move(0, -MOVE_STEP);
        updateHitBox();
        resetCorrectPosition();
    }

    public void moveDown() {
        this.position.move(0, MOVE_STEP);
        updateHitBox();
        resetCorrectPosition();
    }

    public void moveRight() {
        this.position.move(MOVE_STEP, 0);
        updateHitBox();
        resetCorrectPosition();
    }

    public void moveLeft() {
        this.position.move(-MOVE_STEP, 0);
        updateHitBox();
        resetCorrectPosition();
    }
    
    private void updateHitBox() {
        this.hitBox.x = this.position.getX();
        this.hitBox.y = this.position.getY();
        this.hitBox.width = this.size.getWidth();
        this.hitBox.height = this.size.getHeight();
    }
    
    public HitBox getHitBox() {
        return this.hitBox;
    }
    
    private void resetCorrectPosition() {
        if (position.getX() < 0) {
            position.move(1, 0); 
        }

        if (position.getX() + size.getWidth() > screenWidth) {
            position.move(-1, 0); 
        }

        if (position.getY() < 0) {
            position.move(0, 1); 
        }

        if (position.getY() + size.getHeight() > screenHeight) {
            position.move(0, -1); 
        }

        updateHitBox(); // aggiorna sempre la hitbox
    }

    public boolean isInWaterArea() {
        int topArea = this.position.getY();
        int bottomArea = this.position.getY() + this.size.getHeight();

        // controlla se anche solo una parte della rana è nel fiume
        return bottomArea > map.getRiverTop() && topArea < map.getRiverBottom();
    }
    

    //Metodo per captare le collisioni tra hitbox rana e hitbox obj
    public void collisionDetection(LinkedList<MovingObject> movingObjects) {
    	boolean onWater = false;
    	 
        for (MovingObject obj : movingObjects) {
        	if (this.hitBox.intersects(obj.getHitBox())) {
	                
        		//Se collide con un veicolo, perde una vita
	            if (obj.getType() == MovingObjectType.CAR ||
	            		obj.getType() == MovingObjectType.TRUCK) {
	
	                    loseLife();
	                    System.out.println("Frog lost a life! Lives: " + lives);
	            }
	
	            //Se è in acqua (trunk o turtle), frog va trasportata
	            if (obj.getType() == MovingObjectType.TRUNK ||
	            	obj.getType() == MovingObjectType.TURTLE) {
	
	                // esempio: la rana viene spostata con il tronco
	                this.position.setX(this.position.getX() + 
	                		(obj.getDirection() == Direction.RIGHT ? 1 : -1));
	                updateHitBox();
	                onWater = true;
	            }
	        }
        }
        
        if (!onWater && isInWaterArea()) { 
        	loseLife();
            System.out.println("Frog felt into water! Lives: " + lives);
        }
	     
        if (isDead())
            System.out.println("Dead!");
    }
}

    
