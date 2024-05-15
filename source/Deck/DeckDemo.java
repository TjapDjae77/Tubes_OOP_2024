package source.Deck;

import source.Characters.Plants.Peashooter;
import source.Characters.Plants.Wallnut;

import source.Characters.Plants.Plants;
import source.Inventory.Inventory;

public class DeckDemo {
    public static void main(String[] args) {
        Plants plant1 = new Peashooter("Peashooter", 100, 25, 4, null, 100, -1, 10, false);
        Plants plant2 = new Wallnut("Wall-nut", 1000, 0, 0, null, 50, 0, 20, false);
        Deck deck = new Deck();

    }
}
