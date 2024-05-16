package source.Deck;

import java.io.NotActiveException;

import source.Characters.Plants.Plants;
import source.Inventory.Inventory;
import source.Sun.Sun;
import source.Map.GameMap;

public class Deck{
    private Plants[] currentDeck;
    Inventory inventory = new Inventory();
    Sun sun = new Sun();

    public Deck(){
        this.currentDeck = inventory.getGameDeck();
    }

    public void plantPlants(Plants plant, int row, int column) throws TileOccupiedException, InsufficientSunException, CooldownException, NotAquaticException{
        if(!(GameMap.getTile(row, column) instanceof PoolTiles)){
            throw new NotActiveException("Plant tipe aquatic hanya bisa ditanam di pool tiles!");
        }
        if(currentDeck[row] != null){
            System.out.println("Slot sudah terisi");
            return;
        }
        currentDeck[row] = plant;
        sun.reduceSun(plant.getCost());

    }

    public void digPlants(){

    }
}
