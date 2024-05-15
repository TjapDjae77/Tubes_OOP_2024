package source.Inventory;
import source.Characters.Plants.*;
import source.Inventory.Inventory;
public class Tester {
    //Make me a main that tests inventory.java features here
    public static void main(String[] args) {
        Plants plant1 = new Peashooter("Peashooter", 100, 50, 1, null, 1, 1, 0);
        Plants plant2 = new Sunflower("Sunflower", 50, 25, 1, null, 1, 1, 0, 0);
        Plants plant3 = new Wallnut("Wallnut", 200, 100, 1, null, 1, 1, 0);
        Plants plant4 = new Snowpea("Cherry Bomb", 150, 75, 1, null, 1, 1, 0);
        Plants plant5 = new Tallnut("Potato Mine", 50, 25, 1, null, 1, 1, 0);
        Plants plant6 = new Spikeweed("Snow Pea", 125, 75, 1, null, 1, 1, 0);
        Inventory inventory = new Inventory();
        // Inventory.plantsList = new Plants[]{plant1, plant2, plant3, plant4, plant5, plant6};
        // inventory.getGameDeck();
        inventory.selectPlant(plant1, 0);
        inventory.selectPlant(plant2, 1);
        inventory.selectPlant(plant3, 2);
        inventory.selectPlant(plant4, 3);
        inventory.selectPlant(plant5, 4);
        inventory.selectPlant(plant6, 5);
        inventory.getGameDeck();
        System.out.println();
        inventory.switchPlants(0, 1, 2);
        inventory.switchPlants(2, 3,2);
        inventory.switchPlants(4, 5,2);
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
