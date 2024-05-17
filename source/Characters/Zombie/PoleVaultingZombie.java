package source.Characters.Zombie;

import source.Characters.Plants.*;
import source.Map.*;

public class PoleVaultingZombie extends Zombie {
    private boolean pole = true;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, 2.5, 8,0);
        setCurrentColumn(randomColumn());
    }

    public void jump(){
        GameMap gameMap = new GameMap();
        int row = getCurrentRow(); 
        int column = getCurrentColumn(); 
        if (gameMap.getTile(row + 1, column).getPlanted() != null) {
            if (gameMap.getTile(row + 1, column).getPlanted() instanceof Spikeweed) {
                return;
            } else if (gameMap.getTile(row + 1, column).getPlanted() instanceof Tallnut) {
                this.pole = false;
                setOriginalSpeed(5);
                return;
            } else {
                gameMap.getTile(row + 1, column).setPlanted(null);
                setCurrentRow(row + 1);
                this.pole = false;
                setOriginalSpeed(5);
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
        System.out.println("Pole " + pole);
    }
}