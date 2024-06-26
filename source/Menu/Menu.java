//package source.Menu;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import source.Inventory.Inventory;
//import source.Sun.Sun;
//import source.Characters.Plants.*;
//import source.Characters.Zombie.*;
//import source.Deck.*;
//
//public class Menu {
//    public static ArrayList<Zombie> zombieList;
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int choice;
//
//        do {
//            System.out.println("\n*** PVZ Game Menu ***");
//            System.out.println("1. Start Game");
//            System.out.println("2. Help");
//            System.out.println("3. Plants List");
//            System.out.println("4. Zombies List");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    Inventory inventory = new Inventory();
//                    Deck deck = new Deck();
//                    inventory.getGameDeck();
//                    // Call the method to start the game
//                    // You might need to setup the deck of plants here
//                    break;
//                case 2:
//                    System.out.println("\n*** Game Instructions ***");
//                    System.out.println("1. Choose plants from the inventory to build your deck.");
//                    System.out.println("2. Start the game and place your plants strategically to defend your house.");
//                    System.out.println("3. Survive the zombie attacks and win the game!");
//                    System.out.println("\n*** Command List ***");
//                    System.out.println("1. Select plant from inventory: select <plant_name>");
//                    System.out.println("2. Place plant on the grid: place <plant_name> <row> <column>");
//                    System.out.println("3. Remove plant from the grid: remove <row> <column>");
//                    System.out.println("4. Show the grid: show grid");
//                    System.out.println("5. Show inventory: show inventory");
//                    System.out.println("6. End turn: end turn");
//                    // Call the method to show help
//                    // This should include game instructions and command list
//                    // ...
//
//                case 3:
//                    Lilypad lilypad = new Lilypad();
//                    Peashooter peashooter = new Peashooter();
//                    Seashroom seashroom = new Seashroom();
//                    Snowpea snowpea = new Snowpea();
//                    Spikeweed spikeweed = new Spikeweed();
//                    Squash squash = new Squash();
//                    Sunflower sunflower = new Sunflower();
//                    Tallnut tallnut = new Tallnut();
//                    TangleKelp tangleKelp = new TangleKelp();
//                    Wallnut wallnut = new Wallnut();
//                    Inventory.plantsList = new ArrayList<>();
//                    Inventory.plantsList.add(lilypad);
//                    Inventory.plantsList.add(peashooter);
//                    Inventory.plantsList.add(seashroom);
//                    Inventory.plantsList.add(snowpea);
//                    Inventory.plantsList.add(spikeweed);
//                    Inventory.plantsList.add(squash);
//                    Inventory.plantsList.add(sunflower);
//                    Inventory.plantsList.add(tallnut);
//                    Inventory.plantsList.add(tangleKelp);
//                    Inventory.plantsList.add(wallnut);
//                    System.out.println("\n*** Plants List ***");
//                    for (Plants plant : Inventory.plantsList) {
//                        plant.showDescription();
//                        System.out.println("--------------------");
//                    }
//                    // Call the method to show the list of plants
//                    // This should include all attributes of the plants
//                    break;
//                case 4:
//                    BucketheadZombie bucketheadZombie = new BucketheadZombie();
//                    ConeheadZombie coneheadZombie = new ConeheadZombie();
//                    DolphinRiderZombie dolphinRiderZombie = new DolphinRiderZombie();
//                    DuckyTubeZombie duckyTubeZombie = new DuckyTubeZombie();
//                    FootballZombie footballZombie = new FootballZombie();
//                    JackInTheBoxZombie jackInTheBoxZombie = new JackInTheBoxZombie();
//                    NewspaperZombie newspaperZombie = new NewspaperZombie();
//                    NormalZombie normalZombie = new NormalZombie();
////                    PoleVaultingZombie poleVaultingZombie = new PoleVaultingZombie();
//                    ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie();
//                    zombieList = new ArrayList<>();
//                    zombieList.add(bucketheadZombie);
//                    zombieList.add(coneheadZombie);
//                    zombieList.add(dolphinRiderZombie);
//                    zombieList.add(duckyTubeZombie);
//                    zombieList.add(footballZombie);
//                    zombieList.add(jackInTheBoxZombie);
//                    zombieList.add(newspaperZombie);
//                    zombieList.add(normalZombie);
////                    zombieList.add(poleVaultingZombie);
//                    zombieList.add(screenDoorZombie);
//                    System.out.println("\n*** Zombies List ***");
//                    for (Zombie zombie : zombieList) {
//                        zombie.showDescription();
//                        System.out.println("--------------------");
//                    }
//                    // Call the method to show the list of zombies
//                    // This should include all attributes of the zombies
//                    break;
//                case 5:
//                    System.out.println("Exiting the game...");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
//            }
//        } while (choice != 5);
//
//        scanner.close();
//    }
//}