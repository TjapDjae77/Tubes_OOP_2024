package source.Characters.Plants;
public class Lilypad extends Plants{
    public Lilypad(String name, int health, int damage, int attack_speed, int[] position, int cost, int range, int cooldown, boolean is_aquatic) {
        super("Lilypad", 100, 0, 0, position, 25, 0, 10, true);
    }
}
