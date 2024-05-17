package source.Characters.Plants;
import source.Characters.Characters;

import source.Map.*;

public abstract class Plants extends Characters{
    protected int cost;
    protected int range;
    protected int cooldown;
    protected boolean on_cooldown;
    protected int row;
    protected int column;

    public Plants(String name, int health, int attack_damage, int attack_speed, int cost, int range, int cooldown, boolean is_aquatic, int row, int column) {
        super(name, health, attack_damage, attack_speed, is_aquatic);
        this.cost = cost;
        this.range = range;
        this.cooldown = cooldown;
        this.on_cooldown = false;
    }

    public int getCost() {
        return this.cost;
    }

    public int getRange() {
        return this.range;
    }

    public int getCooldown() {
        return this.cooldown;
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

    public boolean getCDStatus(){
        return on_cooldown;
    }
    
    public void setOnCooldown(boolean cdStatus){
        this.on_cooldown = cdStatus;
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

    public boolean validatePosition(Tiles tile) {
        if (is_aquatic) {
            return tile instanceof PoolTiles;
        } else {
            return tile instanceof GrassTiles || (tile instanceof PoolTiles && tile.getPlanted() instanceof Lilypad);
        }
    }

    public boolean setPosition(int row, int column, GameMap gameMap) {
        Tiles tile = gameMap.getTile(row, column);
        if (validatePosition(tile)) {
            this.row = row;
            this.column = column;
            tile.setPlanted(this);
            return true;
        } else {
            return false;
        }
    }

    public abstract void showDescription();
}


