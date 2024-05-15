package source.Characters.Zombie;

public class NormalZombie extends Zombie {
    public NormalZombie(String name, int health, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Normal Zombie", 125, 1, 100, position, false, 5);
    }
}