package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class FootballZombie extends Zombie {
    public FootballZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Football Zombie", 200, damage, 1, 100, position, false, speed);
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}