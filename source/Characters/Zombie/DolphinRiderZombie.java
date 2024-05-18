package source.Characters.Zombie;

import source.Characters.Plants.Spikeweed;
import source.Characters.Plants.Tallnut;
import source.Map.GameMap;

public class DolphinRiderZombie extends Zombie {
    private boolean Dolphin;

    public DolphinRiderZombie() {
        super("Dolphin Rider Zombie", 175, 100, 1, true, 4, 0, 8);
        setCurrentColumn(randomColumn());
        this.Dolphin = true;
    }

    public void jump(){
        GameMap gameMap = new GameMap();
        int row = getCurrentRow(); 
        int column = getCurrentColumn(); 
        if (gameMap.getTile(row, column).getPlanted() != null) {
            if (gameMap.getTile(row + 1, column).getPlanted() instanceof Spikeweed) {
                return;
            } else if (gameMap.getTile(row, column).getPlanted() instanceof Tallnut) {
                this.Dolphin = false;
                setOriginalSpeed(10);
                return;
            } else {
                gameMap.getTile(row, column).setPlanted(null);
                setCurrentColumn(column - 1);
                this.Dolphin = false;
                setOriginalSpeed(10);
            }
        } else {
            return;
        }
    }

    public void showDescription() {
        System.out.println("Name : " + name);
        System.out.println("Health : " + health);
        System.out.println("Attack Damage : " + attack_damage);
        System.out.println("Attack Speed : " + attack_speed);
        System.out.println("Is Aquatic : " + is_aquatic);
        System.out.println("Speed : " + speed);
        System.out.println("Dolphin : " + Dolphin);
    }
}