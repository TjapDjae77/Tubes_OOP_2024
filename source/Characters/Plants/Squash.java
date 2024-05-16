package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Squash extends Plants{
    private boolean isVanished;

    public Squash() {
        super("Squash", 100, 5000, 1, 50, 1, 20, false);
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
        if (zombie != null && !isVanished) {
            zombie.setHealth(zombie.getHealth() - this.attack_damage);
            System.out.println(this.name + " attacks " + zombie.getName() + " for " + this.attack_damage + " damage.");
            System.out.println(zombie.getName() + " has " + zombie.getHealth() + " health remaining.");
            vanish();
        }
    }

    private void vanish() { //Plant klo ilang lu harus remove dari tilesnya
        this.isVanished = true;
        this.health = 0;
        // Map<Tiles>(row, column).setStatus = true;
        System.out.println(this.name + " has vanished after its attack.");
    }

    public boolean isVanished() {
        return this.isVanished;
    }
}