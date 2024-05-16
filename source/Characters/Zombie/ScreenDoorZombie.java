package source.Characters.Zombie;

public class ScreenDoorZombie extends Zombie {
    private boolean door = true;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 250, 100, 1, false, 5,8,0);
        setCurrentColumn(randomColumn());
        // Adjusting door status based on initial health condition
        if (this.health <= 50) {
            this.door = false;
        }
    }
    public void removeDoor() {
        if (this.health <= 50) {
            this.door = false;
        }
    }

    public void showDescription() {
        System.out.println("Screen Door Zombie: A zombie that has a screen door to protect itself.");
        System.out.println("Health: " + this.health);
        System.out.println("Attack Damage: " + this.attack_damage);
        System.out.println("Attack Speed: " + this.attack_speed);
        System.out.println("Speed: " + this.speed);
    } // This closing brace correctly ends the showDescription method
    
} // This closing brace correctly ends the class definition
