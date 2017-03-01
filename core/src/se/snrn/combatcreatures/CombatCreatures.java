package se.snrn.combatcreatures;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.combatcreatures.highscore.HighScoreScreen;

public class CombatCreatures extends Game{
    public static final int TILE_SIZE = 32;
    MissionScreen missionScreen;
    SpriteBatch spriteBatch;
    SpriteBatch uiBatch;
    HighScoreScreen highScoreScreen;

    public CombatCreatures() {

    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        missionScreen = new MissionScreen(spriteBatch,uiBatch, this);
        highScoreScreen = new HighScoreScreen(spriteBatch, uiBatch, this);

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
