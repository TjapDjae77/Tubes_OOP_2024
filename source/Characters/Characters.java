package source.Characters;

public class Characters {
    public String name;
    public int health;
    public int damage;
    public int attack_speed;
    public int[] position;

    public Characters(String name, int health, int damage, int attack_speed, int[] position) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.attack_speed = attack_speed;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }   

    public int getAttackSpeed() {
        return this.attack_speed;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}
