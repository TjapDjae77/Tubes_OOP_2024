package source.Sun;

// import javafx.beans.property.IntegerProperty;
// import javafx.beans.property.SimpleIntegerProperty;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Sun{
    private static int sun = 50;

    Timer timer = new Timer();

    public Sun(){
        spawnSun();
    }

    public void spawnSun(){
        timer.schedule(new TimerTask() {
            @Override
            public void run(){
                addSun(25);
                System.out.println("Sun saat ini : " + sun);
                spawnSun();
            }
        }, randomTime(5, 10)*1000);
        
    }

    public int randomTime(int min, int max){
        Random random = new Random();
        return (random.nextInt((max - min)+1)+ min);
    }

    public static synchronized int getSun(){
        return sun;
    }

    public static synchronized void addSun(int value){
        sun = sun + value;
    }

    public static void reduceSun(int costPlant){
        sun = sun-costPlant;
    }
}
