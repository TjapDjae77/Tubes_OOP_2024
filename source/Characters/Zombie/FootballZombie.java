package source.Characters.Zombie;

public class FootballZombie extends Zombie {
    private boolean Football;

    public FootballZombie() {
        super("Football Zombie", 200, 100, 1, false, 5, 0, 8);
        setCurrentColumn(randomColumn());
        this.Football = true;
    }

    public void removeHelmet() {
        if (this.health < 100 && Football) {
            Football = false; 
            System.out.println("The helmet has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Helmet " + Football);
    }
}