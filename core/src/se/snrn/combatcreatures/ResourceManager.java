package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import se.snrn.combatcreatures.items.consumable.Consumable;
import se.snrn.combatcreatures.map.Tile;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ResourceManager {
    public static Sprite wall = new Sprite(new Texture(Gdx.files.internal("wall.png")));
    public static Sprite floor = new Sprite(new Texture(Gdx.files.internal("floor.png")));
    public static Sprite fog = new Sprite(new Texture(Gdx.files.internal("fog.png")));
    public static Sprite player = new Sprite(new Texture(Gdx.files.internal("llama/llama2.png")));
    public static Sprite boots = new Sprite(new Texture(Gdx.files.internal("item/boots.png")));
    //public static Sprite player = new Sprite(new Texture(Gdx.files.internal("llama/llama.png")));
    public static Sprite creature = new Sprite(new Texture(Gdx.files.internal("creature/vampire.png")));
    public static Sprite creatureDead = new Sprite(new Texture(Gdx.files.internal("creature_dead.png")));
    private static ArrayList<Sprite> walls;
    private static ArrayList<Sprite> dreamyWalls;
    public static Sprite target = new Sprite(new Texture(Gdx.files.internal("target.png")));
    public static Sprite cursor = new Sprite(new Texture(Gdx.files.internal("ui/cursor.png")));
    public static BitmapFont font = new BitmapFont();
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
    public static Sprite doorClosed = new Sprite(new Texture(Gdx.files.internal("tiles/door_closed.png")));
    private static ArrayList<Sprite> rainbowWalls;
    public static Sprite cloud = new Sprite(new Texture(Gdx.files.internal("rainbow/cloud.png")));
    public static Sprite sky = new Sprite(new Texture(Gdx.files.internal("rainbow/sky.png")));
    public static Sprite grass = new Sprite(new Texture(Gdx.files.internal("rainbow/grass.png")));
    public static Sprite trackTop = new Sprite(new Texture(Gdx.files.internal("tiles/track_top.png")));
    public static Sprite trackBottom = new Sprite(new Texture(Gdx.files.internal("tiles/track_bottom.png")));
    public static Sprite trackMiddle = new Sprite(new Texture(Gdx.files.internal("tiles/track_middle.png")));
    public static Sprite hill = new Sprite(new Texture(Gdx.files.internal("map/hill.png")));

    public static Sprite train = new Sprite(new Texture(Gdx.files.internal("map/train.png")));

    public static Sprite brokenTrack = new Sprite(new Texture(Gdx.files.internal("map/track_broken.png")));
    private static ArrayList<Sprite> sixteenWalls;


    public ResourceManager() {
        walls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            walls.add(new Sprite(new Texture(Gdx.files.internal("tiles/wall_" + i + ".png"))));
        }
        dreamyWalls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            dreamyWalls.add(new Sprite(new Texture(Gdx.files.internal("dreamy/dreamy_" + i + ".png"))));
        }
        rainbowWalls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            rainbowWalls.add(new Sprite(new Texture(Gdx.files.internal("rainbow/rainbow_" + i + ".png"))));
        }
        sixteenWalls = new ArrayList<>();
        for (int i = 0; i < 48; i++) {
            sixteenWalls.add(new Sprite(new Texture(Gdx.files.internal("newwalls/" + i + ".png"))));
        }
    }

//    public static Sprite getWallFromBitMask(int tileValue) {
//
//        return walls.get(tileValue);
//    }

    public static Sprite getDreamyWallFromBitMask(int tileValue) {

        return dreamyWalls.get(tileValue);
    }

    public static Sprite getCreatureSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("creature/" + spriteString)));
    }

    public static Sprite getSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("item/" + spriteString)));
    }


    static HashMap<Integer, Integer> fillGfxHash() {
        gfx = new HashMap<Integer, Integer>();
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


    static HashMap<Integer, Integer> gfx;

    public static Sprite getWallFromBitmask(int tileValue) {
        if (gfx == null) {
            gfx = fillGfxHash();
        }

        System.out.println(tileValue);
        return sixteenWalls.get(tileValue);
    }


    public static Sprite getRainbowWallFromBitMask(int tileValue) {


        return rainbowWalls.get(tileValue);

    }

    public static Sprite getTrackFromBitMask(int tileValue) {
        return new Sprite(new Texture(Gdx.files.internal("map/track_" + tileValue + ".png")));

    }
}
