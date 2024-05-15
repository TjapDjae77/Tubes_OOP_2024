package source.Characters.Zombie;

public class NewspaperZombie extends Zombie {
    private boolean hasNewspaper;

    public NewspaperZombie(String name, int health, int damage, int attack_speed, int attack_damage, boolean is_aquatic, int speed) {
        super("Newspaper Zombie", 250, 100, 1, false, speed);
    }

    public boolean getHasNewspaper() {
        return hasNewspaper;
    }

    public void setHasNewspaper(boolean hasNewspaper) {
        this.hasNewspaper = hasNewspaper;
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
    }
}