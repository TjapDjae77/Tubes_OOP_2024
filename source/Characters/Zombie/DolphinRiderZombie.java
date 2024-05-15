package source.Characters.Zombie;

public class DolphinRiderZombie extends Zombie {
    private boolean hasDolphin;

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true, 5);
    }

    public boolean getHasDolphin() {
        return hasDolphin;
    }

    public void setHasDolphin(boolean hasDolphin) {
        this.hasDolphin = hasDolphin;
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