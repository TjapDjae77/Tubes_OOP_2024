package source.Characters;

public class Characters {
    public String name;
    public int health;
    public int attack_damage;
    public int attack_speed;
    public int[] position;
    public boolean is_aquatic;

    public Characters(String name, int health, int attack_damage, int attack_speed, int[] position, boolean is_aquatic) {
        this.name = name;
        this.health = health;
        this.attack_damage = attack_damage;
        this.attack_speed = attack_speed;
        this.position = position;
        this.is_aquatic = is_aquatic;
    }

    public String getName() {
        return this.name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getAttackDamage() {
        return this.attack_damage;
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
