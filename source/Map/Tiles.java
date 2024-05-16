package source.Map.*;
import source.Characters.Plants.*;
import java.awt.*;

public abstract class Tiles {
    private String tilesType;
    private Plants planted;

    public Tiles(String tilesType){
        this.tilesType = tilesType;
        this.planted = null;
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

    public void deadPlanted(){
        this.planted = null;
    }
    public abstract void draw(Graphics g, int x, int y, int width, int height);
}
