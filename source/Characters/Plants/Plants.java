package source.Characters.Plants;
import source.Characters.Characters;


public abstract class Plants extends Characters{
    protected int cost;
    protected int range;
    protected int cooldown;

    public Plants(String name, int health, int attack_damage, int attack_speed, int[] position, int cost, int range, int cooldown, boolean is_aquatic) {
        super(name, health, attack_damage, attack_speed, position, is_aquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRange() {
        return this.range;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public int getHealth() {
        return this.health;
    }
}


