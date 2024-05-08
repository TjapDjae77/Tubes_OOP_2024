// package source.Map;

import java.awt.*;

public abstract class Tiles {
    protected boolean unplanted;

    public Tiles(){
        unplanted = false;
    }

    public boolean getStatus(){
        return unplanted;
    }

    public void setStatus(boolean unplanted){
        this.unplanted = unplanted;
    }

    public abstract void draw(Graphics g, int x, int y, int width, int height);
}
