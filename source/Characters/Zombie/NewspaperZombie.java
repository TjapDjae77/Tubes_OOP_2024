package source.Characters.Zombie;

public class NewspaperZombie extends Zombie {

    public NewspaperZombie() {
        super("Newspaper Zombie", 250, 100, 1, false, 5);
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