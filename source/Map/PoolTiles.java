package source.Map;

import java.awt.*;

public class PoolTiles extends Tiles {
    public PoolTiles(){
        super("Pool");
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLUE); // Warna biru
        g.fillRect(x, y, width, height);
    }
}
