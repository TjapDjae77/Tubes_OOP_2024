package source.Deck;

import source.Characters.Plants.Plants;
import source.Inventory.Inventory;
import source.Sun.Sun;

public class Deck{
    private Plants[] currentDeck;
    Inventory inventory = new Inventory();
    Sun sun = new Sun();

    public Deck(){
        this.currentDeck = inventory.getGameDeck();
    }

    public void plantPlants(Plants plant, int row, int column){
        if(sun.getSun() < plant.getCost()){
            System.out.println("Sun tidak cukup");
            return;
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
