package source.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        Timer timer = new Timer();
        timer.schedule(new SpawnZombieTask(gameMap), 0, 1000);
    }
}

class SpawnZombieTask extends TimerTask {
    private GameMap gameMap;
    private int height = 10;  // Replace with your actual height

    public SpawnZombieTask(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    @Override
    public void run() {
        gameMap.laneSpawner();
    }
}