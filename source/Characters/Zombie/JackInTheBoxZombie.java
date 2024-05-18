package source.Characters.Zombie;

import source.Characters.Plants.Spikeweed;
import source.Map.GameMap;

public class JackInTheBoxZombie extends Zombie {

    public JackInTheBoxZombie() {
        super("Jack In The Box Zombie", 175, 5000, 1, false, 2.5, 8, 0);
        setCurrentColumn(randomColumn());

    }

    public void explode() {
        GameMap gameMap = new GameMap();
        int row = getCurrentRow(); 
        int column = getCurrentColumn();
        if (gameMap.getTile(row, column).getPlanted() != null) {
            if (gameMap.getTile(row, column).getPlanted() instanceof Spikeweed) {
                return;
            } else {
                gameMap.getTile(row, column).getPlanted().setHealth(0, gameMap);
                setHealth(0);
            }
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
    }
}