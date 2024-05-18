package source.Characters.Zombie;

public class ConeheadZombie extends Zombie {
    private boolean Conehead;

    public ConeheadZombie() {
        super("Conehead Zombie", 250, 100, 1, false, 10, 0, 8);
        setCurrentColumn(randomColumn());
        this.Conehead = true;
    }

    public void removeCone() {
        if (this.health < 100 && Conehead) {
            Conehead = false; 
            System.out.println("The cone has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Conehead : " + Conehead);
    }
}