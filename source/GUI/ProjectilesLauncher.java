package source.GUI;




import javafx.fxml.FXMLLoader;
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

    private List<Projectile> projectiles;

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

            if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Grass")){
                String type = null;
                if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Peashooter){
                    type = "Pea";
                } else if (((InformationPlant)targetPane.getChildren().get(1)).getPlant() instanceof Snowpea) {
                    type = "Snow";
                }
                InformationPlant infoPlant = (InformationPlant)targetPane.getChildren().get(1);
                Projectile projectile = new Projectile(infoPlant.getPlant().getAttackDamage(), 48, infoPlant.getPlant().getCurrentRow(), infoPlant.getPlant().getCurrentColumn(), type);
            } else if (((TilesPane)targetPane.getChildren().getFirst()).getTiles().getTilesType().equals("Pool")) {
                String type;
                if (((InformationPlant)targetPane.getChildren().get(4)).getPlant() instanceof Peashooter){
                    type = "Pea";
                } else if (((InformationPlant)targetPane.getChildren().get(4)).getPlant() instanceof Snowpea) {
                    type = "Snow";
                }
            }



        } catch (IOException e){
            e.printStackTrace();
        }

    }


//    public boolean isZombieInRow(int row) {
//        return getZombiesInRow(row).size() > 0;
//    }


}
