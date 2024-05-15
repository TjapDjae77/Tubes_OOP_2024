package source.Characters.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie(String name, int health, int attack_speed, int attack_damage, boolean is_aquatic, int speed) {
        super("Normal Zombie", 125, 100, 1, false, 5);
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