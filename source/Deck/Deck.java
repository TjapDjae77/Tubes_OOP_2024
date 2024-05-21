//package source.Deck;
//
//import java.util.Timer;
//import java.util.TimerTask;
//import source.Characters.Plants.*;
//import source.Inventory.Inventory;
//import source.Sun.Sun;
//import source.Map.*;
//
//public class Deck {
//    private Plants[] currentDeck;
//    Inventory inventory = new Inventory();
//    Sun sun = new Sun();
//    GameMap map = new GameMap();
//    Timer timer = new Timer();
//
//    public Deck() {
//        this.currentDeck = inventory.getGameDeck();
//    }
//
//    public void plantPlants(Plants plant, int row, int column)
//            throws TileOccupiedException, InsufficientSunException, CooldownException, NotAquaticException {
//
//        if (!(map.getTile(row, column) instanceof PoolTiles) && plant.getIsAquatic()) { // Exception untuk tanaman
//                                                                                        // aquatic yang ingin ditanam di
//                                                                                        // Grass Tiles.
//            throw new NotAquaticException("Plant tipe aquatic hanya bisa ditanam di pool tiles!");
//        }
//<<<<<<< Updated upstream
//        if (map.getTile(row, column).getPlanted() != null) { // Exception untuk menanam tanaman di Tiles yang sudah
//                                                             // terisi.
//            throw new TileOccupiedException("Tile sudah terisi oleh tanaman lain!");
//        }
//        if (!(plant.getCDStatus())) { // Exception untuk menanam tanaman yang masih cooldown.
//            throw new CooldownException(
//                    "Tanaman masih dalam cooldown. Tunggu beberapa saat lagi untuk menanam tanaman ini!");
//        }
//        final Plants finalPlant = plant;
//        if (Sun.getSun() < finalPlant.getCost()) { // Exception untuk menanam tanaman namun Sun tidak cukup untuk
//                                                   // menanam tanaman tersebut.
//            throw new InsufficientSunException("Sun tidak cukup untuk menanam tanaman ini!");
//        }
//=======
////        if(map.getTile(row, column).getPlanted() != null){ // Exception untuk menanam tanaman di Tiles yang sudah terisi.
////            throw new TileOccupiedException("Tile sudah terisi oleh tanaman lain!");
////        }
//        if(!(plant.getCDStatus())){ // Exception untuk menanam tanaman yang masih cooldown.
//            throw new CooldownException("Tanaman masih dalam cooldown. Tunggu beberapa saat lagi untuk menanam tanaman ini!");
//        }
////        if(Sun.getSun() < plant.getCost()){ // Exception untuk menanam tanaman namun Sun tidak cukup untuk menanam tanaman tersebut.
////            throw new InsufficientSunException("Sun tidak cukup untuk menanam tanaman ini!");
////        }
//>>>>>>> Stashed changes
//        // Jika lolos dari exception, maka :
//        // Sun akan berkurang sesuai dengan cost tanaman yang ditanam
//        // Tile akan menyimpan info tanaman yang ditanam
//        // Cooldown tanaman akan dimulai
//<<<<<<< Updated upstream
//        if (plant instanceof Peashooter) {
//            Peashooter peashooter = (Peashooter) plant;
//            Sun.reduceSun(peashooter.getCost());
//            map.getTile(row, column).setPlanted(peashooter);
//            peashooter.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    peashooter.setOnCooldown(false);
//                }
//            }, peashooter.getCooldown() * 1000);
//        }
//        if (plant instanceof Sunflower) {
//            Sunflower sunflower = (Sunflower) plant;
//            Sun.reduceSun(sunflower.getCost());
//            map.getTile(row, column).setPlanted(sunflower);
//            sunflower.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    sunflower.setOnCooldown(false);
//                }
//            }, sunflower.getCooldown() * 1000);
//        }
//        if (plant instanceof Spikeweed) {
//            Spikeweed spikeweed = (Spikeweed) plant;
//            Sun.reduceSun(spikeweed.getCost());
//            map.getTile(row, column).setPlanted(spikeweed);
//            spikeweed.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    spikeweed.setOnCooldown(false);
//                }
//            }, spikeweed.getCooldown() * 1000);
//        }
//        if (plant instanceof Squash) {
//            Squash squash = (Squash) plant;
//            Sun.reduceSun(squash.getCost());
//            map.getTile(row, column).setPlanted(squash);
//            squash.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    squash.setOnCooldown(false);
//                }
//            }, squash.getCooldown() * 1000);
//        }
//        if (plant instanceof Tallnut) {
//            Tallnut tallnut = (Tallnut) plant;
//            Sun.reduceSun(tallnut.getCost());
//            map.getTile(row, column).setPlanted(tallnut);
//            tallnut.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    tallnut.setOnCooldown(false);
//                }
//            }, tallnut.getCooldown() * 1000);
//        }
//        if (plant instanceof TangleKelp) {
//            TangleKelp tangleKelp = (TangleKelp) plant;
//            Sun.reduceSun(tangleKelp.getCost());
//            map.getTile(row, column).setPlanted(tangleKelp);
//            tangleKelp.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    tangleKelp.setOnCooldown(false);
//                }
//            }, tangleKelp.getCooldown() * 1000);
//        }
//        if (plant instanceof Snowpea) {
//            Snowpea snowpea = (Snowpea) plant;
//            Sun.reduceSun(snowpea.getCost());
//            map.getTile(row, column).setPlanted(snowpea);
//            snowpea.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    snowpea.setOnCooldown(false);
//                }
//            }, snowpea.getCooldown() * 1000);
//        }
//        if (plant instanceof Lilypad) {
//            Lilypad lilypad = (Lilypad) plant;
//            Sun.reduceSun(lilypad.getCost());
//            map.getTile(row, column).setPlanted(lilypad);
//            lilypad.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    lilypad.setOnCooldown(false);
//                }
//            }, lilypad.getCooldown() * 1000);
//        }
//        if (plant instanceof Seashroom) {
//            Seashroom seashroom = (Seashroom) plant;
//            Sun.reduceSun(seashroom.getCost());
//            map.getTile(row, column).setPlanted(seashroom);
//            seashroom.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    seashroom.setOnCooldown(false);
//                }
//            }, seashroom.getCooldown() * 1000);
//        }
//        if (plant instanceof Wallnut) {
//            Wallnut wallnut = (Wallnut) plant;
//            Sun.reduceSun(wallnut.getCost());
//            map.getTile(row, column).setPlanted(wallnut);
//            wallnut.setOnCooldown(true);
//            timer.schedule(new TimerTask() {
//                public void run() {
//                    wallnut.setOnCooldown(false);
//                }
//            }, wallnut.getCooldown() * 1000);
//        }
//=======
////        Sun.reduceSun(plant.getCost());
////        map.getTile(row, column).setPlanted(plant);
//        plant.setOnCooldown(true);
//        timer.schedule(new TimerTask() {
//            public void run(){
//                plant.setOnCooldown(false);
//            }
//        }, plant.getCooldown()*1000);
//
//>>>>>>> Stashed changes
//    }
//
//    public void digPlants(int row, int column) {
//        if (map.getTile(row, column) == null) {
//            System.out.println("Tidak ada tanaman di slot ini");
//            return;
//<<<<<<< Updated upstream
//        } else {
//            if (map.getTile(row, column) instanceof PoolTiles
//                    && map.getTile(row, column).getPlanted().getIsAquatic() == false) {
//                Lilypad lilypad = new Lilypad();
//                map.getTile(row, column).setPlanted(lilypad);
//                return;
//            } else {
//                map.getTile(row, column).setPlanted(null);
//            }
//=======
//        }
//        else{
////            if (map.getTile(row, column) instanceof PoolTiles && map.getTile(row, column).getPlanted().getIsAquatic() == false){
////                Lilypad lilypad = new Lilypad();
////                map.getTile(row, column).setPlanted(lilypad);
////                return;
////            }
////            else{
////                map.getTile(row, column).setPlanted(null);
////            }
//>>>>>>> Stashed changes
//        }
//    }
//}
//
//class InsufficientSunException extends Exception {
//    public InsufficientSunException(String message) {
//        super(message);
//    }
//}
//
//class TileOccupiedException extends Exception {
//    public TileOccupiedException(String message) {
//        super(message);
//    }
//}
//
//class CooldownException extends Exception {
//    public CooldownException(String message) {
//        super(message);
//    }
//}
//
//class NotAquaticException extends Exception {
//    public NotAquaticException(String message) {
//        super(message);
//    }
//}
