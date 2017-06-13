package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import se.snrn.combatcreatures.items.consumable.Consumable;

import javax.swing.*;
import java.util.ArrayList;

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

    public static Sprite brokenTrack= new Sprite(new Texture(Gdx.files.internal("map/track_broken.png")));


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
    }

    public static Sprite getWallFromBitMask(int tileValue) {

        return walls.get(tileValue);
    }

    public static Sprite getDreamyWallFromBitMask(int tileValue) {

        return dreamyWalls.get(tileValue);
    }

    public static Sprite getCreatureSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("creature/" + spriteString)));
    }

    public static Sprite getSpriteFromString(String spriteString) {
        return new Sprite(new Texture(Gdx.files.internal("item/" + spriteString)));
    }

    public static Sprite getRainbowWallFromBitMask(int tileValue) {
        return rainbowWalls.get(tileValue);

    }

    public static Sprite getTrackFromBitMask(int tileValue) {
        return new Sprite(new Texture(Gdx.files.internal("map/track_"+tileValue+".png")));

    }
}
