package source.Characters.Zombie;

public class ConeheadZombie extends Zombie {
    private boolean hasCone;

    public ConeheadZombie(String name, int health, int damage, int attack_speed, int attack_damage, boolean is_aquatic, int speed) {
        super("Conehead Zombie", 250, 100, 1, false, speed);
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
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