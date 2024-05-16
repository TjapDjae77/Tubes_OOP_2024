package source.Characters.Zombie;
import source.Characters.Characters;
import source.Characters.Plants.Plants;
import source.Characters.Plants.Spikeweed;

public abstract class Zombie extends Characters {
    protected int speed;
    protected int originalSpeed;

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, int speed) {
        super(name, health, attack_damage, attack_speed, is_aquatic);
        this.speed = speed;
        this.originalSpeed = speed;
    }

    public boolean getIsAquatic() {
        return is_aquatic;
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

    public int getSpeed() {
        return speed;
    }

    public int getOriginalSpeed() {
        return originalSpeed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void showDescription();

    public void attackPlant(Plants plant) {
        if (plant instanceof Spikeweed) {
            System.out.println(this.name + " cannot eat Spikeweed!");
        } else {
            plant.setHealth(this.attack_damage);
            System.out.println(this.name + " attacks " + plant.getName() + " for " + this.attack_damage + " damage.");
            System.out.println(plant.getName() + " has " + plant.getHealth() + " health remaining.");
        }
    }
}