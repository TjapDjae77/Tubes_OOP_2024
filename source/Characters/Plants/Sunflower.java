package source.Characters.Plants;

import source.Sun.Sun;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Sunflower extends Plants {
    Timer timer = new Timer();
    private Thread generatingSunThread;
    private boolean startProduce = false;
    private volatile boolean stopRequested = false;

    public Sunflower(int row, int column) {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, row, column);
        startProduce = false;
    }

    public Sunflower() {
        super("Sunflower", 100, 0, 0, 50, 0, 10, false, 0, 0);
        startProduce = false;
    }
    public void startGeneratingSun() {
        stopRequested = false;
        generatingSunThread = new Thread(() -> {
            try {
                while (!stopRequested) {
                    Thread.sleep(3000);
                    synchronized (Sun.class) {
                        Sun.addSun(25); // Generate 25 sun every 3 seconds

                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted");
            }
        });
        generatingSunThread.start();
    }

    public boolean isStartProduce() {
        return startProduce;
    }

    public boolean isStopRequested() {
        return stopRequested;
    }

    public void setStartProduce(boolean startproduce) {
        this.startProduce = startproduce;
        if(isStartProduce()){
            startGeneratingSun();
        }
    }

    public boolean canShoot() {
        return false;
    }

    public void stopGeneratingSun() {
        stopRequested = true;
        generatingSunThread.interrupt();
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