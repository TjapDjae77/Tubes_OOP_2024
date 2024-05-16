package source.Deck;

import java.util.Timer;
import java.util.TimerTask;
import source.Characters.Plants.*;
import source.Inventory.Inventory;
import source.Sun.Sun;
import source.Map.*;

public class Deck{
    private Plants[] currentDeck;
    Inventory inventory = new Inventory();
    Sun sun = new Sun();
    GameMap map = new GameMap();
    Timer timer = new Timer();

    public Deck(){
        this.currentDeck = inventory.getGameDeck();
    }

    public void plantPlants(Plants plant, int row, int column) throws TileOccupiedException, InsufficientSunException, CooldownException, NotAquaticException{
        if(!(map.getTile(row, column) instanceof PoolTiles) && plant.is_aquatic){ // Exception untuk tanaman aquatic yang ingin ditanam di Grass Tiles.
            throw new NotAquaticException("Plant tipe aquatic hanya bisa ditanam di pool tiles!");
        }
        if(map.getTile(row, column).getPlanted() != null){ // Exception untuk menanam tanaman di Tiles yang sudah terisi.
            throw new TileOccupiedException("Tile sudah terisi oleh tanaman lain!");
        }
        if(!(plant.getCDStatus())){ // Exception untuk menanam tanaman yang masih cooldown.
            throw new CooldownException("Tanaman masih dalam cooldown. Tunggu beberapa saat lagi untuk menanam tanaman ini!");
        }
        if(sun.getSun() < plant.getCost()){ // Exception untuk menanam tanaman namun Sun tidak cukup untuk menanam tanaman tersebut.
            throw new InsufficientSunException("Sun tidak cukup untuk menanam tanaman ini!");
        }
        // Jika lolos dari exception, maka :
        // Sun akan berkurang sesuai dengan cost tanaman yang ditanam
        // Tile akan menyimpan info tanaman yang ditanam
        // Cooldown tanaman akan dimulai
        sun.reduceSun(plant.getCost());
        map.getTile(row, column).setPlanted(plant);
        plant.setOnCooldown(true);
        timer.schedule(new TimerTask() {
            public void run(){
                plant.setOnCooldown(false);
            }
        }, plant.getCooldown()*1000);

    }

    public void digPlants(int row, int column){
        if (map.getTile(row, column) == null){
            System.out.println("Tidak ada tanaman di slot ini");
            return;
        }
        else{
            if (map.getTile(row, column) instanceof PoolTiles && map.getTile(row, column).getPlanted().is_aquatic == false){
                Lilypad lilypad = new Lilypad();
                map.getTile(row, column).setPlanted(lilypad);
                return;
            }
            else{
                map.getTile(row, column).setPlanted(null);
            }
        }
    }
}

class InsufficientSunException extends Exception{
    public InsufficientSunException(String message){
        super(message);
    }
}

class TileOccupiedException extends Exception{
    public TileOccupiedException(String message){
        super(message);
    }
}

class CooldownException extends Exception{
    public CooldownException(String message){
        super(message);
    }
}

class NotAquaticException extends Exception{
    public NotAquaticException(String message){
        super(message);
    }
}
