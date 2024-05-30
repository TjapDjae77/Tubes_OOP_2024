package source.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import source.Characters.Plants.Projectile;

public class MovingProjectiles extends Pane {
    private Projectile projectile;
    private ImageView img;


    public MovingProjectiles(Projectile projectile) {
        this.projectile = projectile;
        String imagePath = "@../../assets/Projectiles/PVZ_"+ projectile.getType() + "Projectiles.png";
        img = new ImageView(new Image(imagePath));
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

}
