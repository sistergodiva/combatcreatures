package se.snrn.combatcreatures;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CombatCreatures extends Game{
    public static final int TILE_SIZE = 16;
    public static final float DELAY = 0.25f;
    public static final float SPEED_MULTIPLIER = 8;
    MissionScreen missionScreen;
    SpriteBatch spriteBatch;
    SpriteBatch uiBatch;


    public CombatCreatures() {

    }



    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        missionScreen = new MissionScreen(spriteBatch,uiBatch, this);

        setScreen(missionScreen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
