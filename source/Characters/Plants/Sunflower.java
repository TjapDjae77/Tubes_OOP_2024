package source.Characters.Plants;

import source.Sun.Sun;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Sunflower extends Plants {
    Timer timer = new Timer();
    private Thread generatingSunThread;
    private boolean startproduce = false;
    private boolean stopRequested = false;

    public Sunflower(int row, int column) {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, row, column);
        startproduce = false;
    }

    public Sunflower() {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, 0, 0);
        startproduce = false;
    }
    public void startGeneratingSun() {
        generatingSunThread = new Thread(() -> {
            try {
                while (!stopRequested) {
                    Thread.sleep(3000);
                    synchronized (Sun.class) {
                        Sun.addSun(25); // Generate 25 sun every 3 seconds
                        System.out.println("Sun saat ini ding : " + Sun.getSun() + " di row " + this.getCurrentRow() + " dan column " + getCurrentColumn());
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        generatingSunThread.start();
    }

    public boolean isStartproduce() {
        return startproduce;
    }

    public void setStartproduce(boolean startproduce) {
        this.startproduce = startproduce;
        if(startproduce){
            startGeneratingSun();
        }
    }

    public boolean canShoot() {
        return false;
    }

    public void stopGeneratingSun() {
        stopRequested = true;
        if (generatingSunThread != null) {
            generatingSunThread.interrupt();
        }
    }

    public void addSunSunflower(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
//                Sun.addSun(25);
//                System.out.println("Sun saat ini : " + Sun.getSun());
                addSunSunflower();
            }
        }, 3000);
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
}