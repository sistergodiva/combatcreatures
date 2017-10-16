package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {

    public static Sprite fog = new Sprite(new Texture(Gdx.files.internal("fog.png")));

    public static Sprite player = new Sprite(new Texture(Gdx.files.internal("player4.png")));

    public static BitmapFont font = new BitmapFont();
    public static Animation<Texture> cursorAnimation = new Animation<>(0.075f, getAnimation("ui/cursor", 4));
    public static GlyphLayout glyphLayout = new GlyphLayout();



    // COLORS
    public static Sprite white = new Sprite(new Texture(Gdx.files.internal("ui/white.png")));
    public static Sprite black = new Sprite(new Texture(Gdx.files.internal("ui/black.png")));
    public static Sprite red = new Sprite(new Texture(Gdx.files.internal("ui/red.png")));
    public static Sprite blue = new Sprite(new Texture(Gdx.files.internal("ui/blue.png")));
    public static Sprite pink = new Sprite(new Texture(Gdx.files.internal("ui/pink.png")));




    public static Sprite bullet = new Sprite(new Texture(Gdx.files.internal("bullet.png")));


    // UI
    public static NinePatch uiNinePatch = new NinePatch(new Texture(Gdx.files.internal("ui/uibox.png")), 6, 6, 6, 6);
    public static Sprite target = new Sprite(new Texture(Gdx.files.internal("ui/target.png")));
    public static Sprite cursor = new Sprite(new Texture(Gdx.files.internal("ui/cursor1.png")));

    public static Sprite heart = new Sprite(new Texture(Gdx.files.internal("ui/heart.png")));
    public static Sprite magic = new Sprite(new Texture(Gdx.files.internal("ui/magic.png")));
    public static Sprite tooth = new Sprite(new Texture(Gdx.files.internal("ui/tooth.png")));

    public static Sprite up = new Sprite(new Texture(Gdx.files.internal("tiles/up.png")));
    public static Sprite down = new Sprite(new Texture(Gdx.files.internal("tiles/down.png")));
    public static Sprite doorClosed = new Sprite(new Texture(Gdx.files.internal("tiles/door.png")));
    public static Sprite floor = new Sprite(new Texture(Gdx.files.internal("tiles/floor.png")));
    private static ArrayList<Sprite> sixteenWalls;
    private static HashMap<Integer, Integer> tileMap;
    public static Sprite doorNS = new Sprite(new Texture(Gdx.files.internal("tiles/doorNS.png")));


    public ResourceManager() {
        mapTiles();
        TextureRegion[][] test = TextureRegion.split(new Texture(Gdx.files.internal("tiles/tile_sheet.png")), 16, 16);

        sixteenWalls = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                sixteenWalls.add(new Sprite(test[i][j]));
            }
        }
    }

    static Texture[] getAnimation(String name, int number) {
        Texture[] animation = new Texture[number];

        animation[0] = new Texture(Gdx.files.internal("ui/cursor1.png"));
        animation[1] = new Texture(Gdx.files.internal("ui/cursor2.png"));
        animation[2] = new Texture(Gdx.files.internal("ui/cursor3.png"));
        animation[3] = new Texture(Gdx.files.internal("ui/cursor4.png"));
        return animation;
    }

    public static Sprite getCreatureSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("creature/" + spriteString)));
    }

    public static Sprite getSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("item/" + spriteString)));
    }


    static HashMap<Integer, Integer> mapTiles() {
        tileMap = new HashMap<>();
        tileMap.put(0, 7);
        tileMap.put(1, 55);
        tileMap.put(2, 62);
        tileMap.put(3, 54);
        tileMap.put(4, 18);
        tileMap.put(5, 56);
        tileMap.put(6, 40);
        tileMap.put(7, 35);
        tileMap.put(8, 1);
        tileMap.put(9, 61);
        tileMap.put(10, 60);
        tileMap.put(11, 57);
        tileMap.put(12, 59);
        tileMap.put(13, 7);
        tileMap.put(14, 24);
        tileMap.put(15, 5);
        tileMap.put(16, 47);
        tileMap.put(17, 39);
        tileMap.put(18, 0);
        tileMap.put(19, 16);
        tileMap.put(20, 19);
        tileMap.put(21, 2);
        tileMap.put(22, 53);
        tileMap.put(23, 46);
        tileMap.put(24, 17);
        tileMap.put(25, 45);
        tileMap.put(26, 28);
        tileMap.put(27, 15);
        tileMap.put(28, 31);
        tileMap.put(29, 26);
        tileMap.put(30, 10);
        tileMap.put(31, 52);
        tileMap.put(32, 44);
        tileMap.put(33, 38);
        tileMap.put(34, 9);
        tileMap.put(35, 25);
        tileMap.put(36, 33);
        tileMap.put(37, 11);
        tileMap.put(38, 27);
        tileMap.put(39, 36);
        tileMap.put(40, 41);
        tileMap.put(41, 21);
        tileMap.put(42, 12);
        tileMap.put(43, 13);
        tileMap.put(44, 42);
        tileMap.put(45, 37);
        tileMap.put(46, 22);
        tileMap.put(47, 22);


        return tileMap;
    }


    public static Sprite getWallFromBitmask(int tileValue) {

        if (tileMap == null) {
            tileMap = mapTiles();
        }

        return sixteenWalls.get(tileMap.get(tileValue));
    }
}
