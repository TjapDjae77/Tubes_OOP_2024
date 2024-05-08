package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class NormalZombie extends Zombie {
    public NormalZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Normal Zombie", 250, damage, 1, 100, position, false, 5);
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}