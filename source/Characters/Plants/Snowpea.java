package source.Characters.Plants;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

import source.Characters.Zombie.ScreenDoorZombie;
import source.Characters.Zombie.Zombie;

public class Snowpea extends Plants{
    private int slowDuration; // Duration in milliseconds for how long the zombie will be slowed
    private double slowEffect; // Slow effect as a factor (e.g., 1.5 for 150% speed)
    private ScheduledExecutorService scheduler;
    
    public Snowpea(int row, int column) {
        super("Snow Pea", 100, 25, 4, 175, -1, 10, false, row, column);
        this.slowDuration = 3000;
        this.slowEffect = 1.5;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public Snowpea() {
        super("Snow Pea", 100, 25, 4, 175, -1, 10, false, 0, 0);
        this.slowDuration = 3000;
        this.slowEffect = 1.5;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
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
        super.attack(zombie);
        applySlowEffect(zombie);
    }

    private void applySlowEffect(Zombie zombie) {
        // Check if the zombie is a ScreenDoorZombie and still has its door
        if (zombie instanceof ScreenDoorZombie) {
            ScreenDoorZombie screenDoorZombie = (ScreenDoorZombie) zombie;
            if (screenDoorZombie.hasDoor()) {   
                System.out.println(zombie.getName() + " is protected by a screen door and cannot be slowed.");
                return;
            }
        }

        double originalSpeed = zombie.getOriginalSpeed();
        double slowedSpeed = (double) (originalSpeed * slowEffect);

        if (zombie.getSpeed() == originalSpeed) {
            zombie.setSpeed(slowedSpeed);
            System.out.println(zombie.getName() + " is slowed to " + slowedSpeed + " speed.");
        } else {
            System.out.println(zombie.getName() + " is already slowed. Resetting slow duration.");
        }

        // Reset any previous task and schedule a new one to reset the speed
        scheduler.shutdownNow();
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            if (zombie.getSpeed() == slowedSpeed) {
                zombie.setSpeed(originalSpeed);
                System.out.println(zombie.getName() + " has returned to normal speed: " + originalSpeed);
            }
        }, slowDuration, TimeUnit.MILLISECONDS);
    }
}