package source.Characters.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie(String name, int health, int attack_speed, int attack_damage, int[] position, boolean is_aquatic, int speed) {
        super("Normal Zombie", 125, 100, 1, position, false, 5);
    }
}