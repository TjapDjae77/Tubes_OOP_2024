package source.Characters.Zombie;

public class NewspaperZombie extends Zombie {
    private boolean Newspaper;

    public NewspaperZombie() {
        super("Newspaper Zombie", 250, 100, 1, false, 10, 0, 10);
        setCurrentColumn(randomColumn());
        this.Newspaper = true;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        removeNewspaper(); // Check if the newspaper should be removed whenever health is updated
    }

    public void removeNewspaper() {
        if (getHealth() < 100 && this.Newspaper) {
            Newspaper = false; 
            setOriginalSpeed(5);
            System.out.println("The newspaper has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
        System.out.println("Newspaper : " + Newspaper);
    }
}