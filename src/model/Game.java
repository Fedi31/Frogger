package model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	private HashMap<MovingObjectType, Integer> spawnIntervals;
    private HashMap<MovingObjectType, LocalDateTime> lastSpawnTime;

	private String death; //morte formato stringa
	private LocalDateTime lastEarnLifeSpawnTime; 
	
	public Game(Map map) {
		this.startTime = LocalDateTime.now();
		this.map = map;
		this.randomNumberGenerator = new Random();
		
		this.isEarnLifeSpawned = false;
		this.earnLifeSpawnCycles = 1;
		this.lastEarnLifeSpawnTime.until(LocalDateTime.now(), ChronoUnit.SECONDS);

		this.death = "";
		
		this.spawnIntervals = new HashMap<>();
        this.lastSpawnTime = new HashMap<>();

        // Riempie la mappa usando i valori definiti nell’enum
        for (MovingObjectType type : MovingObjectType.values()) {
            spawnIntervals.put(type, type.getSpawnInterval());
            lastSpawnTime.put(type, LocalDateTime.now());
        }
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
	private void moveFrog(Frog frog, int x, int y) {
		frog.setX(x);
	    frog.setY(y);
	}

	public void moveFrogUp(Frog frog) {
		this.moveFrog(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.UP);
	}

	public void moveFrogDown(Frog frog) {
		this.moveFrog(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.DOWN);
	}

	public void moveFrogLeft(Frog frog) {
		this.moveFrog(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.LEFT);
	}

	public void moveFrogRight(Frog frog) {
		this.moveFrog(frog, frog.getX(), frog.getY());
		frog.setDirection(Direction.RIGHT);
	}
	
	
	
	
	
}
