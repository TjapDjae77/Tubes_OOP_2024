package source.Characters.Plants;
public class Lilypad extends Plants{
    private boolean occupied;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, 25, 0, 10, true);
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

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void unoccupy() {
        occupied = false;
    }

    public int getTotalHealth(Plants plant) {
        return this.health + plant.getHealth();
    }

    public void checkAndRemovePlant(Plants plant) {
        if (getTotalHealth(plant) <= 0) {
            System.out.println("The plant on top of the Lilypad has died. Removing Lilypad...");
            this.setHealth(0);
        }
    }
}
