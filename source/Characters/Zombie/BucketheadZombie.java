package source.Characters.Zombie;

public class BucketheadZombie extends Zombie {
    private boolean hasBuckethead;

    public BucketheadZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Buckethead Zombie", 300, 100, 1, position, false, speed);
    }

    public boolean getHasBuckethead() {
        return hasBuckethead;
    }

    public void setHasBuckethead(boolean hasBuckethead) {
        this.hasBuckethead = hasBuckethead;
    }
}
