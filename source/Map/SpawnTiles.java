// package source.Map;

import java.awt.*;

public class SpawnTiles extends Tiles {
    public SpawnTiles(){
        super();
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.YELLOW); // Warna kuning
        g.fillRect(x, y, width, height);
    }
}
