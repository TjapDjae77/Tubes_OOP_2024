package source.Inventory;

import source.Characters.Plants.Plants;
import java.util.ArrayList;
import java.util.Arrays;

public class Inventory {
    public static int tanamanInDeck = 0;
    public static ArrayList<Plants> gameDeck;
    public static ArrayList<Plants> plantsList;

    public Inventory(Plants plant) {
        this.selectedPlant = plant;
        gameDeck = new ArrayList<>(Arrays.asList(new Plants[6]));
        plantsList = new ArrayList<>();
    }

    public Inventory() {
        this.selectedPlant = null;
        gameDeck = new ArrayList<>(Arrays.asList(new Plants[6]));
        plantsList = new ArrayList<>();
    }

    public void selectPlant(Plants plant, int index) {
        if (index < 0 || index > 5) {
            return;
        }
        else if (gameDeck.get(index) != null) {
            System.out.println("Invalid operation. The selected slot "+(index+1)+ " is already occupied.");
            //search for empty slot and insert there
            for (int i = 0; i < 6; i++) {
                if (gameDeck.get(i) == null) {
                    gameDeck.set(i, plant);
                    tanamanInDeck++;
                    System.out.println(plant.getName() + " has been added to slot " + (i+1) + " in your deck.");
                    return;
                }
            }
        }
        gameDeck.set(index, plant);
        tanamanInDeck++;
    }

    public void switchPlants(int index1, int index2, int category) {
        // Ensure indices are different and within valid range
        if (index1 == index2 || index1 < 0 || index1 > 5 || index2 < 0 || index2 > 5) {
            return;
        }

        // Swap within inventory
        if (category == 1) {
            // Ensure neither slot is empty
            if (plantsList.get(index1) == null || plantsList.get(index2) == null) {
                return;
            }

            // Swap
            Plants[] temp = plantsList.toArray(new Plants[0]);
            temp[index1] = plantsList.get(index2);
            temp[index2] = plantsList.get(index1);
            plantsList = new ArrayList<>(Arrays.asList(temp));
            System.out.println(plantsList.get(index1).getName() + " in inventory has been switched with " + plantsList.get(index2).getName()+"\n");
        }
        // Swap within gameDeck
        else {
            // Ensure neither slot is empty
            if (gameDeck.get(index1) == null || gameDeck.get(index2) == null) {
                return;
            }

            // Swap
            Plants temp = gameDeck.get(index1);
            gameDeck.set(index1, gameDeck.get(index2));
            gameDeck.set(index2, temp);
            System.out.println(gameDeck.get(index1).getName() + " in gamedeck has been switched with " + gameDeck.get(index2).getName()+"\n");
        }
    }

    public void removePlant(int index) {
        if (index >= 0 && index < 6 && gameDeck.get(index) != null) {
            gameDeck.set(index, null);
            tanamanInDeck--;
        } else {
            System.out.println("Invalid operation. The selected slot is either out of range or empty.");
        }
    }

    public Plants[] getGameDeck() {
        System.out.println("\n*** Game Deck ***");
        for (int i = 0; i < 6; i++) {
            if (gameDeck.get(i) != null) {
                System.out.println(String.valueOf(i+1)+". " + gameDeck.get(i).getName());
            }
        }
        if (tanamanInDeck < 6 && tanamanInDeck >= 0) {
            System.out.println("You have " + tanamanInDeck + " plants in your deck, please add " + (6 - tanamanInDeck) + " more plants.");
        }
        return gameDeck.toArray(new Plants[0]);
    }
}
