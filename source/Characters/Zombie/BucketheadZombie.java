package source.Characters.Zombie;

public class BucketheadZombie extends Zombie {
    private boolean hasBuckethead;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, false, 5);
    }

    public boolean getHasBuckethead() {
        return hasBuckethead;
    }

    public void setHasBuckethead(boolean hasBuckethead) {
        this.hasBuckethead = hasBuckethead;
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
    }
}
