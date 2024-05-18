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
        System.out.println("Name: " + this.getName());
        System.out.println("Health: " + this.getHealth());
        System.out.println("Attack Damage: " + this.getAttackDamage());
        System.out.println("Attack Speed: " + this.getAttackSpeed());
        System.out.println("Cost: " + this.cost);
        System.out.println("Range: " + this.range);
        System.out.println("Cooldown: " + this.cooldown);
        System.out.println("Is Aquatic: " + this.getIsAquatic()); 
    }

    public void attack(Zombie zombie) {
        super.attack(zombie);
    }
}    
