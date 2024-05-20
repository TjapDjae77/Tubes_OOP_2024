package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class TangleKelp extends Plants{

    public TangleKelp(int row, int column) {
        super("Tangle Kelp", 100, 5000, 1, 25, 0, 20, true, row, column);
    }

    public TangleKelp() {
        super("Tangle Kelp", 100, 5000, 1, 25, 0, 20, true, 0, 0);
    }

    public boolean canShoot() {
        return false;
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
        if (this.getHealth() > 0) {
            this.vanish();
        }
    }

    private void vanish() {
        this.setHealth(0);
        System.out.println(this.getName() + " has vanished after its attack.");
    }

}
