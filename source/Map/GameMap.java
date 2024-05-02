package source.Map;

public class GameMap {
    private final int width = 9;
    private final int height = 6;
    private final Tiles[][] grid;
    
    public GameMap() {
        grid = new Tiles[width][height];
        initializeMap();
    }

    public void initializeMap(){
        for (int j = 0; j < height; j++) {
            grid[0][j] = new ProtectedTiles();
        }
        for (int i = 1; i < width-1; i++){
            for(int j = 0; j < height; j++){
                if((j == 2) || (j == 3)){
                    grid[i][j] = new PoolTiles();
                }
                else{
                    grid[i][j] = new LandTiles();
                }
            }
        }
        for(int j = 0; j < height; j++){
            grid[8][j] = new SpawnTiles();
;        }
    }
}
