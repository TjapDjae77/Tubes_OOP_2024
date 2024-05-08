package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class BucketheadZombie extends Zombie {
    private boolean hasBuckethead;

    public BucketheadZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Buckethead Zombie", 300, damage, 1, 100, position, false, speed);
    }

    public boolean getHasBuckethead() {
        return hasBuckethead;
    }

    public void setHasBuckethead(boolean hasBuckethead) {
        this.hasBuckethead = hasBuckethead;
    }
    
    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}
