package model;

public enum MovingObjectType {
    CAR(3),
    TRUCK(5),
    TURTLE(4),
    TRUNK(6);

    private final int spawnInterval;

    MovingObjectType(int spawnInterval){
        this.spawnInterval = spawnInterval;
    }

    public int getSpawnInterval(){
        return spawnInterval;
    }
}
