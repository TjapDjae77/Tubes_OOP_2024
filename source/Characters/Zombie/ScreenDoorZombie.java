package source.Characters.Zombie;

public class ScreenDoorZombie extends Zombie {
    private boolean hasDoor;

    public ScreenDoorZombie(String name, int health, int damage, int attack_speed, int attack_damage, boolean is_aquatic, int speed) {
        super("Screen Door Zombie", 250, 100, 1, false, speed);
    }

    public boolean getHasDoor() {
        return hasDoor;
    }

    public void setHasDoor(boolean hasDoor) {
        this.hasDoor = hasDoor;
    }
}
