package source.Sun;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Sun{

    private static final IntegerProperty sun = new SimpleIntegerProperty(50);

    Timer timer = new Timer();

    private boolean isDay = true;

    public Sun(){
        spawnSun();
        TimerTask stopSpawningSun = new TimerTask() {
          @Override
          public void run() {
              setToNight();
              System.out.println("Malam sudah tiba, sun akan berhenti!");
          }
        };
        timer.schedule(stopSpawningSun,100000);
    }

    public void spawnSun(){
        if (isDay){
            timer.schedule(new TimerTask() {
                @Override
                public void run(){
                    Platform.runLater(() -> addSun(25));

                    spawnSun();
                }
            }, randomTime(5, 10)*1000);
        }
    }

    public int randomTime(int min, int max){
        Random random = new Random();
        return (random.nextInt((max - min)+1)+ min);
    }

    public void setToNight(){
        isDay = false;
    }

    public static synchronized int getSun(){
        return sun.get();
    }

    public static synchronized void addSun(int value){
        Platform.runLater(() -> sun.set(sun.get() + value));
    }

    public static void reduceSun(int costPlant){
        Platform.runLater(() -> sun.set(sun.get() - costPlant));
    }

    public static IntegerProperty sunProperty() {
        return sun;
    }
}
