package source.Characters.Zombie;

import source.Characters.Plants.Spikeweed;
import source.Map.GameMap;

public class JackInTheBoxZombie extends Zombie {

    public JackInTheBoxZombie() {
        super("Jack In The Box Zombie", 175, 5000, 1, false, 5, 0, 10);
        setCurrentColumn(randomColumn());

    }

    public void explode() {
        GameMap gameMap = new GameMap();
        int row = getCurrentRow(); 
        int column = getCurrentColumn();
//        if (gameMap.getTile(row, column).getPlanted() != null) {
//            if (gameMap.getTile(row, column).getPlanted() instanceof Spikeweed) {
//                return;
//            } else {
//                gameMap.getTile(row, column).getPlanted().setHealth(0, gameMap);
//                setHealth(0);
//            }
//        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
    }
}