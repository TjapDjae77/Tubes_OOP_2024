package source.Characters.Zombie;

public class DolphinRiderZombie extends Zombie {
    private boolean hasDolphin;

    public DolphinRiderZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Dolphin Rider Zombie", 175, 100, 1, position, true, speed);
    }

    public boolean getHasDolphin() {
        return hasDolphin;
    }

    public void setHasDolphin(boolean hasDolphin) {
        this.hasDolphin = hasDolphin;
    }
}