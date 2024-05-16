package source.Characters.Plants;

public class Lilypad extends Plants{
    private Plants plantOnTop;

    public Lilypad() {
        super("Lilypad", 100, 0, 0, 25, 0, 10, true);
        this.plantOnTop = null;
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

    public boolean isCompatible(Plants p) { 
        // Untuk dipanggil sm class lain, true jika lilypad bisa dipijak oleh p
        if (!p.is_aquatic && p.name != "Spikeweed") {
            return true;
        } else {
            return false;
        }
    }

    public boolean isOccupied() { // true jika ada tumbuhan yang memijak
        return plantOnTop != null;
    }

    public void occupy(Plants plant) {
        if (!this.isOccupied()) { // kalau masih kosong, baru inisialisasi plant on top
            this.plantOnTop = plant;
            this.health += plant.getHealth();
            System.out.println("Lilypad is occupied by " + plant.name + ". Combined health: " + this.health);
        } else { // kalau udah ada plant on top, gabisa
            System.out.println("Lilypad is already occupied.");
        }
    }

    public void checkAndRemove() {
        if (this.health <= 0) {
            System.out.println("The plant on top of the Lilypad and the Lilypad itself have died.");
            this.plantOnTop = null; // Remove reference to the plant on top
        }
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (this.health <= 0 && this.plantOnTop != null) {
            this.plantOnTop.setHealth(0); // Ensure the plant on top is also considered dead
        }
    }
}
