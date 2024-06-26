package source.Characters.Plants;
import java.util.List;
import source.GUI.*;
import source.Characters.Characters;
import source.Characters.Zombie.Zombie;
import source.Characters.Zombie.ZombieList;
import source.Map.*;

public abstract class Plants extends Characters{
    protected int cost;
    protected int range;
    protected int cooldown;
    protected boolean on_cooldown;
    protected int row;
    protected int column;

    public Plants(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean is_aquatic, int row, int column) {
        super(name, health, attack_damage, attack_speed, is_aquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.on_cooldown = false;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRange() {
        return this.range;
    }

    public int getCooldown() {
        return this.cooldown;
    }

//    public void setHealth(int health, GameMap gameMap) {
//        this.setHealth(health);
//        if (this.getHealth() <= 0) {
//            gameMap.getTile(this.row, this.column).setPlanted(null);
//        }
//    }

    public boolean getCDStatus(){
        return on_cooldown;
    }
    
    public void setOnCooldown(boolean cdStatus){
        this.on_cooldown = cdStatus;
    }

    public int getCurrentRow() {
        return this.row;
    }

    public int getCurrentColumn() {
        return this.column;
    }

    public void setCurrentRow(int row) {
        this.row = row;
    }

    public void setCurrentColumn(int column) {
        this.column = column;
    }

//    public boolean validatePosition(Tiles tile) {
//        if (this.getIsAquatic()) {
//            return tile instanceof PoolTiles;
//        } else {
//            return tile instanceof GrassTiles || (tile instanceof PoolTiles && tile.getPlanted() instanceof Lilypad);
//        }
//    }

//    public boolean setPosition(int row, int column, GameMap gameMap) {
//        Tiles tile = gameMap.getTile(row, column);
//        if (validatePosition(tile)) {
//            this.row = row;
//            this.column = column;
//            tile.setPlanted(this);
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void attack(Zombie zombie) {
        if (zombie != null) {
            zombie.setHealth(zombie.getHealth() - this.getAttackDamage());
            System.out.println(this.getName() + " attacks " + zombie.getName() + " for " + this.getAttackDamage() + " damage.");
            System.out.println(zombie.getName() + " has " + zombie.getHealth() + " health remaining.");
        }
    }

//    public void attackZombiesInRange(GameMap gameMap) {
//        int range = this.getRange();
//        Zombie closestZombie = null;
//        int minDistance = Integer.MAX_VALUE;
//
//        // Obtain the list of zombies currently in the game
//        ZombieList<Zombie> inGameZombies = gameMap.getInGameZombies();
//        List<Zombie> zombiesInRow = inGameZombies.getZombies().stream()
//                .filter(z -> z.getCurrentRow() == this.row)
//                .toList();
//
//        if (range == -1) {
//            // Infinite range: search all zombies in the same row to the right
//            for (Zombie zombie : zombiesInRow) {
//                int zombieColumn = zombie.getCurrentColumn();
//                if (zombieColumn > this.column && zombieColumn < minDistance) {
//                    closestZombie = zombie;
//                    minDistance = zombieColumn;
//                }
//            }
//        } else {
//            // Finite range: search zombies within the specified range
//            for (Zombie zombie : zombiesInRow) {
//                int zombieColumn = zombie.getCurrentColumn();
//                if (zombieColumn > this.column && zombieColumn <= this.column + range && zombieColumn < minDistance) {
//                    closestZombie = zombie;
//                    minDistance = zombieColumn;
//                }
//            }
//        }
//        // Attack the closest zombie if found
//        if (closestZombie != null) {
//            if (this.canShoot()) {  // Check if the plant can shoot
//                shoot(gameMap);     // Highlight Create a projectile to attack the closest zombie
//            } else {
//                attack(closestZombie); // Perform a melee attack if the plant cannot shoot
//            }
//        }
//    }

//    public void shoot(GameMap gameMap) {
//        Projectile projectile = new Projectile(this.getAttackDamage(), 1.0, this.row, this.column);
//        projectile.move(gameMap);
//    }


//    public void startAttacking(GameMap gameMap) {
//        new Thread(() -> {
//            while (true) {
//                attackZombiesInRange(gameMap);
//                try {
//                    Thread.sleep(this.getAttackSpeed() * 1000); // Sleep to simulate attack speed
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

    public abstract boolean canShoot();

    public abstract void showDescription();
}


