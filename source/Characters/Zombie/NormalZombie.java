package source.Characters.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, false, 10, 0, 10);
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