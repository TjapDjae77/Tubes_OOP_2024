package source.GUI;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import source.Characters.Zombie.Zombie;

public class WalkingZombie extends Pane {
    private Zombie zombie;
    private ImageView zombieimg1;
    private ImageView zombieimg2 = null;
    private int x=0;
    private Thread movementThread;



    public WalkingZombie(Zombie zombie){
        this.zombie = zombie;
        String imagePath = "@../../assets/Zombies/PVZ_"+ zombie.getName() + ".png";
        zombieimg1 = new ImageView(new Image(imagePath));
        if(zombie.getName().equals("Newspaper Zombie") || zombie.getName().equals("Pole Vaulting Zombie") || zombie.getName().equals("Dolphin Rider Zombie")){
            imagePath = "@../../assets/Zombies/PVZ_"+ zombie.getName() + "2.png";
            zombieimg2 = new ImageView(new Image(imagePath));
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x){
        this.x = x;
    }


    public Zombie getZombie() {
        return zombie;
    }

    public ImageView getZombieimg1() {
        return zombieimg1;
    }

    public ImageView getZombieimg2() {
        return zombieimg2;
    }
}
