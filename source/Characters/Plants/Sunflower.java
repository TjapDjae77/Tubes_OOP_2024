package source.Characters.Plants;

import source.Sun.Sun;

import java.util.Timer;
import java.util.TimerTask;

public class Sunflower extends Plants {
    Timer timer = new Timer();

    public Sunflower(int row, int column) {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, row, column);
    }

    public Sunflower() {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, 0, 0);
    }

    public void addSunSunflower(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                Sun.addSun(25);
                System.out.println("Sun saat ini : " + Sun.getSun());
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