package source.GUI;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class WalkingZombieController extends AnchorPane{

    @FXML
    private AnchorPane zombieRoot;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    private WalkingZombie wz = null;

    private boolean moving = true;
    private boolean isDead = false;

    public AnchorPane getZombieRoot(){
        return zombieRoot;
    }

    private WalkingZombie getWalkingZombie(){
        return wz;
    }

    public ImageView getZombieImageView1(){
        return img1;
    }

    public ImageView getZombieImageView2(){
        return img2;
    }

    public void setWz(WalkingZombie wz){
        this.wz = wz;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void moveZombie(double deltaX) {
        double currentX = zombieRoot.getLayoutX();
        zombieRoot.setLayoutX(currentX - deltaX);
    }

}