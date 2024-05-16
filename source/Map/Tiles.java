// package source.Map;

import java.awt.*;

public abstract class Tiles {
    protected boolean unplanted;
    private String tilesType;

    public Tiles(String tilesType){
        unplanted = false;
        this.tilesType = tilesType;
    }

    public boolean getStatus(){
        return unplanted;
    }

    public void setStatus(boolean unplanted){
        this.unplanted = unplanted;
    }

    public String getTilesType(){
        return tilesType;
    }
    
    public abstract void draw(Graphics g, int x, int y, int width, int height);
}
