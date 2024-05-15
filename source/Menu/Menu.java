package source.Menu;

import java.util.Scanner;
import source.Inventory.Inventory;
import source.Characters.Zombie.Zombie;
import source.Characters.Plants.Plants; // Import the Plants class

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*** PVZ Game Menu ***");
            System.out.println("1. Start Game");
            System.out.println("2. Help");
            System.out.println("3. Plants List");
            System.out.println("4. Zombies List");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    // Call the method to start the game
                    // You might need to setup the deck of plants here
                    break;
                case 2:
                    // Call the method to show help
                    // This should include game instructions and command list
                    // ...

                case 3:
                    System.out.println("\n*** Plants List ***");
                    for (Plants plant : Inventory.plantsList) {
                        printPlant(plant);
                    }
                    // Call the method to show the list of plants
                    // This should include all attributes of the plants
                    break;
                case 4:
                    System.out.println("\n*** Zombies List ***");
                    for (Zombie zombie : zombieList) {
                        printZombie(zombie);
                    }
                    // Call the method to show the list of zombies
                    // This should include all attributes of the zombies
                    break;
                case 5:
                    System.out.println("Exiting the game...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }
}