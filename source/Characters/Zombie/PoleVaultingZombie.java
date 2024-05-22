package source.Characters.Zombie;

import source.Characters.Plants.*;
import source.Map.*;

public class PoleVaultingZombie extends Zombie {
    private boolean pole = true;

    public PoleVaultingZombie() {
        super("Pole Vaulting Zombie", 175, 100, 1, false, 5, 0, 10);
        setCurrentColumn(randomColumn());
    }

    public void jump(){
        GameMap gameMap = new GameMap();
        int row = getCurrentRow();
        int column = getCurrentColumn();
        if (gameMap.getTile(row, column).getPlanted() != null) {
            if (gameMap.getTile(row, column).getPlanted() instanceof Spikeweed) {
                return;
            } else if (gameMap.getTile(row, column).getPlanted() instanceof Tallnut) {
                this.pole = false;
                setOriginalSpeed(10);
                return;
            } else {
                gameMap.getTile(row, column).setPlanted(null);
                setCurrentColumn(column - 1);
                this.pole = false;
                setOriginalSpeed(10);
            }
        } else {
            return;
        }
    }

    public void showDescription() {
        System.out.println("Name : " + getName());
        System.out.println("Health : " + getHealth());
        System.out.println("Attack Damage : " + getAttackDamage());
        System.out.println("Attack Speed : " + getAttackSpeed());
        System.out.println("Is Aquatic : " + getIsAquatic());
        System.out.println("Speed : " + speed);
        System.out.println("Pole " + pole);
    }
}