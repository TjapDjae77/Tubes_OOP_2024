package source.Characters.Plants;
public class Squash extends Plants{
    public Squash(String name, int health, int attack_damage, int attack_speed, int[] position, int cost, int range, int cooldown, boolean is_aquatic) {
        super("Squash", 100, 5000, 1, position, 50, 1, 20, false);
    }
}