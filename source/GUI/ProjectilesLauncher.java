package source.GUI;




import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import source.Characters.Plants.Peashooter;
import source.Characters.Plants.Projectile;
import source.Characters.Plants.Snowpea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectilesLauncher {

    private static ProjectilesLauncher instanceLauncher;

    private List<ProjectilesController> projectiles;

    private Thread movementThread;

    private static final int PANEL9 = 1620;
    private static final int PANEL8 = 1420;
    private static final int PANEL7 = 1240;
    private static final int PANEL6 = 1040;
    private static final int PANEL5 = 850;
    private static final int PANEL4 = 630;
    private static final int PANEL3 = 480;
    private static final int PANEL2 = 280;
    private static final int PANEL1 = 70;


    private ProjectilesLauncher() {
        projectiles = new ArrayList<>();
    }

    public static ProjectilesLauncher getInstance() {
        if (instanceLauncher == null) {
            instanceLauncher = new ProjectilesLauncher();
        }
        return instanceLauncher;
    }

    public void launchProjectiles(Pane targetPane) {
        ControllerMainGame mainGameController = ControllerMainGame.getInstanceGame();
        AnchorPane projectilesArea = mainGameController.getProjectilesArea();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("projectiles.fxml"));
        try {
            Pane projectilePane = loader.load();
            ProjectilesController projectilesController = loader.getController();

            Projectile projectile = null;
            InformationPlant infoPlant = null;

            if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Grass")){
                String type = null;
                if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Peashooter){
                    type = "Pea";
                } else if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Snowpea) {
                    type = "Snow";
                }
                infoPlant = (InformationPlant)targetPane.getChildren().get(1);
                projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
            } else if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Pool")) {
                String type = null;
                if (targetPane.getChildren().size() > 4){
                    if (!(targetPane.getChildren().get(4) instanceof ImageView)) {
                        if (((InformationPlant) targetPane.getChildren().get(4)).getPlant() instanceof Peashooter) {
                            type = "Pea";
                        } else if (((InformationPlant) targetPane.getChildren().get(4)).getPlant() instanceof Snowpea) {
                            type = "Snow";
                        }
                        infoPlant = (InformationPlant)targetPane.getChildren().get(4);
                        projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
                    }
                }
                else {
                    type = "Shroom";
                    infoPlant = (InformationPlant)targetPane.getChildren().get(1);
                    projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
                }

            }
            projectilesController.setProjectile(projectile);

            String imagePath = "@../../assets/Zombies/PVZ_" + projectile.getType() + "Projectiles.png";
            Image image = new Image(imagePath);
            projectilesController.getImg1().setImage(image);
            projectilesController.getImg1().setFitWidth(image.getWidth());
            projectilesController.getImg1().setFitHeight(image.getHeight());



            if(infoPlant.getPlant().getCurrentRow() == 0){
                projectilePane.setLayoutY(60);
            } else if (infoPlant.getPlant().getCurrentRow() == 1) {
                projectilePane.setLayoutY(230);
            } else if (infoPlant.getPlant().getCurrentRow() == 2) {
                projectilePane.setLayoutY(380);
            } else if (infoPlant.getPlant().getCurrentRow() == 3) {
                projectilePane.setLayoutY(530);
            } else if (infoPlant.getPlant().getCurrentRow() == 4) {
                projectilePane.setLayoutY(700);
            } else if (infoPlant.getPlant().getCurrentRow() == 5) {
                projectilePane.setLayoutY(830);
            }

            if(infoPlant.getPlant().getCurrentColumn() == 1){
                projectilePane.setLayoutX(PANEL1);
            } else if (infoPlant.getPlant().getCurrentColumn() == 2) {
                projectilePane.setLayoutX(PANEL2);
            } else if (infoPlant.getPlant().getCurrentColumn() == 3) {
                projectilePane.setLayoutX(PANEL3);
            } else if (infoPlant.getPlant().getCurrentColumn() == 4) {
                projectilePane.setLayoutX(PANEL4);
            } else if (infoPlant.getPlant().getCurrentColumn() == 5) {
                projectilePane.setLayoutX(PANEL5);
            } else if (infoPlant.getPlant().getCurrentColumn() == 6) {
                projectilePane.setLayoutX(PANEL6);
            } else if (infoPlant.getPlant().getCurrentColumn() == 7) {
                projectilePane.setLayoutX(PANEL7);
            } else if (infoPlant.getPlant().getCurrentColumn() == 8) {
                projectilePane.setLayoutX(PANEL8);
            } else if (infoPlant.getPlant().getCurrentColumn() == 9) {
                projectilePane.setLayoutX(PANEL9);
            }

            projectilePane.setPadding(new Insets(0));

            projectiles.add(projectilesController);
            projectilesArea.getChildren().add(projectilePane);

            projectilesController.setMoving(true);

            moveRight(projectilesController);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private void moveRight(ProjectilesController projectilesController) {
        new Thread(() -> {
            try {
                Thread.sleep(48);
                Platform.runLater(() -> {
                    if (projectilesController.isMoving()) {
                        projectilesController.moveProjectile(1);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


//    public boolean isZombieInRow(int row) {
//        return getZombiesInRow(row).size() > 0;
//    }


}
