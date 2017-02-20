package se.snrn.combatcreatures;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CombatCreatures extends Game{
    public static final int TILE_SIZE = 32;
    MissionScreen missionScreen;
    SpriteBatch spriteBatch;


    public CombatCreatures() {

    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        missionScreen = new MissionScreen(spriteBatch);
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
