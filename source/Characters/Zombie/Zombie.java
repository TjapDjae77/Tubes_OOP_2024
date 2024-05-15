package source.Characters.Zombie;
import source.Characters.Characters;
import source.Characters.Plants.Plants;

public abstract class Zombie extends Characters {
    protected boolean is_aquatic;
    protected int speed;

    public Zombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super(name, health, attack_damage, attack_speed, position);
        this.is_aquatic = isAquatic;
        this.speed = speed;
    }

    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; 
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void attack(Plants tanaman) {
        System.out.println(name + " attacks with " + damage + " damage.");
    }
}