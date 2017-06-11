package se.snrn.combatcreatures.map.prefabs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;

/**
 * Created by snrn on 6/10/17.
 */
public class ImageToPrefab {


    public static MapComponent getMapFromImage(String fileName) {


        Pixmap mapPixmap = new Pixmap(Gdx.files.internal(fileName));


        int width = mapPixmap.getWidth();
        int height = mapPixmap.getHeight();


        Color color = new Color();

        Tile[][] tiles = new Tile[width][height];


        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                int val = mapPixmap.getPixel(i, mapPixmap.getHeight() - j - 1);
                Color.rgba8888ToColor(color, val);

                if (color.equals(Color.BLACK)) {
                    tiles[i][j] = new Tile(i, j, TileType.WALL);
                }
                if (color.equals(Color.WHITE)) {
                    tiles[i][j] = new Tile(i, j, TileType.FLOOR);
                }
                if (color.equals(Color.BLUE)) {
                    tiles[i][j] = new Tile(i, j, TileType.DOOR);

                }
            }
        }


        mapPixmap.dispose();
        return new MapSegment(tiles);
    }

}
