package source.Characters.Plants;

import source.Map.GameMap;

public class Lilypad extends Plants{
    private Plants plantOnTop;
    public Lilypad(int row, int column) {
        super("Lilypad", 100, 0, 0, 25, 0, 10, true, row, column);
        this.plantOnTop = null;
    }

    public Lilypad() {
        super("Lilypad", 100, 0, 0, 25, 0, 10, true, 0, 0);
        this.plantOnTop = null;
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

    public boolean isCompatible(Plants plant) { 
        // Untuk dipanggil sm class lain, true jika lilypad bisa dipijak oleh p
        if (!plant.getIsAquatic() && !plant.getName().equals("Spikeweed")) {
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
            this.setHealth(this.getHealth()+plant.getHealth());
            System.out.println("Lilypad is occupied by " + plant.getName() + ". Combined health: " + this.getHealth());
        } else { // kalau udah ada plant on top, gabisa
            System.out.println("Lilypad is already occupied.");
        }
    }

    public void checkAndRemove(GameMap gameMap) {
        if (this.getHealth() <= 0) {
            System.out.println("The plant on top of the Lilypad and the Lilypad itself have died.");
            this.plantOnTop = null; // Remove reference to the plant on top
            gameMap.getTile(this.row, this.column).setPlanted(null);
        }
    }

    @Override
    public void setHealth(int health, GameMap gameMap) {
        super.setHealth(health, gameMap);
        if (this.getHealth() <= 0 && this.plantOnTop != null) {
            this.plantOnTop.setHealth(0, gameMap); // Ensure the plant on top is also considered dead
        }
    }
}
