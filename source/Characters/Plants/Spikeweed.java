package source.Characters.Plants;
public class Spikeweed extends Plants{
    public Spikeweed(String name, int health, int attack_damage, int attack_speed, int[] position, int cost, int range, int cooldown, boolean is_aquatic) {
        super("Spikeweed", 100, 20, 5, position, 100, 0, 10, false);
    }
}
