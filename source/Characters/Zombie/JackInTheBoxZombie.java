package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class JackInTheBoxZombie extends Zombie {
    private boolean hasJack;

    public JackInTheBoxZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Jack In The Box Zombie", 175, damage, 1, 100, position, false, speed);
    }

    public boolean getHasJack() {
        return hasJack;
    }

    public void setHasJack(boolean hasJack) {
        this.hasJack = hasJack;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}