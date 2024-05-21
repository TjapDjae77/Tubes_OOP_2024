//package source.GUI;
//
//import javafx.application.Platform;
//
//import source.Characters.Zombie.Zombie;
//import source.Characters.Zombie.ZombieFactory;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class WalkingZombieSpawner {
//
//    private static final int MAX_ZOMBIE = 10;
//    private static final double SPAWN_PROBABILITY = 0.3;
//    private static final int SPAWN_INTERVAL = 3000;
//    private ArrayList<WalkingZombie> zombies;
//    private Thread spawningThread;
//    private boolean isGameOver = false;
//
//
//    public WalkingZombieSpawner(){
//        this.zombies = new ArrayList<>();
//    }
//
//    public void startSpawning() {
//        spawningThread = new Thread(() -> {
//            while(!isGameOver){
//                try {
//                    Thread.sleep(SPAWN_INTERVAL);
//                    Platform.runLater(this::startSpawning);
//                }
//                catch(InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        });
//        spawningThread.start();
//    }
//
//
//
//
//    public void stopSpawning() {
//        isGameOver = true;
//    }
//}
