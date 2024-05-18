package source.Characters.Zombie;

public class BucketheadZombie extends Zombie {
    private boolean Buckethead;

    public BucketheadZombie() {
        super("Buckethead Zombie", 300, 100, 1, false, 10, 0, 10);
        this.Buckethead = true;
    }

    public void removeBucket() {
        if (getHealth() < 100 && Buckethead) {
            Buckethead = false; 
            System.out.println("The bucket has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + getOriginalSpeed());
        System.out.println("Buckethead : " + Buckethead);
    }
}
