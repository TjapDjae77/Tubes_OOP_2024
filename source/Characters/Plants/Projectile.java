// File: source/Characters/Plants/Projectile.java
package source.Characters.Plants;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import source.Map.GameMap;

import java.util.List;

import source.Characters.Zombie.Zombie;
import source.Characters.Zombie.ZombieList;

public class Projectile {
    private int attackDamage;
    private double speed; // tiles per second
    private int row;
    private int column;
    private boolean active;
    private String type;


    public Projectile(int attackDamage, double speed, int row, int column, String type) {
        this.attackDamage = attackDamage;
        this.speed = speed;
        this.row = row;
        this.column = column;
        this.active = true;
        this.type = type;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    private void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }
}

// DUMP
//    public void move() {
//        new Thread(() -> {
//            while (active) {
//                try {
//                    Thread.sleep((long) (1000 * speed));
//                    // TOLONG IMPLEMENTASI
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }

//    private void checkCollision(GameMap gameMap) {
//        ZombieList<Zombie> inGameZombies = gameMap.getInGameZombies();
//        List<Zombie> zombiesInRow = inGameZombies.getZombies().stream()
//                .filter(z -> z.getCurrentRow() == this.row && z.getCurrentColumn() == this.column)
//                .toList();
//
//        for (Zombie zombie : zombiesInRow) {
//            zombie.setHealth(zombie.getHealth() - attackDamage);
//            System.out.println("Projectile hits " + zombie.getName() + " for " + attackDamage + " damage.");
//            deactivate();
//            if (zombie.getHealth() <= 0) {
//                gameMap.deadZombie(zombie);
//            }
//            break;
//        }
//    }
