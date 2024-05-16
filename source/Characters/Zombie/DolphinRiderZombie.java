package source.Characters.Zombie;

public class DolphinRiderZombie extends Zombie {

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true, 5, 8, 0);
        setCurrentColumn(randomColumn());
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