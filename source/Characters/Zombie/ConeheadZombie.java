package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class ConeheadZombie extends Zombie {
    private boolean hasCone;

    public ConeheadZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Conehead Zombie", 250, damage, 1, 100, position, false, speed);
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}