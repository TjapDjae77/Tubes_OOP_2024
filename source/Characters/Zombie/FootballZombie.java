package source.Characters.Zombie;

public class FootballZombie extends Zombie {
    public FootballZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Football Zombie", 200, 100, 1, position, false, speed);
    }
}