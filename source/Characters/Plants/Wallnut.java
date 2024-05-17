package source.Characters.Plants;
public class Wallnut extends Plants{
    public Wallnut(int row, int column) {
        super("Wall-nut", 1000, 0, 0, 50, 0, 20, false, row, column);
    }

    public Wallnut() {
        super("Wall-nut", 1000, 0, 0, 50, 0, 20, false, 0, 0);
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
