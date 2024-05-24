package source.GUI;

import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Pane;
import source.Characters.Plants.Spikeweed;
import source.Characters.Plants.*;
import source.Characters.Zombie.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WalkingZombieSpawner {

    private static WalkingZombieSpawner instanceSpawner;
    private static final int MAX_ZOMBIE = 10;
    private static final double SPAWN_PROBABILITY = 0.3;
    private static final int SPAWN_INTERVAL = 3000;

    private static final int BATASKIRI10 = 1810;
    private static final int BATASKIRI9 = 1560;
    private static final int BATASKIRI8 = 1360;
    private static final int BATASKIRI7 = 1180;
    private static final int BATASKIRI6 = 980;
    private static final int BATASKIRI5 = 790;
    private static final int BATASKIRI4 = 570;
    private static final int BATASKIRI3 = 420;
    private static final int BATASKIRI2 = 220;
    private static final int BATASKIRI1 = 10;

    private ArrayList<WalkingZombieController> zombies = new ArrayList<>();
    private Thread spawningThread;
    private Thread movementThread;
    private boolean isGameOver = false;
    private static boolean[][] plantPositions;

    static {
        plantPositions = new boolean[6][11];
        for (int i = 0; i < plantPositions.length; i++) {
            for (int j = 0; j < plantPositions[i].length; j++) {
                plantPositions[i][j] = false;
            }
        }
    }

    public static WalkingZombieSpawner getInstanceSpawner() {
        if (instanceSpawner == null) {
            instanceSpawner = new WalkingZombieSpawner();
        }
        return instanceSpawner;
    }

    public ArrayList<WalkingZombieController> getZombies() {
        return zombies;
    }

    public void setPlantPositions(boolean setter, int row, int col){
        plantPositions[row][col] = setter;

    }

    public void startSpawning() {
        Timer timer = new Timer();
        TimerTask startSpawnTime = new TimerTask() {
            public void run() {
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

                        }
                        catch(InterruptedException e){
                            Thread.currentThread().interrupt();


                        }
                    }
                });
                spawningThread.start();
            }
        };
        TimerTask stopSpawnTime = new TimerTask() {
            public void run() {
                stopSpawning();
            }
        };

        timer.schedule(startSpawnTime, 10000);
        timer.schedule(stopSpawnTime, 160000);
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
            Pane zombiePane = loader.load();
            WalkingZombieController walkingZombieController = loader.getController();

            int[] values = {60, 230, 380, 530, 700, 830};
            Random random = new Random();
            int randomIndex = random.nextInt(values.length);
            int randomValue = values[randomIndex];

            Zombie randomZombie;
            if(randomValue == 380 || randomValue == 530){
                randomZombie = ZombieFactory.createZombie(true);
            }
            else{
                randomZombie = ZombieFactory.createZombie(false);
            }
            randomZombie.setCurrentRow(randomIndex);
            WalkingZombie walkingZombie = new WalkingZombie(randomZombie);
            walkingZombieController.setWz(walkingZombie);

            String imagePath1 = "@../../assets/Zombies/PVZ_" + randomZombie.getName() + ".png";
            Image image1 = new Image(imagePath1);
            walkingZombieController.getZombieImageView1().setImage(image1);
            walkingZombieController.getZombieImageView1().setFitWidth(image1.getWidth());
            walkingZombieController.getZombieImageView1().setFitHeight(image1.getHeight());

            if (randomZombie instanceof NewspaperZombie || randomZombie instanceof PoleVaultingZombie || randomZombie instanceof DolphinRiderZombie) {
                String imagePath2 = "@../../assets/Zombies/PVZ_" + randomZombie.getName() + "2.png";
                Image image2 = new Image(imagePath2);
                walkingZombieController.getZombieImageView2().setImage(image2);
                walkingZombieController.getZombieImageView2().setOpacity(0);
                if(randomZombie instanceof DolphinRiderZombie){
                    walkingZombieController.getZombieImageView2().setLayoutY(70);
                } else if (randomZombie instanceof PoleVaultingZombie) {
                    walkingZombieController.getZombieImageView2().setLayoutX(40);
                }
                walkingZombieController.getZombieImageView2().setFitWidth(image2.getWidth());
                walkingZombieController.getZombieImageView2().setFitHeight(image2.getHeight());
            }

            zombiePane.setLayoutX(2090);
            zombiePane.setLayoutY(randomValue);

            zombiePane.setPadding(new Insets(0));

            zombies.add(walkingZombieController);
            zombieArea.getChildren().add(zombiePane);

            walkingZombieController.setMoving(true);

            int testnum = 0;
            if (randomValue == 60){
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
    }

    public boolean isPlantInFront(WalkingZombieController zombie){
        int row = zombie.getWalkingZombie().getZombie().getCurrentRow();
        int col = zombie.getWalkingZombie().getZombie().getCurrentColumn();
        System.out.println("TYPE: "+ zombie.getWalkingZombie().getZombie().getClass().getSimpleName() + " ROW: " + (row) + ", COL: " + (col));
        return plantPositions[row][col];
    }

    private void startZombieMovement(WalkingZombieController walkingZombieController) {
        movementThread = new Thread(() -> {
           while (!isGameOver) {
               try {
                   Thread.sleep(48 * (10/((int)walkingZombieController.getWalkingZombie().getZombie().getOriginalSpeed())));
                   Platform.runLater(() -> {
                       if(walkingZombieController.isMoving()) {
                           int rowNow = walkingZombieController.getWalkingZombie().getZombie().getCurrentRow();
                           int colNow = walkingZombieController.getWalkingZombie().getZombie().getCurrentColumn();
                           ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
                           Pane targetPane = mainGameController.getPaneAt(rowNow, colNow);

                           if (!isPlantInFront(walkingZombieController)) {
                               walkingZombieController.moveZombie(1);
                               double layoutX = walkingZombieController.getZombiePane().getLayoutX();

                               if (layoutX >= BATASKIRI9 && layoutX < BATASKIRI10) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(9);
                               } else if (layoutX >= BATASKIRI8 && layoutX < BATASKIRI9) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(8);
                               } else if (layoutX >= BATASKIRI7 && layoutX < BATASKIRI8) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(7);
                               } else if (layoutX >= BATASKIRI6 && layoutX < BATASKIRI7) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(6);
                               } else if (layoutX >= BATASKIRI5 && layoutX < BATASKIRI6) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(5);
                               } else if (layoutX >= BATASKIRI4 && layoutX < BATASKIRI5) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(4);
                               } else if (layoutX >= BATASKIRI3 && layoutX < BATASKIRI4) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(3);
                               } else if (layoutX >= BATASKIRI2 && layoutX < BATASKIRI3) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(2);
                               } else if (layoutX >= BATASKIRI1 && layoutX < BATASKIRI2) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(1);
                               } else if (layoutX < BATASKIRI1) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(0);
                               }

                               if(walkingZombieController.getZombiePane().getLayoutX() < -30) {
                                   mainGameController.stopGame(false);
                                   stopSpawning();
                               }
                           } else if (targetPane.getChildren().size() > 2) {
                               if (targetPane.getChildren().get(1) instanceof ImageView || ((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Spikeweed) {
                                   walkingZombieController.moveZombie(1);
                                   double layoutX = walkingZombieController.getZombiePane().getLayoutX();

                                   if (layoutX >= BATASKIRI9 && layoutX < BATASKIRI10) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(9);
                                   } else if (layoutX >= BATASKIRI8 && layoutX < BATASKIRI9) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(8);
                                   } else if (layoutX >= BATASKIRI7 && layoutX < BATASKIRI8) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(7);
                                   } else if (layoutX >= BATASKIRI6 && layoutX < BATASKIRI7) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(6);
                                   } else if (layoutX >= BATASKIRI5 && layoutX < BATASKIRI6) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(5);
                                   } else if (layoutX >= BATASKIRI4 && layoutX < BATASKIRI5) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(4);
                                   } else if (layoutX >= BATASKIRI3 && layoutX < BATASKIRI4) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(3);
                                   } else if (layoutX >= BATASKIRI2 && layoutX < BATASKIRI3) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(2);
                                   } else if (layoutX >= BATASKIRI1 && layoutX < BATASKIRI2) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(1);
                                   } else if (layoutX < BATASKIRI1) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(0);
                                   }

                                   if(walkingZombieController.getZombiePane().getLayoutX() < -30) {
                                       mainGameController.stopGame(false);
                                       stopSpawning();
                                   }
                               }
                               else {
                                   walkingZombieController.setMoving(false);
                                   zStartAttack(walkingZombieController,targetPane);
                               }
                           }
                       }

                   });

               } catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
                   System.out.println("ZOMBIE BERHENTI");
               }
           }
        });
        movementThread.start();
    }

    public void zStartAttack(WalkingZombieController walkingZombieController, Pane targetPane) {
        new Thread(() -> {
            while (isPlantInFront(walkingZombieController)) {
                Platform.runLater(() -> {
                    // Get the plant in front of the zombie
                    ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
                    if(walkingZombieController.getWalkingZombie().getZombie() instanceof PoleVaultingZombie){
                        PoleVaultingZombie pvz = (PoleVaultingZombie) walkingZombieController.getWalkingZombie().getZombie();
                        if (pvz.isPole()){
                            jumping(mainGameController, targetPane, walkingZombieController);
                        }
                        else {
                            normalAttacking(mainGameController, targetPane, walkingZombieController);
                        }
                    } else if (walkingZombieController.getWalkingZombie().getZombie() instanceof DolphinRiderZombie) {
                        DolphinRiderZombie drz = (DolphinRiderZombie) walkingZombieController.getWalkingZombie().getZombie();
                        if(drz.isDolphin()){
                            jumping(mainGameController, targetPane, walkingZombieController);
                        }
                        else {
                            normalAttacking(mainGameController, targetPane, walkingZombieController);
                        }
                    } else {
                        normalAttacking(mainGameController, targetPane, walkingZombieController);
                    }

                    if (walkingZombieController.getWalkingZombie().getZombie() instanceof JackInTheBoxZombie){
                        walkingZombieController.setDead(true);
                    }
                });
                try {
                    Thread.sleep(walkingZombieController.getWalkingZombie().getZombie().getAttackSpeed() * 1000L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
                if (!isPlantInFront(walkingZombieController)) {
                    break;
                }
            }
        }).start();
    }

    public void deadPlant(WalkingZombieController walkingZombieController, Pane targetPane, int sumOfPlant) {
        if (sumOfPlant == 1) {
            int row = walkingZombieController.getWalkingZombie().getZombie().getCurrentRow();
            int col = walkingZombieController.getWalkingZombie().getZombie().getCurrentColumn();

            setPlantPositions(false, row, col);
            targetPane.getChildren().remove(1, 4);

        }
        else if (sumOfPlant == 2) {
            targetPane.getChildren().remove(4, 7);
        }

        else{ // BIKIN SEMUA HILANG
            int row = walkingZombieController.getWalkingZombie().getZombie().getCurrentRow();
            int col = walkingZombieController.getWalkingZombie().getZombie().getCurrentColumn();
            setPlantPositions(false, row, col);
            targetPane.getChildren().remove(1, 7);
        }

    }

    public void deadZombie(WalkingZombieController walkingZombieController){
        movementThread.interrupt();
        int mustBeDeletedIndex = zombies.indexOf(walkingZombieController);
        zombies.remove(mustBeDeletedIndex);
        for (int i = 0; i < zombies.size(); i++) {
            startZombieMovement(zombies.get(i));
        }
    }



    public void normalAttacking(ControllerMainGame mainGameController, Pane targetPane, WalkingZombieController walkingZombieController) {
        int sumOfPlant = mainGameController.sumOfInformationPlant(targetPane);
        if (sumOfPlant == 1) {
            Plants plant = ((InformationPlant) targetPane.getChildren().get(1)).getPlant();
            System.out.println("Jenis plant: " + plant.getName());
            // Damage the plant
            plant.setHealth(plant.getHealth() - walkingZombieController.getWalkingZombie().getZombie().getAttackDamage());
            System.out.println("Zombie attacked plant. Plant health: " + plant.getHealth());
            // Check if the plant is destroyed
            if (plant.getHealth() <= 0) {
                System.out.println("Plant destroyed!");
                deadPlant(walkingZombieController, targetPane, 1);
                walkingZombieController.setMoving(true);
            }

        } else if (sumOfPlant == 2) {
            Plants plant2 = ((InformationPlant) targetPane.getChildren().get(4)).getPlant();
            System.out.println("Jenis plant: " + plant2.getName());

            // Damage the plant
            plant2.setHealth(plant2.getHealth() - walkingZombieController.getWalkingZombie().getZombie().getAttackDamage());
            System.out.println("Zombie attacked plant. Plant health: " + plant2.getHealth());

            if (plant2.getHealth() <= 0) {
                System.out.println("Plant Atas destroyed!");
                deadPlant(walkingZombieController, targetPane, 2);
            }
        }
    }

    public void jumping(ControllerMainGame mainGameController, Pane targetPane, WalkingZombieController walkingZombieController) {
         if(walkingZombieController.getWalkingZombie().getZombie() instanceof PoleVaultingZombie) {
             if (!(((InformationPlant) targetPane.getChildren().get(1)).getPlant() instanceof Tallnut)) {
                 setupPosition(mainGameController, walkingZombieController);
             }
         }
        else if (walkingZombieController.getWalkingZombie().getZombie() instanceof DolphinRiderZombie) {
            if(targetPane.getChildren().size() > 4) {
                if (!(targetPane.getChildren().get(4) instanceof ImageView)) { // Kalo bukan foreshadowing
                    if(!(((InformationPlant) targetPane.getChildren().get(4)).getPlant() instanceof Tallnut)) { // Kalo bukan Tallnut
                        setupPosition(mainGameController, walkingZombieController);

                    }
                }
                else { // Cuma satu tanaman (dan itu pasti lilypad)
                    setupPosition(mainGameController, walkingZombieController);
                }
            }
            else { // Cuma satu tanaman (dan itu pasti lilypad)
                setupPosition(mainGameController, walkingZombieController);
            }
        }

        if (walkingZombieController.getWalkingZombie().getZombie() instanceof PoleVaultingZombie){
            PoleVaultingZombie pvz = (PoleVaultingZombie) walkingZombieController.getWalkingZombie().getZombie();
            pvz.setPole(false);
        }
        else {
            DolphinRiderZombie drz = (DolphinRiderZombie) walkingZombieController.getWalkingZombie().getZombie();
            drz.setDolphin(false);
        }
        walkingZombieController.getZombieImageView2().setOpacity(1);
        walkingZombieController.getZombieImageView1().setOpacity(0);

        walkingZombieController.setMoving(true);

    }

    public void setupPosition(ControllerMainGame mainGameController, WalkingZombieController walkingZombieController){
        int colnow = walkingZombieController.getWalkingZombie().getZombie().getCurrentColumn();
        int rownow = walkingZombieController.getWalkingZombie().getZombie().getCurrentRow();
        System.out.println("COLUMN SAAT INI: " + colnow);
        walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(colnow - 1);

        if ((colnow - 1) == 8) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI9 - 40);
        } else if ((colnow - 1) == 7) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI8 - 40);
        } else if ((colnow - 1) == 6) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI7 - 40);
        } else if ((colnow - 1) == 5) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI6 - 40);
        } else if ((colnow - 1) == 4) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI5 - 40);
        } else if ((colnow - 1) == 3) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI4 - 40);
        } else if ((colnow - 1) == 2) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI3 - 40);
        } else if ((colnow - 1) == 1) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI2 - 40);
        } else if ((colnow - 1) == 0) {
            walkingZombieController.getZombiePane().setLayoutX(BATASKIRI1 - 40);
        }

        Pane behindJumpPane = mainGameController.getPaneAt(rownow, colnow-1);
        int sumOfPlant = mainGameController.sumOfInformationPlant(behindJumpPane);
        if(sumOfPlant != 0) {
            if (walkingZombieController.getWalkingZombie().getZombie() instanceof DolphinRiderZombie && sumOfPlant == 2) {
                deadPlant(walkingZombieController, behindJumpPane, 3);
            } else{
                deadPlant(walkingZombieController, behindJumpPane, sumOfPlant);
            }
        }
    }

    public void stopSpawning() {
        isGameOver = true;
        spawningThread.interrupt();
        movementThread.interrupt();
    }

}



// DUMP
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
