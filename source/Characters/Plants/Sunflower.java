package source.Characters.Plants;

import java.util.Timer;
import java.util.TimerTask;

public class Sunflower extends Plants {
    private int sun = 25;
    Timer timer = new Timer();

    public Sunflower() {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false);
    }

    public void addSunSunflower(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                sun += 25;
                System.out.println("Sun saat ini : " + sun);
                addSunSunflower();
            }
        }, 3000);
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
}