package source.GUI;

import javafx.scene.layout.Pane;

import source.Map.Tiles;

public class TilesPane extends Pane {
    private Tiles tiles;

    public TilesPane(Tiles tiles) {
        this.tiles = tiles;
    }
    public Tiles getTiles() {
        return tiles;
    }
    public void setTiles(Tiles tiles) {
        this.tiles = tiles;
    }
}
