// package source.Map;

import java.awt.*;

public class ProtectedTiles extends Tiles {
    public ProtectedTiles(){
        super("Protected");
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.RED); // Warna merah
        g.fillRect(x, y, width, height);
    }
}
