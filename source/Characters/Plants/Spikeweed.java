package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Spikeweed extends Plants{
    public Spikeweed(int row, int column) {
        super("Spikeweed", 100, 20, 3, 100, 0, 10, false, row, column);
    }

    public Spikeweed() {
        super("Spikeweed", 100, 20, 3, 100, 0, 10, false, 0, 0);
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
        }
    }

    public void continuousAttack(Zombie zombie) {//Dia cuman nyerang yang ada di tiles dia sesuaiin yak
        while (zombie.getHealth() > 0) {
            attack(zombie);
            try {
                Thread.sleep(this.attack_speed * 1000); // Sleep to simulate attack speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(zombie.getName() + " has been defeated.");
    }
}
