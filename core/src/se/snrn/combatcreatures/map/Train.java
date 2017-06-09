package se.snrn.combatcreatures.map;


import com.badlogic.gdx.graphics.g2d.Batch;

public class Train implements MapComponent {

    private int posX;
    private int posY;
    private int width;
    private int height;
    public Tile[][] tiles;

    public Train(int width, int height, int posX, int posY) {
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;


        tiles = new Tile[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, TileType.FLOOR);
            }
        }

    }


    @Override
    public void render(Batch batch) {
        for (Tile[] tile : tiles) {
            for (Tile aTile : tile) {
                aTile.render(batch);
            }
        }
    }


    @Override
    public Tile getTile(int x, int y) {
        if (x < width &&
                x >= 0 &&
                y < height &&
                y >= 0 &&
                tiles[x][y] != null) {
            return tiles[x][y];
        }
        return null;
    }

    @Override
    public Tile[][] getTiles() {
        return tiles;
    }

    @Override
    public void setPosition(int x, int y) {

        posX = x;
        posY = y;

    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
