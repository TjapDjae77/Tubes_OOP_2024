package source.Characters.Zombie;

public class DuckyTubeZombie extends Zombie {

    public DuckyTubeZombie() {
        super("Ducky Tube Zombie", 100, 100, 1, true, 10, 0, 10);

    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
    }
}
