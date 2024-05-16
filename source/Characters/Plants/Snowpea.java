package source.Characters.Plants;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

import source.Characters.Zombie.Zombie;

public class Snowpea extends Plants{
    private int slowDuration; // Duration in milliseconds for how long the zombie will be slowed
    private double slowEffect; // Slow effect as a factor (e.g., 0.5 for 50% speed)
    private ScheduledExecutorService scheduler;

    public Snowpea() {
        super("Snow Pea", 100, 25, 4, 175, -1, 10, false);
        this.slowDuration = 3000;
        this.slowEffect = 0.5;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
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

    public void attack(Zombie zombie) {
        if (zombie != null) {
            zombie.setHealth(this.attack_damage);
            System.out.println(this.name + " attacks " + zombie.getName() + " for " + this.attack_damage + " damage.");
            System.out.println(zombie.getName() + " has " + zombie.getHealth() + " health remaining.");
            applySlowEffect(zombie);
        }
    }

    private void applySlowEffect(Zombie zombie) {
        int originalSpeed = zombie.getOriginalSpeed();
        int slowedSpeed = (int) (originalSpeed * slowEffect);

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

    public void continuousAttack(Zombie zombie) {
        while (zombie.getHealth() > 0) {
            attack(zombie);
            try {
                Thread.sleep(this.attack_speed * 1000); // Sleep to simulate attack speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(zombie.getName() + " has been defeated.");
    }
}