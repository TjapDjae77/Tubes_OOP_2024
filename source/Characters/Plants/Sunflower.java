package source.Characters.Plants;

import java.util.Timer;
import java.util.TimerTask;

public class Sunflower extends Plants {
    private int sun = 25;
    Timer timer = new Timer();

    public Sunflower(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean is_aquatic, int sun) {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false);
        this.sun = sun; 
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
}