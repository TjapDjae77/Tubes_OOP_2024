package source.Characters.Zombie;

public class PoleVaultingZombie extends Zombie {
    private boolean hasPole;

    public PoleVaultingZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Pole Vaulting Zombie", 175, 100, 1, position, false, speed);
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole) {
        this.hasPole = hasPole;
    }
}