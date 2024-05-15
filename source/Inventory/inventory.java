package source.Inventory;

import source.Characters.Plants.Plants;

public class Inventory {
    public static int tanamanInDeck = 0;
    private Plants selectedPlant;
    private Plants[] gameDeck;
    public static Plants[] plantsList;

    public Inventory() {
        this.selectedPlant = null;
        this.gameDeck = new Plants[6];
    }

    public void selectPlant(Plants plant, int index) {
        // LIST OF PLANTS
        if (gameDeck[index] == null) {
            gameDeck[index] = plant;
            tanamanInDeck++;
        } else {
            Plants temp = gameDeck[index];
            gameDeck[index] = plant;
            gameDeck[tanamanInDeck] = temp;
            this.selectedPlant = plant;
        }
    }

    public void switchPlants(int index1, int index2, int category) {
        if (index1 == index2) {
            return;
        }
        // Tuker inventory
        if (category == 1) {
            // Empty slot
            if (plantsList[index1] == null || plantsList[index2] == null || index1 > 6 || index2 > 6) {
                return;
            }
            // Tuker
            Plants temp = plantsList[index1];
            plantsList[index1] = plantsList[index2];
            plantsList[index2] = temp;
        }
        // Tuker gameDeck
        else {
            // Empty slot
            if (gameDeck[index1] == null || gameDeck[index2] == null || index1 > 6 || index2 > 6) {
                return;
            }
            // Tuker
            Plants temp = gameDeck[index1];
            gameDeck[index1] = gameDeck[index2];
            gameDeck[index2] = temp;
        }
    }

    public void removePlant(int index) {
        if (gameDeck[index] != null && index < 6) {
            gameDeck[index] = null;
            tanamanInDeck--;
        }
    }

    public Plants[] getGameDeck() {
        return gameDeck;
    }
}