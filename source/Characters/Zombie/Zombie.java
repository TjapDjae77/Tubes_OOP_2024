package source.Characters.Zombie;
import source.Characters.Characters;

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

    public void setHealth(int damage) {
        this.health -= damage;
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
}