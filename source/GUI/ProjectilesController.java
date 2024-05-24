package source.GUI;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import source.Characters.Plants.Projectile;

public class ProjectilesController extends Pane {
    @FXML
    private ImageView img1;

    @FXML
    private Pane projectilesPane;

    private Projectile projectile = null;

    public Pane getProjectilesPane() { return projectilesPane; }

    public ImageView getImg1() { return img1; }

    public void setProjectile(Projectile projectile) { this.projectile = projectile; }

    public Projectile getProjectile() { return projectile; }

    public void moveProjectile(double deltaX) {
        double currentX = projectilesPane.getLayoutX();
        projectilesPane.setLayoutX(currentX - deltaX);
    }
}
