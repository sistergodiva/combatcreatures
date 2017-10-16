package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {
    public static Sprite wall = new Sprite(new Texture(Gdx.files.internal("wall.png")));
    public static Sprite floor = new Sprite(new Texture(Gdx.files.internal("floor.png")));
    public static Sprite fog = new Sprite(new Texture(Gdx.files.internal("fog.png")));
    //public static Sprite player = new Sprite(new Texture(Gdx.files.internal("llama/llama2.png")));
    public static Sprite boots = new Sprite(new Texture(Gdx.files.internal("item/boots.png")));
    public static Sprite player = new Sprite(new Texture(Gdx.files.internal("player4.png")));
    public static Sprite creature = new Sprite(new Texture(Gdx.files.internal("creature/vampire.png")));
    public static Sprite creatureDead = new Sprite(new Texture(Gdx.files.internal("creature_dead.png")));
    public static Sprite target = new Sprite(new Texture(Gdx.files.internal("ui/target.png")));
    public static Sprite cursor = new Sprite(new Texture(Gdx.files.internal("ui/cursor1.png")));
    public static BitmapFont font = new BitmapFont();
    public static Animation<Texture> cursorAnimation = new Animation<>(0.075f, getAnimation("ui/cursor", 4));
    public static GlyphLayout glyphLayout = new GlyphLayout();
    public static Sprite heart = new Sprite(new Texture(Gdx.files.internal("ui/heart.png")));
    public static Sprite magic = new Sprite(new Texture(Gdx.files.internal("ui/magic.png")));
    public static Sprite tooth = new Sprite(new Texture(Gdx.files.internal("ui/tooth.png")));
    public static Sprite white = new Sprite(new Texture(Gdx.files.internal("ui/white.png")));
    public static Sprite black = new Sprite(new Texture(Gdx.files.internal("ui/black.png")));
    public static Sprite red = new Sprite(new Texture(Gdx.files.internal("ui/red.png")));
    public static Sprite blue = new Sprite(new Texture(Gdx.files.internal("ui/blue.png")));
    public static Sprite pink = new Sprite(new Texture(Gdx.files.internal("ui/pink.png")));
    public static Sprite up = new Sprite(new Texture(Gdx.files.internal("tiles/up.png")));
    public static Sprite down = new Sprite(new Texture(Gdx.files.internal("tiles/down.png")));

    public static Sprite bullet = new Sprite(new Texture(Gdx.files.internal("bullet.png")));

    public static NinePatch pinkBox = new NinePatch(new Texture(Gdx.files.internal("ui/pink_box.png")), 6, 6, 6, 6);
    public static Sprite doorClosed = new Sprite(new Texture(Gdx.files.internal("door.png")));

    public static Sprite grass = new Sprite(new Texture(Gdx.files.internal("tilefloor.png")));
    private static ArrayList<Sprite> sixteenWalls;
    private static HashMap<Integer, Integer> tileMap;


    public ResourceManager() {
//        sixteenWalls = new ArrayList<>();
//        for (int i = 0; i < 48; i++) {
//            sixteenWalls.add(new Sprite(new Texture(Gdx.files.internal("awall/" + i + ".png"))));
//        }
        mapTiles();
        TextureRegion[][] test = TextureRegion.split(new Texture(Gdx.files.internal("tile_sheet.png")), 16, 16);

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


    static HashMap<Integer, Integer> fillGfxHash() {
        gfx = new HashMap<>();
        gfx.put(2, 1);
        gfx.put(8, 2);
        gfx.put(10, 3);
        gfx.put(11, 4);
        gfx.put(16, 5);
        gfx.put(18, 6);
        gfx.put(22, 7);
        gfx.put(24, 8);
        gfx.put(26, 9);
        gfx.put(27, 10);
        gfx.put(30, 11);
        gfx.put(31, 12);
        gfx.put(64, 13);
        gfx.put(66, 14);
        gfx.put(72, 15);
        gfx.put(74, 16);
        gfx.put(75, 17);
        gfx.put(80, 18);
        gfx.put(82, 19);
        gfx.put(86, 20);
        gfx.put(88, 21);
        gfx.put(90, 22);
        gfx.put(91, 23);
        gfx.put(94, 24);
        gfx.put(95, 25);
        gfx.put(104, 26);
        gfx.put(106, 27);
        gfx.put(107, 28);
        gfx.put(120, 29);
        gfx.put(122, 30);
        gfx.put(123, 31);
        gfx.put(126, 32);
        gfx.put(127, 33);
        gfx.put(208, 34);
        gfx.put(210, 35);
        gfx.put(214, 36);
        gfx.put(216, 37);
        gfx.put(218, 38);
        gfx.put(219, 39);
        gfx.put(222, 40);
        gfx.put(223, 41);
        gfx.put(248, 42);
        gfx.put(250, 43);
        gfx.put(251, 44);
        gfx.put(254, 45);
        gfx.put(255, 46);
        gfx.put(0, 47);

        return gfx;
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


    private static HashMap<Integer, Integer> gfx;

    public static Sprite getWallFromBitmask(int tileValue) {
        if (gfx == null) {
            gfx = fillGfxHash();
        }
        if (tileMap == null) {
            tileMap = mapTiles();
        }

        return sixteenWalls.get(tileMap.get(tileValue));
    }
}
