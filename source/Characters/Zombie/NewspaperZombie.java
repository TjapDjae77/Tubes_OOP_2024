package source.Characters.Zombie;

public class NewspaperZombie extends Zombie {
    private boolean Newspaper;

    public NewspaperZombie() {
        super("Newspaper Zombie", 250, 100, 1, false, 5, 8, 0);
        setCurrentColumn(randomColumn());
        this.Newspaper = true;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        removeNewspaper(); // Check if the newspaper should be removed whenever health is updated
    }

    public void removeNewspaper() {
        if (this.health < 100 && this.Newspaper) {
            Newspaper = false; 
            setOriginalSpeed(2);
            System.out.println("The newspaper has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Newspaper : " + Newspaper);
    }
}