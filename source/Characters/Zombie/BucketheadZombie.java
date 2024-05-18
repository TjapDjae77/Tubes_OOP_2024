package source.Characters.Zombie;

public class BucketheadZombie extends Zombie {
    private boolean Buckethead;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, false, 10, 8, 0);
        setCurrentColumn(randomColumn());
        this.Buckethead = true;
    }

    public void removeBucket() {
        if (this.health < 100 && Buckethead) {
            Buckethead = false; 
            System.out.println("The bucket has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Buckethead : " + Buckethead);
    }
}
