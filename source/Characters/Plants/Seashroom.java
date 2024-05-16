package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Seashroom extends Plants{
    public Seashroom() {
        super("Sea-shroom", 100, 15, 4, 0, 3, 25, true);
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

    public void continuousAttack(Zombie zombie) {
        while (zombie.getHealth() > 0) {
            attack(zombie);
            try {
                Thread.sleep(this.attack_speed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(zombie.getName() + " has been defeated.");
    }
}    
