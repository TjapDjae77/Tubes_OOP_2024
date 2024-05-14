import javax.swing.*;
import java.awt.*;

public class GameMap extends JPanel {
    private final int width = 9;
    private final int height = 6;
    private Tiles[][] grid;
    
    public GameMap() {
        grid = new Tiles[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(j == 0){
                    grid[i][j] = new ProtectedTiles();
                }
                else if(j == width-1){
                    grid[i][j] = new SpawnTiles();
                }
                else if((i == 2) || (i == 3)){
                    grid[i][j] = new PoolTiles();
                }
                else{
                    grid[i][j] = new GrassTiles();
                }
            }
        }
    }
    
    public Tiles getTile(int row, int column){
        if (row >= 0 && row < grid.length && column >= 0 && column < grid[0].length) {
            return grid[row][column];
        } else {
            // Jika indeks yang diminta di luar rentang grid, throw Exception
            throw new IllegalArgumentException("Invalid row or column index");
        }
    }

    public int getHeight(){ return height; }
    
    public int getWidth(){ return width; }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 100; // Ukuran petak yang diinginkan (100x100)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // Menggambar petak pada koordinat (j*tileSize, i*tileSize)
                grid[i][j].draw(g, j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }
}