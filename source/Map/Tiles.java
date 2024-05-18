package source.Map;
import source.Characters.Plants.*;
import source.Characters.Zombie.Zombie;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class Tiles {
    private String tilesType;
    private Plants planted;
    private List<Zombie> zombies;

    public Tiles(String tilesType){
        this.tilesType = tilesType;
        this.planted = null;
        this.zombies = new ArrayList<>();
    }

    public String getTilesType(){
        return tilesType;
    }

    public Plants getPlanted(){
        return planted;
    }

    public void setPlanted(Plants planted){
        this.planted = planted;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public void deadPlanted(){
        this.planted = null;
    }
    public abstract void draw(Graphics g, int x, int y, int width, int height);
}
