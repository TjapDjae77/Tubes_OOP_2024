package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Seashroom extends Plants{
    public Seashroom(int row, int column) {
        super("Sea-shroom", 100, 15, 4, 0, 3, 25, true, row, column);
    }

    public Seashroom() {
        super("Sea-shroom", 100, 15, 4, 0, 3, 25, true, 0, 0);
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

    public void attack(Zombie zombie) {
        super.attack(zombie);
    }
}    
