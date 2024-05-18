package source.Characters.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie() {
        super("Normal Zombie", 125, 100, 1, false, 10, 0, 10);
        setCurrentColumn(randomColumn());
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