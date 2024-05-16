package source.Characters.Plants;
import source.Characters.Characters;


public abstract class Plants extends Characters{
    protected int cost;
    protected int range;
    protected int cooldown;
    protected boolean on_cooldown;

    public Plants(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean is_aquatic) {
        super(name, health, attack_damage, attack_speed, is_aquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.on_cooldown = false;
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

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0; 
        }
    }

    public boolean getCDStatus(){
        return on_cooldown;
    }
    
    public void setOnCooldown(boolean cdStatus){
        this.on_cooldown = cdStatus;
    }

    public abstract void showDescription();
}


