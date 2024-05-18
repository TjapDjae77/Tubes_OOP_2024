package source.Characters.Zombie;

public class ConeheadZombie extends Zombie {
    private boolean Conehead;

    public ConeheadZombie() {
        super("Conehead Zombie", 250, 100, 1, false, 10, 0, 10);
        setCurrentColumn(randomColumn());
        this.Conehead = true;
    }

    public void removeCone() {
        if (getHealth() < 100 && Conehead) {
            Conehead = false; 
            System.out.println("The cone has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
        System.out.println("Conehead : " + Conehead);
    }
}