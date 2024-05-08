// package source.Map;

import java.awt.*;

public class GrassTiles extends Tiles{
    public GrassTiles(){
        super();
    }

    @Override
    public void draw(Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.GREEN); // Warna hijau
        g.fillRect(x, y, width, height);
    }
}
