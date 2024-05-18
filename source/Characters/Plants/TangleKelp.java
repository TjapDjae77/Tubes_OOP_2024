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
        super.attack(zombie);
        if (this.health > 0) {
            this.vanish();
        }
    }

    private void vanish() {
        this.health = 0;
        System.out.println(this.name + " has vanished after its attack.");
    }

}
