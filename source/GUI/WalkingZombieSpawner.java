package source.GUI;

import javafx.application.Platform;

import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import source.Characters.Zombie.*;


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
                    System.out.println("JUMLAH ZOMBIE: " + zombies.size());
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

        zombies.add(walkingZombie);
        ImageView imageZombie = new ImageView(walkingZombie.getZombieimg1().getImage());
        ImageView imageZombie2 = null;

        if(walkingZombie.getZombie() instanceof NormalZombie){
            setupImageView(imageZombie, 152, 245, -100, 125);
        }
        else if(walkingZombie.getZombie() instanceof ConeheadZombie){
            setupImageView(imageZombie, 151, 290, -150, 125);
        }
        else if(walkingZombie.getZombie() instanceof BucketheadZombie){
            setupImageView(imageZombie, 158, 275, -135, 125);
        }
        else if(walkingZombie.getZombie() instanceof DuckyTubeZombie){
            setupImageView(imageZombie, 154, 245, -100, 125);
        }
        else if(walkingZombie.getZombie() instanceof FootballZombie){
            setupImageView(imageZombie, 257, 275, -125, 125);
        }
        else if(walkingZombie.getZombie() instanceof JackInTheBoxZombie){
            setupImageView(imageZombie, 185, 245, -100, 125);
        }
        else if(walkingZombie.getZombie() instanceof DolphinRiderZombie){
            setupImageView(imageZombie, 203, 245, -100, 125);
        }
        else if(walkingZombie.getZombie() instanceof ScreenDoorZombie){
            setupImageView(imageZombie, 298, 240, -100, 125);
        }
        else if(walkingZombie.getZombie() instanceof PoleVaultingZombie){
            setupImageView(imageZombie, 320, 245, -100, 125);
            imageZombie2 = new ImageView(walkingZombie.getZombieimg2().getImage());
            setupImageView(imageZombie2, 211, 250, -110, 125);
            imageZombie2.setOpacity(0);
        }
        else if(walkingZombie.getZombie() instanceof NewspaperZombie){
            setupImageView(imageZombie, 180, 245, -100, 125);
            imageZombie2 = new ImageView(walkingZombie.getZombieimg2().getImage());
            setupImageView(imageZombie2, 245, 245, -120, 125);
            imageZombie2.setOpacity(0);
        }

        int[] values = {10, 21, 32, 43, 54, 65};


        Random random = new Random();

        // Menghasilkan indeks acak
        int randomIndex = random.nextInt(values.length);

        // Mengambil nilai dari array berdasarkan indeks acak
        int randomValue = values[randomIndex];

        System.out.println("Zombie yang tercetak: " + walkingZombie.getZombie().getClass().getSimpleName() + "   Di-spawn di tiles: " + randomValue);

        ((Pane) stateGridPane.getChildren().get(randomValue)).getChildren().add(walkingZombie);
        if(walkingZombie.getZombie() instanceof PoleVaultingZombie || walkingZombie.getZombie() instanceof NewspaperZombie){
            ((Pane) stateGridPane.getChildren().get(randomValue)).getChildren().add(imageZombie2);
        }
        ((Pane) stateGridPane.getChildren().get(randomValue)).getChildren().add(imageZombie);
    }


    public void stopSpawning() {
        isGameOver = true;
    }

    public void setupImageView(ImageView img, int fitWidth, int fitHeight, int layoutY, int layoutX){
        img.setFitWidth(fitWidth);
        img.setFitHeight(fitHeight);
        img.setLayoutX(layoutX);
        img.setLayoutY(layoutY);
    }
}
