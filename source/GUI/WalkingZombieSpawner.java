package source.GUI;

import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import source.Characters.Zombie.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WalkingZombieSpawner {

    private static final int MAX_ZOMBIE = 10;
    private static final double SPAWN_PROBABILITY = 1;
    private static final int SPAWN_INTERVAL = 3000;
    private ArrayList<WalkingZombieController> zombies;
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
                    Platform.runLater(() -> {
                        try {
                            trySpawnZombie();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    });
                    System.out.println("JUMLAH ZOMBIE: " + zombies.size());
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        spawningThread.start();
    }

    private void trySpawnZombie() throws IOException {
        if(zombies.size() < MAX_ZOMBIE && Math.random() < SPAWN_PROBABILITY){
            try {
                spawnZombie();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void spawnZombie() throws IOException {
        ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
        AnchorPane zombieArea = mainGameController.getZombieArea();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("walkingzombie.fxml"));
        try {
            AnchorPane zombiePane = loader.load();
            WalkingZombieController walkingZombieController = loader.getController();

            Zombie randomZombie = ZombieFactory.createZombie();
            WalkingZombie walkingZombie = new WalkingZombie(randomZombie);
            walkingZombieController.setWz(walkingZombie);

            String imagePath1 = "@../../assets/Zombies/PVZ_" + randomZombie.getName() + ".png";
            Image image1 = new Image(imagePath1);
            walkingZombieController.getZombieImageView1().setImage(image1);
            walkingZombieController.getZombieImageView1().setFitWidth(image1.getWidth());
            walkingZombieController.getZombieImageView1().setFitHeight(image1.getHeight());

            if (randomZombie instanceof NewspaperZombie || randomZombie instanceof PoleVaultingZombie) {
                String imagePath2 = "@../../assets/Zombies/PVZ_" + randomZombie.getName() + "2.png";
                Image image2 = new Image(imagePath2);
                walkingZombieController.getZombieImageView2().setImage(image2);
                walkingZombieController.getZombieImageView2().setOpacity(0);
                walkingZombieController.getZombieImageView2().setFitWidth(image2.getWidth());
                walkingZombieController.getZombieImageView2().setFitHeight(image2.getHeight());
            }

            int[] values = {70, 230, 380, 530, 700, 830};
            Random random = new Random();
            int randomIndex = random.nextInt(values.length);
            int randomValue = values[randomIndex];

            zombiePane.setLayoutX(1900);
            zombiePane.setLayoutY(randomValue);
//            zombiePane.getChildren().add(walkingZombie);

            zombies.add(walkingZombieController);
            zombieArea.getChildren().add(zombiePane);

            walkingZombieController.setMoving(true);

            int testnum = 0;
            if (randomValue == 70){
                testnum = 1;
            } else if (randomValue == 230) {
                testnum = 2;
            } else if (randomValue == 380) {
                testnum = 3;
            } else if (randomValue == 530) {
                testnum = 4;
            } else if (randomValue == 700) {
                testnum = 5;
            } else if (randomValue == 830) {
                testnum = 6;
            }
            System.out.println("Zombie yang tercetak: " + randomZombie.getClass().getSimpleName() + "   Di-spawn di tiles row: " + testnum);

            startZombieMovement(walkingZombieController);
        } catch (IOException e){
            e.printStackTrace();
        }

//            startZombieMovement((WalkingZombieController) zombieArea.getChildren().get(zombies.size()-1));
//            startZombieMovement((AnchorPane) zombieArea.getChildren().get(zombies.size()-1));


    }

    private void startZombieMovement(WalkingZombieController walkingZombieController) {
        Thread movementThread = new Thread(() -> {
           while (!isGameOver) {
               try {
                   Thread.sleep(100);
                   Platform.runLater(() -> {
                       if(walkingZombieController.isMoving()) {
                           walkingZombieController.moveZombie(1);
                           System.out.println("Zombie moved to x: " + walkingZombieController.getZombieRoot().getLayoutX());
                       }
                   });

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        movementThread.start();
    }

//        if(walkingZombie.getZombie() instanceof NormalZombie){
//            setupImageView(imageZombie, 152, 245, -100, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof ConeheadZombie){
//            setupImageView(imageZombie, 151, 290, -150, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof BucketheadZombie){
//            setupImageView(imageZombie, 158, 275, -135, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof DuckyTubeZombie){
//            setupImageView(imageZombie, 154, 245, -100, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof FootballZombie){
//            setupImageView(imageZombie, 257, 275, -125, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof JackInTheBoxZombie){
//            setupImageView(imageZombie, 185, 245, -100, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof DolphinRiderZombie){
//            setupImageView(imageZombie, 203, 245, -100, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof ScreenDoorZombie){
//            setupImageView(imageZombie, 298, 240, -100, 2090);
//        }
//        else if(walkingZombie.getZombie() instanceof PoleVaultingZombie){
//            setupImageView(imageZombie, 320, 245, -100, 2090);
//            imageZombie2 = new ImageView(walkingZombie.getZombieimg2().getImage());
//            setupImageView(imageZombie2, 211, 250, -110, 2090);
//            imageZombie2.setOpacity(0);
//        }
//        else if(walkingZombie.getZombie() instanceof NewspaperZombie){
//            setupImageView(imageZombie, 180, 245, -100, 2090);
//            imageZombie2 = new ImageView(walkingZombie.getZombieimg2().getImage());
//            setupImageView(imageZombie2, 245, 245, -120, 2090);
//            imageZombie2.setOpacity(0);
//        }

//        int[] values = {70, 230, 380, 530, 700, 830};
//
//
//        Random random = new Random();
//
//        // Menghasilkan indeks acak
//        int randomIndex = random.nextInt(values.length);
//
//        // Mengambil nilai dari array berdasarkan indeks acak
//        int randomValue = values[randomIndex];
//
//        System.out.println("Zombie yang tercetak: " + walkingZombie.getZombie().getClass().getSimpleName() + "   Di-spawn di tiles: " + randomValue);

//        walkingZombie.setMoving(true);
//
//        zombieArea.getChildren().add(walkingZombie);
//        if(walkingZombie.getZombie() instanceof PoleVaultingZombie || walkingZombie.getZombie() instanceof NewspaperZombie){
//            zombieArea.getChildren().add(imageZombie2);
//        }
//        zombieArea.getChildren().add(imageZombie);


    public void stopSpawning() {
        isGameOver = true;
    }

//    public void setupImageView(ImageView img, int fitWidth, int fitHeight, int layoutY, int layoutX){
//        img.setFitWidth(fitWidth);
//        img.setFitHeight(fitHeight);
//        img.setLayoutX(layoutX);
//        img.setLayoutY(layoutY);
//    }
}
