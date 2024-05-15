package source.Characters.Zombie;

public class NewspaperZombie extends Zombie {
    private boolean hasNewspaper;

    public NewspaperZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean is_aquatic, int speed) {
        super("Newspaper Zombie", 250, 100, 1, position, false, speed);
    }

    public boolean getHasNewspaper() {
        return hasNewspaper;
    }

    public void setHasNewspaper(boolean hasNewspaper) {
        this.hasNewspaper = hasNewspaper;
    }
}