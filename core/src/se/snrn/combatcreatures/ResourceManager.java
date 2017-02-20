package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class ResourceManager {
    public static Sprite wall = new Sprite(new Texture(Gdx.files.internal("wall.png")));
    public static Sprite floor = new Sprite(new Texture(Gdx.files.internal("floor.png")));
    public static Sprite fog = new Sprite(new Texture(Gdx.files.internal("fog.png")));
    public static Sprite player = new Sprite(new Texture(Gdx.files.internal("llama/llama.png")));
    public static Sprite creature = new Sprite(new Texture(Gdx.files.internal("creature.png")));
    public static Sprite creatureDead = new Sprite(new Texture(Gdx.files.internal("creature_dead.png")));
    private static ArrayList<Sprite> walls;
    private static ArrayList<Sprite> dreamyWalls;
    public static Sprite target = new Sprite(new Texture(Gdx.files.internal("target.png")));

    public ResourceManager() {
        walls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            walls.add(new Sprite(new Texture(Gdx.files.internal("tiles/wall_"+i+".png"))));
        }
        dreamyWalls = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            dreamyWalls.add(new Sprite(new Texture(Gdx.files.internal("dreamy/dreamy_"+i+".png"))));
        }
    }

    public static Sprite getWallFromBitMask(int tileValue) {
        System.out.println(tileValue);
        return walls.get(tileValue);
    }
    public static Sprite getDreamyWallFromBitMask(int tileValue) {
        System.out.println(tileValue);
        return dreamyWalls.get(tileValue);
    }
}
