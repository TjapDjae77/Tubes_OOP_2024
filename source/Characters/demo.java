package source.Characters;
import source.Characters.Plants.*;
import source.Characters.Zombie.*;

public class demo {
//     public static void main(String[] args) {
//         TangleKelp kelp = new TangleKelp();
//         BucketheadZombie Zombie = new BucketheadZombie();

//         kelp.attack(Zombie);
//     }
// }
// javac source/Characters/Characters.java source/Characters/Plants/Plants.java source/Characters/Plants/Peashooter.java source/Characters/Zombie/Zombie.java source/Characters/Zombie/NormalZombie.java

public static void main(String[] args) {
    // Create a Lilypad and a Peashooter
    Lilypad lilypad = new Lilypad();
    Peashooter peashooter = new Peashooter();

    // Occupy Lilypad with Peashooter and combine health
    lilypad.occupy(peashooter);

    // Display Lilypad's health after occupancy
    System.out.println("Lilypad's combined health: " + lilypad.getHealth());

    // Simulate an attack by a Zombie that deals 200 damage
    int damage = 200;
    System.out.println("Lilypad and Peashooter are attacked by a Zombie, dealing " + damage + " damage.");
    lilypad.setHealth(lilypad.getHealth() - damage);

    // Check if the Lilypad and Peashooter should be removed
    lilypad.checkAndRemove();

    // Display status of Lilypad and Peashooter after the attack
    if (lilypad.getHealth() <= 0) {
        System.out.println("Lilypad has been removed.");
    } else {
        System.out.println("Lilypad is still in place.");
    }

    if (peashooter.getHealth() <= 0) {
        System.out.println("Peashooter has been killed.");
    } else {
        System.out.println("Peashooter is still alive.");
    }
}
}