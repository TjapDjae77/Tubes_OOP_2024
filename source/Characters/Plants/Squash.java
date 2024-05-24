package source.Characters.Plants;

import source.Characters.Zombie.Zombie;

public class Squash extends Plants{
    public Squash(int row, int column) {
        super("Squash", 100, 5000, 1, 50, 1, 20, false, row, column);
    }

    public Squash() {
        super("Squash", 100, 5000, 1, 50, 1, 20, false, 0, 0);
    }

    @Override
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

    private void vanish() {
        this.setHealth(0);
        System.out.println(this.getName() + " has vanished after its attack.");
    }
    @Override
    public void attack(Zombie zombie) {
        zombie.setHealth(0);
        this.vanish();
        }
    }