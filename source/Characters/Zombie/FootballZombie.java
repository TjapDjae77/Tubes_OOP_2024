package source.Characters.Zombie;

public class FootballZombie extends Zombie {
    private boolean Football;

    public FootballZombie() {
        super("Football Zombie", 200, 100, 1, false, 5, 0, 10);

        this.Football = true;
    }

    public void removeHelmet() {
        if (getHealth() < 100 && Football) {
            Football = false; 
            System.out.println("The helmet has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
        System.out.println("Helmet " + Football);
    }
}