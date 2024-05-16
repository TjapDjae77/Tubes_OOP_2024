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
        Lilypad lilypad = new Lilypad();
        Peashooter peashooter = new Peashooter();

        // Occupy Lilypad
        lilypad.occupy();

        // Simulate the death of the plant on top of the Lilypad
        peashooter.setHealth(0);

        // Check if the total health is 0 and remove the Lilypad if true
        lilypad.checkAndRemovePlant(peashooter);

        // Display status of Lilypad
        if (lilypad.getHealth() <= 0) {
            System.out.println("Lilypad has been removed.");
        } else {
            System.out.println("Lilypad is still in place.");
        }
    }
}