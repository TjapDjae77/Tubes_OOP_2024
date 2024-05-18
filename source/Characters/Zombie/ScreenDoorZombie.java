package source.Characters.Zombie;

public class ScreenDoorZombie extends Zombie {
    private boolean door;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 250, 100, 1, false, 10, 0, 8);
        setCurrentColumn(randomColumn());
        this.door = true;
    }

    public boolean hasDoor() {
        return door;
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        removeDoor(); // Check if the door should be removed whenever health is updated
    }

    public void removeDoor() {
        if (this.health <= 100 && this.door) {
            this.door = false; 
            System.out.println("The door has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Screen Door Zombie: A zombie that has a screen door to protect itself.");
        System.out.println("Health: " + this.health);
        System.out.println("Attack Damage: " + this.attack_damage);
        System.out.println("Attack Speed: " + this.attack_speed);
        System.out.println("Speed: " + this.speed);
        System.out.println("Door : " + door);
    }
    
} 
