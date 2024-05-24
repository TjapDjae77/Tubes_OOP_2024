package source.Characters.Zombie;
import source.Characters.Characters;
import source.Characters.Plants.Plants;
import source.Characters.Plants.Spikeweed;
import source.Map.GameMap;
import java.util.Random;

public abstract class Zombie extends Characters {
    protected double speed;
    protected double originalSpeed;
    private int row;
    private int column;
    

    public Zombie(String name, int health, int attack_damage, int attack_speed, boolean is_aquatic, double speed,int row,int column) {
        super(name, health, attack_damage, attack_speed, is_aquatic);
        this.speed = speed;
        this.originalSpeed = speed;
        this.column = 10;
    }

    public void setHealth(int health) {
        getHealth();
        if (getHealth() < 0) {
            health = 0; 
        }
    }

    public double getSpeed() {
        return speed;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void setOriginalSpeed(double originalSpeed) {
        this.originalSpeed = originalSpeed;
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

    public void deadZombie(GameMap gameMap) {
        if (getHealth() <= 0){
            gameMap.deadZombie(this);
            System.out.println(getName() + " has died!");
        }
    }

    public abstract void showDescription();

    public void attackPlant(Plants plant, GameMap gameMap) {
        if (plant instanceof Spikeweed) {
            System.out.println(getName() + " cannot eat Spikeweed!");
        } else {
//            plant.setHealth(plant.getHealth() - getAttackDamage(), gameMap);
            System.out.println(getName() + " attacks " + plant.getName() + " for " + getAttackDamage() + " damage.");
            System.out.println(plant.getName() + " has " + plant.getHealth() + " health remaining.");
        }
    }
    public void move(GameMap gameMap) {
        new Thread(() -> {
            while (getHealth() > 0 && !gameMap.getGameOver()) {
                try {
                    Thread.sleep((long) (1000 * speed));//Kondisi klo mogok lagi makan
                    if (getHealth() > 0) {
                        if (getCurrentColumn() == 0) {
                            System.out.println(getName() + " has reached the house and eaten the brains!");
                            gameMap.setGameOver(true);
                        } else {
                            setCurrentColumn(getCurrentColumn() - 1);
                            System.out.println(getName() + " has moved to column " + getCurrentColumn() + ".");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void eat(Plants plant, GameMap gameMap) {
        new Thread(() -> {
            while (plant.getHealth() > 0 && getHealth() > 0 && !gameMap.getGameOver()) {
                attackPlant(plant, gameMap);
                try {
                    Thread.sleep(getAttackSpeed() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}