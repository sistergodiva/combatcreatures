package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceManager {
    public static Sprite wall = new Sprite(new Texture(Gdx.files.internal("wall.png")));
    public static Sprite floor = new Sprite(new Texture(Gdx.files.internal("floor.png")));
    public static Sprite fog = new Sprite(new Texture(Gdx.files.internal("fog.png")));
    public static Sprite player = new Sprite(new Texture(Gdx.files.internal("llama/llama.png")));
    public static Sprite creature = new Sprite(new Texture(Gdx.files.internal("creature.png")));
    public static Sprite creatureDead = new Sprite(new Texture(Gdx.files.internal("creature_dead.png")));
}
