package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Peashooter extends Plants{
    public Peashooter(int row, int column) {
        super("Peashooter", 100, 25, 4, 100, -1, 10, false, row, column);
    }

    public Peashooter() {
        super("Peashooter", 100, 25, 4, 100, -1, 10, false, 0, 0);
    }

    public boolean canShoot() {
        return true;
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
