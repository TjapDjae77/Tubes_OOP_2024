package source.Sun;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Sun{
    private static int sun = 25;
    Timer timer = new Timer();
    
    public void spawnSun(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                sun += 25;
                System.out.println("Sun saat ini : " + sun);
                spawnSun();
            }
        }, randomTime(5, 10)*1000);
        
    }

    public int randomTime(int min, int max){
        Random random = new Random();
        int randomTime = (random.nextInt((max - min)+1)+ min);
        return randomTime;
    }

    public static int getSun(){
        return Sun.sun;
    }

    public static void addSun(int sun){
        Sun.sun += sun;
    }

    public void reduceSun(int costPlant){
        Sun.sun -= costPlant;
    }
}
