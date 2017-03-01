package se.snrn.combatcreatures.highscore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.snrn.combatcreatures.CombatCreatures;
import se.snrn.combatcreatures.ResourceManager;

public class HighScoreScreen implements Screen {
    Score score;
    ScoreSender scoreSender;
    private Batch uiBatch;
    private  SpriteBatch batch;
    private  CombatCreatures cc;


    public HighScoreScreen(Batch uiBatch, SpriteBatch batch, CombatCreatures combatCreatures) {
        this.uiBatch = uiBatch;
        this.batch = batch;
        cc = combatCreatures;


        score = new Score("asdasa", 99999999,111,1111, "2001-01-01 00:00:00","2001-01-01 00:00:00");
        scoreSender = new ScoreSender("http://snrnscore.azurewebsites.net/webapp/send");


    }

    @Override
    public void show() {
        scoreSender.sendScore(score);
        System.out.println("score sent");
    }

    private void update(float delta) {

        Gdx.graphics.setTitle("FPS: "+Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        uiBatch.begin();
        ResourceManager.pinkBox.draw(uiBatch, 0,0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
