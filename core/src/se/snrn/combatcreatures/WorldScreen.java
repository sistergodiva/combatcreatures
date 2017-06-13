package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.snrn.combatcreatures.entities.enemies.CreatureFactory;
import se.snrn.combatcreatures.entities.enemies.CreatureManager;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.visualeffects.VisualEffectManager;
import se.snrn.combatcreatures.worldmap.PlayerTrain;
import se.snrn.combatcreatures.worldmap.WorldMapInputHandler;
import se.snrn.combatcreatures.worldmap.WorldMapUi;
import se.snrn.combatcreatures.worldmap.WorldMap;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class WorldScreen implements Screen {


    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    public static boolean debug = false;
    public static WorldMapUi worldMapUi;
    private final CombatCreatures cc;
    private Batch batch;
    private WorldMapInputHandler inputHandler;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private CreatureManager creatureManager;
    private Batch uiBatch;
    private ShapeRenderer shapeRenderer;
    public static VisualEffectManager visualEffectManager;
    public static WorldMap worldMap;

    private PlayerTrain playerTrain;



    public WorldScreen(Batch batch, SpriteBatch uiBatch, CombatCreatures combatCreatures) {
        cc = combatCreatures;
        new GameLog();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, orthographicCamera);
        viewport.apply();
        new ResourceManager();
        this.batch = batch;
        this.uiBatch = uiBatch;
        new CreatureFactory();
        worldMap = new WorldMap(60,40);


        playerTrain = new PlayerTrain(worldMap.getTrainTrack().get(0), worldMap);

        creatureManager = new CreatureManager();

        inputHandler = new WorldMapInputHandler(playerTrain);

        visualEffectManager = new VisualEffectManager();

        Gdx.input.setInputProcessor(inputHandler);


        worldMapUi = new WorldMapUi();


        shapeRenderer = new ShapeRenderer();





    }


    @Override
    public void show() {
        orthographicCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        orthographicCamera.update();
        viewport.apply();
    }

    private void update(float delta) {
        orthographicCamera.position.set(playerTrain.getTile().getX() * TILE_SIZE, playerTrain.getTile().getY() * TILE_SIZE, 0);


        orthographicCamera.update();

        playerTrain.update(delta);
        visualEffectManager.update(delta);
        creatureManager.update(delta);

        worldMapUi.update(delta);
        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        //trainStopMap.render(batch);
        worldMap.render(batch);

        creatureManager.render(batch);
        playerTrain.render(batch);
        visualEffectManager.render(batch);
//        for (Tile tile : line
//                ) {
//            ResourceManager.player.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
//            ResourceManager.player.draw(batch);
//        }
//        train.render(batch);
        batch.end();
        uiBatch.begin();
        worldMapUi.render(uiBatch);
        uiBatch.end();


        shapeRenderer.setProjectionMatrix(orthographicCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);


        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
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
