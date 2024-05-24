package source.GUI;

import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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

public class WalkingZombieSpawner {

    private static WalkingZombieSpawner instanceSpawner;
    private static final int MAX_ZOMBIE = 10;
    private static final double SPAWN_PROBABILITY = 1;
    private static final int SPAWN_INTERVAL = 3000;
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

    public void setPlantPositions(boolean setter, int row, int col){
        plantPositions[row][col] = setter;
        System.out.println("PLANT POSITION ON ROW " + row + " COL " + col + " : " + plantPositions[row][col]);
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
                    Thread.currentThread().interrupt();
                    System.out.println("GAME SELESAI");

                }
            }
        });
        spawningThread.start();
    }

    private void trySpawnZombie() throws IOException {
        if(zombies.size() < 1 && Math.random() < SPAWN_PROBABILITY){
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

            if (randomZombie instanceof NewspaperZombie || randomZombie instanceof PoleVaultingZombie) {
                String imagePath2 = "@../../assets/Zombies/PVZ_" + randomZombie.getName() + "2.png";
                Image image2 = new Image(imagePath2);
                walkingZombieController.getZombieImageView2().setImage(image2);
                walkingZombieController.getZombieImageView2().setOpacity(0);
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
                   Thread.sleep(48);
                   Platform.runLater(() -> {
                       if(walkingZombieController.isMoving()) {
                           int rowNow = walkingZombieController.getWalkingZombie().getZombie().getCurrentRow();
                           int colNow = walkingZombieController.getWalkingZombie().getZombie().getCurrentColumn();
                           ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
                           Pane targetPane = mainGameController.getPaneAt(rowNow, colNow);

                           if (!isPlantInFront(walkingZombieController)) {
                               walkingZombieController.moveZombie(1);
                               double layoutX = walkingZombieController.getZombiePane().getLayoutX();
//                               System.out.println("Zombie " + zombies.size() + " moved to x: " + walkingZombieController.getZombiePane().getLayoutX());
                               if (layoutX >= 1560 && layoutX < 1810) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(9);
                               } else if (layoutX >= 1360 && layoutX < 1560) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(8);
                               } else if (layoutX >= 1180 && layoutX < 1360) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(7);
                               } else if (layoutX >= 980 && layoutX < 1180) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(6);
                               } else if (layoutX >= 790 && layoutX < 980) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(5);
                               } else if (layoutX >= 570 && layoutX < 790) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(4);
                               } else if (layoutX >= 420 && layoutX < 570) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(3);
                               } else if (layoutX >= 220 && layoutX < 420) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(2);
                               } else if (layoutX >= 10 && layoutX < 220) {
                                   walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(1);
                               } else if (layoutX < 10) {
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
//                               System.out.println("Zombie " + zombies.size() + " moved to x: " + walkingZombieController.getZombiePane().getLayoutX());
                                   if (layoutX >= 1560 && layoutX < 1810) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(9);
                                   } else if (layoutX >= 1360 && layoutX < 1560) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(8);
                                   } else if (layoutX >= 1180 && layoutX < 1360) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(7);
                                   } else if (layoutX >= 980 && layoutX < 1180) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(6);
                                   } else if (layoutX >= 790 && layoutX < 980) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(5);
                                   } else if (layoutX >= 570 && layoutX < 790) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(4);
                                   } else if (layoutX >= 420 && layoutX < 570) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(3);
                                   } else if (layoutX >= 220 && layoutX < 420) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(2);
                                   } else if (layoutX >= 10 && layoutX < 220) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(1);
                                   } else if (layoutX < 10) {
                                       walkingZombieController.getWalkingZombie().getZombie().setCurrentColumn(0);
                                   }

                                   if (walkingZombieController.getZombiePane().getLayoutX() < -30) {
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

    public void zStartAttack(WalkingZombieController walkingZombieController, Pane targetPane ) {
        new Thread(() -> {
            while (isPlantInFront(walkingZombieController)) {
                Platform.runLater(() -> {
                    // Get the plant in front of the zombie
                    ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
                    int sumOfPlant = mainGameController.sumOfInformationPlant(targetPane);
                    if (sumOfPlant == 1) { // INI HARUS GW SESUAIKAN SAMA CONTROLLER MAIN GAME, SOALNYA TANAMAN BISA LEBIH DARI 1
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

                        if(plant2.getHealth() <= 0){
                            System.out.println("Plant Atas destroyed!");
                            deadPlant(walkingZombieController, targetPane, 2);
                        }

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
