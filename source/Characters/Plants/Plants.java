package source.Characters.Plants;
import source.Characters.Characters;


public abstract class Plants extends Characters{
    public int cost;
    public int range;
    public int cooldown;

    public Plants(String name, int health, int damage, int attack_speed, int[] position, int cost, int range, int cooldown) {
        super(name, health, damage, attack_speed, position);
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



