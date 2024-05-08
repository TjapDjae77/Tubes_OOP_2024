package source.Characters.Zombie;
import source.Characters.Plants.Plants;

public class NewspaperZombie extends Zombie {
    private boolean hasNewspaper;

    public NewspaperZombie(String name, int health, int damage, int attack_speed, int attack_damage, int[] position, boolean isAquatic, int speed) {
        super("Newspaper Zombie", 250, damage, 1, 100, position, false, speed);
    }

    public boolean getHasNewspaper() {
        return hasNewspaper;
    }

    public void setHasNewspaper(boolean hasNewspaper) {
        this.hasNewspaper = hasNewspaper;
    }

    public void attack(Plants tanaman) {
        super.attack(tanaman);
    }
}