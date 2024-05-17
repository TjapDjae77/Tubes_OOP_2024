package source.Characters.Zombie;

public class JackInTheBoxZombie extends Zombie {
    private boolean JackInTheBox;

    public JackInTheBoxZombie() {
        super("Jack In The Box Zombie", 175, 100, 1, false, 5, 8, 0);
        setCurrentColumn(randomColumn());
        this.JackInTheBox = true;
    }

    public void removeJack() {
        if (this.health < 100 && JackInTheBox) {
            JackInTheBox = false; 
            System.out.println("The Jack In The Box has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Jack In The Box : " + JackInTheBox);
    }
}