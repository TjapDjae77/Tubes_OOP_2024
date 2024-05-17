package source.Characters.Plants;

public class Tallnut extends Plants{
    public Tallnut(int row, int column) {
        super("Tall-nut", 2000, 0, 0, 125, 0, 20, false, row, column);
    }

    public Tallnut() {
        super("Tall-nut", 2000, 0, 0, 125, 0, 20, false, 0, 0);
    }

    public void showDescription() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Attack Damage: " + this.attack_damage);
        System.out.println("Attack Speed: " + this.attack_speed);
        System.out.println("Cost: " + this.cost);
        System.out.println("Range: " + this.range);
        System.out.println("Cooldown: " + this.cooldown);
        System.out.println("Is Aquatic: " + this.is_aquatic); 
    }
}
