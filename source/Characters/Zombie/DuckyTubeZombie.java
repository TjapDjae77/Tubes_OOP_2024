package source.Characters.Zombie;

public class DuckyTubeZombie extends Zombie {

    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, true, 10, 0, 10);
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
