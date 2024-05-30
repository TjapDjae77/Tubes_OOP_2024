package source.GUI;




import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import source.Characters.Plants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectilesLauncher {

    private static ProjectilesLauncher instanceLauncher;

    private List<ProjectilesController> projectiles;

    private Thread movementThread;

    private static final int PANEL9 = 1790;
    private static final int PANEL8 = 1590;
    private static final int PANEL7 = 1410;
    private static final int PANEL6 = 1210;
    private static final int PANEL5 = 1020;
    private static final int PANEL4 = 800;
    private static final int PANEL3 = 650;
    private static final int PANEL2 = 450;
    private static final int PANEL1 = 240;


    private ProjectilesLauncher() {
        projectiles = new ArrayList<>();
    }

    public static ProjectilesLauncher getInstance() {
        if (instanceLauncher == null) {
            instanceLauncher = new ProjectilesLauncher();
        }
        return instanceLauncher;
    }

    public void launchProjectiles(Plants plant) {
        ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
        AnchorPane projectilesArea = mainGameController.getProjectilesArea();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("projectiles.fxml"));
        try {
            Pane projectilePane = loader.load();
            ProjectilesController projectilesController = loader.getController();

//            if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Grass")){
//                String type = null;
//                if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Peashooter){
//                    type = "Pea";
//                } else if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Snowpea) {
//                    type = "Snow";
//                }
//                infoPlant = (InformationPlant)targetPane.getChildren().get(1);
//                projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
//            } else if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Pool")) {
//                String type = null;
//                if (targetPane.getChildren().size() > 4){
//                    if (!(targetPane.getChildren().get(4) instanceof ImageView)) {
//                        if (((InformationPlant) targetPane.getChildren().get(4)).getPlant() instanceof Peashooter) {
//                            type = "Pea";
//                        } else if (((InformationPlant) targetPane.getChildren().get(4)).getPlant() instanceof Snowpea) {
//                            type = "Snow";
//                        }
//                        infoPlant = (InformationPlant)targetPane.getChildren().get(4);
//                        projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
//                    }
//                }
//                else {
//                    type = "Shroom";
//                    infoPlant = (InformationPlant)targetPane.getChildren().get(1);
//                    projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
//                }
//
//            }
            String type = null;
            if(plant instanceof Peashooter) {
                type = "Pea";
            }
            else if(plant instanceof Snowpea) {
                type = "Snow";
            }
            else if (plant instanceof Seashroom) {
                type = "Shroom";
            }

            Projectile projectile = new Projectile(plant.getAttackDamage(), 48, plant.getCurrentRow(), plant.getCurrentColumn(), type);

            projectilesController.setProjectile(projectile);

            String imagePath = "@../../assets/Projectiles/PVZ_" + projectile.getType() + "Projectiles.png";
            Image image = new Image(imagePath);
            projectilesController.getImg1().setImage(image);
            projectilesController.getImg1().setFitWidth(image.getWidth());
            projectilesController.getImg1().setFitHeight(image.getHeight());



            if(plant.getCurrentRow() == 0){
                projectilePane.setLayoutY(210);
            } else if (plant.getCurrentRow() == 1) {
                projectilePane.setLayoutY(365);
            } else if (plant.getCurrentRow() == 2) {
                projectilePane.setLayoutY(520);
            } else if (plant.getCurrentRow() == 3) {
                projectilePane.setLayoutY(680);
            } else if (plant.getCurrentRow() == 4) {
                projectilePane.setLayoutY(850);
            } else if (plant.getCurrentRow() == 5) {
                projectilePane.setLayoutY(1000);
            }

            if(plant.getCurrentColumn() == 1){
                projectilePane.setLayoutX(PANEL1);
            } else if (plant.getCurrentColumn() == 2) {
                projectilePane.setLayoutX(PANEL2);
            } else if (plant.getCurrentColumn() == 3) {
                projectilePane.setLayoutX(PANEL3);
            } else if (plant.getCurrentColumn() == 4) {
                projectilePane.setLayoutX(PANEL4);
            } else if (plant.getCurrentColumn() == 5) {
                projectilePane.setLayoutX(PANEL5);
            } else if (plant.getCurrentColumn() == 6) {
                projectilePane.setLayoutX(PANEL6);
            } else if (plant.getCurrentColumn() == 7) {
                projectilePane.setLayoutX(PANEL7);
            } else if (plant.getCurrentColumn() == 8) {
                projectilePane.setLayoutX(PANEL8);
            } else if (plant.getCurrentColumn() == 9) {
                projectilePane.setLayoutX(PANEL9);
            }

            projectilePane.setPadding(new Insets(0));

            projectiles.add(projectilesController);
            projectilesArea.getChildren().add(projectilePane);

            System.out.println("PROJECTILE TERCIPTA");

            projectilesController.setMoving(true);

            moveRight(projectilesController);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void moveRight(ProjectilesController projectilesController) {
        movementThread = new Thread(() -> {
            while (projectilesController.isMoving()) {
                try {
                    Thread.sleep(28);
                    Platform.runLater(() -> {
                        if (checkZombieCollision(projectilesController)) {
                            projectilesController.setMoving(false);
                            System.out.println("Projectile berhenti");
                        } else {
                            projectilesController.moveProjectile(1);
                            System.out.println("Projectile di: " + projectilesController.getProjectilesPane().getLayoutX());
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        movementThread.start();
    }

    private boolean checkZombieCollision(ProjectilesController projectilesController) {
        WalkingZombieSpawner wzs = WalkingZombieSpawner.getInstanceSpawner();
        ArrayList<WalkingZombieController> listZombie = wzs.getZombies();

//        boolean collision = false;

        for (WalkingZombieController walkingZombie : listZombie) {
            if (walkingZombie.getWalkingZombie().getZombie().getCurrentRow() == projectilesController.getProjectile().getRow()){
                if (walkingZombie.getWalkingZombie().getZombie().getCurrentColumn() == projectilesController.getProjectile().getColumn()){
//                    collision = true;
                    return true;
                }
            }
        }

        return false;
//        return collision;
    }

//    private boolean checkZombieCollision(ProjectilesController projectilesController) {
//        ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
//        AnchorPane zombiesArea = mainGameController.getZombieArea();
//        List<WalkingZombieController> zombies = zombiesArea.getChildren().stream()
//                .filter(node -> node instanceof Pane)
//                .map(node -> (Pane) node)
//                .flatMap(pane -> pane.getChildren().stream())
//                .filter(node -> node instanceof WalkingZombieController)
//                .map(node -> (WalkingZombieController) node)
//                .collect(Collectors.toList());
//
//        for (WalkingZombieController zombie : zombies) {
//            // Periksa jika zombie berada pada posisi yang sama dengan proyektil
//            if (zombie.getWalkingZombie().getZombie().getCurrentRow() == projectilesController.getProjectile().getRow() &&
//                    zombie.getWalkingZombie().getZombie().getCurrentColumn() == projectilesController.getProjectile().getColumn()) {
//                // Kurangi darah zombie
//                zombie.getWalkingZombie().getZombie().setHealth(zombie.getWalkingZombie().getZombie().getHealth() - projectilesController.getProjectile().getAttackDamage());
//                return true; // Collision detected
//            }
//        }
//        return false; // No collision
//    }




//    public boolean isZombieInRow(int row) {
//        return getZombiesInRow(row).size() > 0;
//    }


}
