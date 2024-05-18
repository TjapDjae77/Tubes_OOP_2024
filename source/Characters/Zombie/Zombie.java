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
    public int randomColumn() {
        if (getIsAquatic() == true){
            int [] numbers = {2,3};
            Random rand = new Random();
            return numbers[rand.nextInt(numbers.length)];
        }
        else{
            int [] numbers = {0,1,4,5};
            Random rand = new Random();
            return numbers[rand.nextInt(numbers.length)];
        }
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
            plant.setHealth(plant.getHealth() - getAttackDamage(), gameMap);
            System.out.println(getName() + " attacks " + plant.getName() + " for " + getAttackDamage() + " damage.");
            System.out.println(plant.getName() + " has " + plant.getHealth() + " health remaining.");
        }
    }
}