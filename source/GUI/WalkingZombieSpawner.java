package source.GUI;

import javafx.application.Platform;

import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import source.Characters.Zombie.Zombie;
import source.Characters.Zombie.ZombieFactory;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalkingZombieSpawner {

    private static final int MAX_ZOMBIE = 10;
    private static final double SPAWN_PROBABILITY = 0.3;
    private static final int SPAWN_INTERVAL = 3000;
    private ArrayList<WalkingZombie> zombies;
    private Thread spawningThread;
    private boolean isGameOver = false;


    public WalkingZombieSpawner(){
        this.zombies = new ArrayList<>();

    }

    public void startSpawning() {
        spawningThread = new Thread(() -> {
            while(!isGameOver){
                try {
                    Thread.sleep(SPAWN_INTERVAL);
                    Platform.runLater(this::trySpawnZombie);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        spawningThread.start();
    }

    private void trySpawnZombie() {
        if(zombies.size() < MAX_ZOMBIE && Math.random() < SPAWN_PROBABILITY){
            spawnZombie();
        }
    }

    private void spawnZombie() {
        ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
        GridPane stateGridPane = mainGameController.getGridtilesmap();
        Zombie randomZombie = ZombieFactory.createZombie();
        WalkingZombie walkingZombie = new WalkingZombie(randomZombie);
//        walkingZombie.setX();
        zombies.add(walkingZombie);
        ImageView imageZombie = new ImageView(walkingZombie.getZombieimg1().getImage());

        int[] values = {10, 21, 32, 43, 54, 65};

        Random random = new Random();

        // Menghasilkan indeks acak
        int randomIndex = random.nextInt(values.length);

        // Mengambil nilai dari array berdasarkan indeks acak
        int randomValue = values[randomIndex];

        ((Pane) stateGridPane.getChildren().get(randomValue))
    }


    public void stopSpawning() {
        isGameOver = true;
    }
}
