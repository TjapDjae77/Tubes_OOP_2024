package source.Map;
import source.Characters.Zombie.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.random.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameMap extends JPanel {
    private final int width = 9;
    private final int height = 6;
    private Tiles[][] grid;
    private static ZombieList<Zombie> inGameZombie;
    private static ZombieList<Zombie> zombieList = new ZombieList<>();
    private static boolean gameOver =false;
    public GameMap() {
        BucketheadZombie bucketheadZombie = new BucketheadZombie();
        ConeheadZombie coneheadZombie = new ConeheadZombie();
        DolphinRiderZombie dolphinRiderZombie = new DolphinRiderZombie();
        DuckyTubeZombie duckyTubeZombie = new DuckyTubeZombie();
        FootballZombie footballZombie = new FootballZombie();
        JackInTheBoxZombie jackInTheBoxZombie = new JackInTheBoxZombie();
        NewspaperZombie newspaperZombie = new NewspaperZombie();
        NormalZombie normalZombie = new NormalZombie();
        PoleVaultingZombie poleVaultingZombie = new PoleVaultingZombie();
        ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie();
        zombieList.addZombie(bucketheadZombie);
        zombieList.addZombie(coneheadZombie);
        zombieList.addZombie(dolphinRiderZombie);
        zombieList.addZombie(duckyTubeZombie);
        zombieList.addZombie(footballZombie);
        zombieList.addZombie(jackInTheBoxZombie);
        zombieList.addZombie(newspaperZombie);
        zombieList.addZombie(normalZombie);
        zombieList.addZombie(poleVaultingZombie);
        zombieList.addZombie(screenDoorZombie);
        inGameZombie = new ZombieList<>();
        grid = new Tiles[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(j == 0){
                    grid[i][j] = new ProtectedTiles();
                }
                else if(j == width-1){
                    grid[i][j] = new SpawnTiles();
                }
                else if((i == 2) || (i == 3)){
                    grid[i][j] = new PoolTiles();
                }
                else{
                    grid[i][j] = new GrassTiles();
                }
            }
        }
    }
    public Zombie zombiePicker(){
        Random rand = new Random();
        int randomNum = rand.nextInt(10);
        return zombieList.getZombies().get(randomNum);
    }

    public void spawnZombie(int row){
        inGameZombie.addZombie(zombiePicker());
        //set the last added zombie row into int row
        inGameZombie.getZombies().get(inGameZombie.getZombies().size()-1).setCurrentRow(row);
    }
    
    public void deadZombie(Zombie zombie){
        inGameZombie.removeZombie(zombie);
    }
    //Lane spawner
    public void laneSpawner(){
        for(int i = 0; i < height; i++){
            //there's a 0,3 chance that a zombie would spawn in that row
            if(Math.random() < 0.3){
                spawnZombie(i);
                System.out.println((inGameZombie.getZombies().get(inGameZombie.getZombies().size()-1)).getName() + " has spawned in lane " + i);
            }
        }
    }

    public ZombieList<Zombie> getInGameZombies(){
        return inGameZombie;
    }

    public Tiles getTile(int row, int column){
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length) {
            return grid[row][column];
        } else {
            // Jika indeks yang diminta di luar rentang grid, throw Exception
            throw new IllegalArgumentException("Invalid row or column index");
        }
    }

    public int getHeight(){ return height; }
    
    public int getWidth(){ return width; }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 100; // Ukuran petak yang diinginkan (100x100)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Menggambar petak pada koordinat (j*tileSize, i*tileSize)
                grid[i][j].draw(g, j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }
    public boolean getGameOver(){
        return gameOver;
    }
    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
}
// public class Main {
//     public static void main(String[] args) {
//         Timer timer = new Timer();
//         timer.schedule(new SpawnZombieTask(), 0, 1000);
//     }
// }
class SpawnZombieTask extends TimerTask {
    private GameMap gameMap;
    @Override
    public void run() {
        for(int i = 0; i < 6; i++){
            //there's a 0,3 chance that a zombie would spawn in that row
            if(Math.random() < 0.3){
                gameMap.spawnZombie(i);
            }
        }
    }
}