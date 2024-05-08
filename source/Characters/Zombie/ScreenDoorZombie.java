package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class ScreenDoorZombie extends Zombie {
    private boolean hasDoor;

    public ScreenDoorZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Screen Door Zombie", 250, damage, 1, 100, position, false, speed);
    }

    public boolean getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor) {
        this.hasDoor = hasDoor;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}
