package source.Characters.Zombie;

public class PoleVaultingZombie extends Zombie {
    private boolean hasPole;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, 5);
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole) {
        this.hasPole = hasPole;
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