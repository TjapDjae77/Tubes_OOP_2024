// File: source/Characters/Plants/Projectile.java
package source.Characters.Plants;

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

    public Projectile(int attackDamage, double speed, int row, int column) {
        this.attackDamage = attackDamage;
        this.speed = speed;
        this.row = row;
        this.column = column;
        this.active = true;
    }

    public void move(GameMap gameMap) {
        new Thread(() -> {
            while (active) {
                try {
                    Thread.sleep((long) (1000 * speed));
                    column++;
                    if (column >= gameMap.getWidth()) {
                        deactivate();
                    } else {
                        checkCollision(gameMap);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void checkCollision(GameMap gameMap) {
        ZombieList<Zombie> inGameZombies = gameMap.getInGameZombies();
        List<Zombie> zombiesInRow = inGameZombies.getZombies().stream()
                .filter(z -> z.getCurrentRow() == this.row && z.getCurrentColumn() == this.column)
                .toList();

        for (Zombie zombie : zombiesInRow) {
            zombie.setHealth(zombie.getHealth() - attackDamage);
            System.out.println("Projectile hits " + zombie.getName() + " for " + attackDamage + " damage.");
            deactivate();
            if (zombie.getHealth() <= 0) {
                gameMap.deadZombie(zombie);
            }
            break;
        }
    }

    private void deactivate() {
        this.active = false;
    }

    public boolean isActive() {
        return active;
    }
}
