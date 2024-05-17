package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class TangleKelp extends Plants{

    public TangleKelp(int row, int column) {
        super("Tangle Kelp", 100, 5000, 1, 25, 0, 20, true, row, column);
    }

    public TangleKelp() {
        super("Tangle Kelp", 100, 5000, 1, 25, 0, 20, true, 0, 0);
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
        if (zombie != null) {
            zombie.setHealth(zombie.getHealth() - this.attack_damage);
            System.out.println(this.name + " attacks " + zombie.getName() + " for " + this.attack_damage + " damage.");
            System.out.println(zombie.getName() + " has " + zombie.getHealth() + " health remaining.");
            vanish();
        }
    }

    private void vanish() {//Sama kek squash
        this.health = 0;
        System.out.println(this.name + " has vanished after its attack.");
    }

}
