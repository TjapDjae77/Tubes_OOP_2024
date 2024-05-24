package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Spikeweed extends Plants{
    public Spikeweed(int row, int column) {
        super("Spikeweed", 100, 20, 3, 100, 0, 10, false, row, column);
    }

    public Spikeweed() {
        super("Spikeweed", 100, 20, 3, 100, 0, 10, false, 0, 0);
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
        if (zombie.getCurrentColumn()==this.getCurrentColumn() && zombie.getCurrentRow()==this.getCurrentRow()){
            super.attack(zombie);
        }
    }
}
