package source.Characters.Zombie;
import source.Characters.Characters;
import source.Characters.Plants.Plants;
import source.Characters.Plants.Spikeweed;
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
    }

    public boolean getIsAquatic() {
        return is_aquatic;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (this.health < 0) {
            this.health = 0; 
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
        if (is_aquatic){
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

    public abstract void showDescription();

    public void attackPlant(Plants plant) {
        if (plant instanceof Spikeweed) {
            System.out.println(this.name + " cannot eat Spikeweed!");
        } else {
            plant.setHealth(this.attack_damage);
            System.out.println(this.name + " attacks " + plant.getName() + " for " + this.attack_damage + " damage.");
            System.out.println(plant.getName() + " has " + plant.getHealth() + " health remaining.");
        }
    }
}