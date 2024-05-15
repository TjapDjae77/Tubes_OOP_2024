package source.Characters.Zombie;

public class ConeheadZombie extends Zombie {
    private boolean hasCone;

    public ConeheadZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean is_aquatic, int speed) {
        super("Conehead Zombie", 250, 100, 1, position, false, speed);
    }

    public boolean getHasCone() {
        return hasCone;
    }

    public void setHasCone(boolean hasCone) {
        this.hasCone = hasCone;
    }
}