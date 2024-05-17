package source.Characters.Zombie;

public class ScreenDoorZombie extends Zombie {
    private boolean door;

    public ScreenDoorZombie() {
        super("Screen Door Zombie", 250, 100, 1, false, 5,8,0);
        setCurrentColumn(randomColumn());
        this.door = true;
    }

    public void removeDoor() {
        if (this.health <= 50 && door) {
            door = false; 
            System.out.println("The door has been destroyed!");
        }
    }

    public void showDescription() {
        System.out.println("Screen Door Zombie: A zombie that has a screen door to protect itself.");
        System.out.println("Health: " + this.health);
        System.out.println("Attack Damage: " + this.attack_damage);
        System.out.println("Attack Speed: " + this.attack_speed);
        System.out.println("Speed: " + this.speed);
    }
    
} 
