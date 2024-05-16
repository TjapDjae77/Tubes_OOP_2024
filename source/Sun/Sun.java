package source.Sun;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Sun{
    private int sun = 25;
    Timer timer = new Timer();
    
    public void addSun(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                sun += 25;
                System.out.println("Sun saat ini : " + sun);
                addSun();
            }
        }, randomTime(5, 10)*1000);
        
    }

    public int randomTime(int min, int max){
        Random random = new Random();
        int randomTime = (random.nextInt((max - min)+1)+ min);
        return randomTime;
    }

    public int getSun(){
        return sun;
    }

    public void reduceSun(int costPlant){
        this.sun -= costPlant;
    }
}
