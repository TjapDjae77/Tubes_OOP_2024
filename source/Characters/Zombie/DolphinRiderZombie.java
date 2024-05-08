package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class DolphinRiderZombie extends Zombie {
    private boolean hasDolphin;

    public DolphinRiderZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Dolphin Rider Zombie", 175, damage, 1, 100, position, true, speed);
    }

    public boolean getHasDolphin() {
        return hasDolphin;
    }

    public void setHasDolphin(boolean hasDolphin) {
        this.hasDolphin = hasDolphin;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}