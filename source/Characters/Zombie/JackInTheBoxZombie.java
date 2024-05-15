package source.Characters.Zombie;

public class JackInTheBoxZombie extends Zombie {
    private boolean hasJack;

    public JackInTheBoxZombie() {
        super("Jack In The Box Zombie", 175, 100, 1, false, 5);
    }

    public boolean getHasJack() {
        return hasJack;
    }

    public void setHasJack(boolean hasJack) {
        this.hasJack = hasJack;
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