package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game {
	private static final int EARN_LIFE_SPAWN_INTERVAL = 20; //ogni 20 secondi
	private LocalDateTime startTime;
	private Map map;
	private Frog frog;
	private EarnLife earnLife;
	private Random randomNumberGenerator;
	private boolean isEarnLifeSpawned;
	private int earnLifeSpawnCycles;
	private LinkedList<MovingObject> movingObjects;
	private String death; //morte formato stringa
	private LocalDateTime lastEarnLifeSpawnTime; 
	
	public Game(Map map) {
		this.startTime = LocalDateTime.now();
		this.map = map;
		this.movingObjects = new LinkedList<>();
		this.randomNumberGenerator = new Random();
		
		this.isEarnLifeSpawned = false;
		this.earnLifeSpawnCycles = 1;
		this.lastEarnLifeSpawnTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);

		this.death = " ";
		
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public Map getMap() {
		return map;
	}

	public Frog getFrog() {
		return frog;
	}

	public EarnLife getEarnLife() {
		return earnLife;
	}

	public Random getRandomNumberGenerator() {
		return randomNumberGenerator;
	}

	public boolean isEarnLifeSpawned() {
		return isEarnLifeSpawned;
	}

	public int getEarnLifeSpawnCycles() {
		return earnLifeSpawnCycles;
	}

	public LinkedList<MovingObject> getMovingObjects() {
		return movingObjects;
	}

	public String getDeath() {
		return death;
	}
	
	//Calcola quanti secondi totali sono passati tra startTime e time
	//Logica che restituisce la rappresentazione “clock-like” (modulo 60/24).
	private long getDurationSeconds(LocalDateTime time) {
		return this.startTime.until(time, ChronoUnit.SECONDS)%60;
	}
		
	private long getDurationMinutes(LocalDateTime time) {
		return this.startTime.until(time, ChronoUnit.MINUTES)%60;
	}

	private long getDurationHours(LocalDateTime time) {
		return this.startTime.until(time, ChronoUnit.HOURS)%24;
	}
		
	public long getMatchDurationSeconds() {
		return this.getDurationSeconds(LocalDateTime.now());
	}
		
	public long getMatchDurationMinutes() {
		return this.getDurationMinutes(LocalDateTime.now());
	}

	public long getMatchDurationHours() {
		return this.getDurationHours(LocalDateTime.now());
	}
	
	public void earnLifeSpawn() {
	    LocalDateTime now = LocalDateTime.now();

	    long secondsSinceLastSpawn =
	            this.lastEarnLifeSpawnTime.until(now, ChronoUnit.SECONDS);

	    //Se sono passati abbastanza secondi e non è stato già spawnato l’oggetto
	    if (secondsSinceLastSpawn >= EARN_LIFE_SPAWN_INTERVAL && !this.isEarnLifeSpawned) {

	        int x = this.randomNumberGenerator.nextInt(0, this.map.getWidth() + 1);
	        int y = this.randomNumberGenerator.nextInt(0, this.map.getHeight() + 1);

	        this.earnLife = new EarnLife(x, y);

	        this.isEarnLifeSpawned = true;
	        this.earnLifeSpawnCycles++;

	        this.lastEarnLifeSpawnTime = now; //reset tempo ultimo spawn
	    }

	    //Se siamo "fuori" dall'intervallo, abilitiamo lo spawn del prossimo ciclo
	    if (secondsSinceLastSpawn < EARN_LIFE_SPAWN_INTERVAL) {
	        this.isEarnLifeSpawned = false;
	    }
	}

	/* metodi per il movimento del giocatore */
	private void movePlayer(Frog frog, int x, int y) {
		frog.setX(x);
	    frog.setY(y);
	}

	public void movePlayerUp(Frog frog) {
		this.movePlayer(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.UP);
	}

	public void movePlayerDown(Frog frog) {
		this.movePlayer(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.DOWN);
	}

	public void movePlayerLeft(Frog frog) {
		this.movePlayer(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.LEFT);
	}

	public void movePlayerRight(Frog frog) {
		this.movePlayer(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.RIGHT);
	}
	
	
	
	
	
	
	
	
	
	
}
