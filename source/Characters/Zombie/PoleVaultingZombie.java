package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class PoleVaultingZombie extends Zombie {
    private boolean hasPole;

    public PoleVaultingZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Pole Vaulting Zombie", 175, damage, 1, 100, position, false, speed);
    }

    public boolean getHasPole() {
        return hasPole;
    }

    public void setHasPole(boolean hasPole) {
        this.hasPole = hasPole;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}