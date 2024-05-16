package source.Inventory;

import source.Characters.Plants.*;
import source.Inventory.Inventory;

public class Tester {
    // Make me a main that tests inventory.java features here
    public static void main(String[] args) {
        Plants plant1 = new Peashooter();
        Plants plant2 = new Sunflower();
        Plants plant3 = new Wallnut();
        Plants plant4 = new Snowpea();
        Plants plant5 = new Tallnut();
        Plants plant6 = new Spikeweed();
        Inventory inventory = new Inventory();
        // Inventory.plantsList = new Plants[]{plant1, plant2, plant3, plant4, plant5,
        // plant6};
        // inventory.getGameDeck();
        inventory.selectPlant(plant1, 3);
        inventory.selectPlant(plant2, 3);
        inventory.selectPlant(plant3, 3);
        inventory.selectPlant(plant4, 3);
        inventory.selectPlant(plant5, 3);
        inventory.selectPlant(plant6, 3);
        inventory.getGameDeck();
        System.out.println();
        inventory.switchPlants(0, 1, 2);
        inventory.switchPlants(2, 3, 2);
        inventory.switchPlants(4, 5, 2);
        inventory.getGameDeck();
        System.out.println();
        inventory.removePlant(0);
        inventory.removePlant(1);
        inventory.removePlant(2);
        inventory.removePlant(3);
        inventory.removePlant(4);
        inventory.removePlant(5);
        inventory.getGameDeck();
        System.out.println("finished testing inventory.java features");
    }
}
