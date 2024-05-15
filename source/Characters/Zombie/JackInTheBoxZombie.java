package source.Characters.Zombie;

public class JackInTheBoxZombie extends Zombie {
    private boolean hasJack;

    public JackInTheBoxZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Jack In The Box Zombie", 175, 100, 1, position, false, speed);
    }

    public boolean getHasJack() {
        return hasJack;
    }

    public void setHasJack(boolean hasJack) {
        this.hasJack = hasJack;
    }
}