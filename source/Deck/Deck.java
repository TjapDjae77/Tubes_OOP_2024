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

    public void plantPlants()
}
