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
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.entities.enemies.EnemySpawner;
import se.snrn.combatcreatures.entities.player.Experience;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.input.InputHandler;
import se.snrn.combatcreatures.input.InputStateMachine;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.trainstops.TileMapFactory;
import se.snrn.combatcreatures.map.trainstops.TileMap;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.userinterface.Ui;
import se.snrn.combatcreatures.visualeffects.VisualEffectManager;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class MissionScreen implements Screen {


    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    public static boolean debug = false;
    public static Ui ui;
    public static Box2DWorld box2DWorld;
    private final CombatCreatures cc;
    private InputStateMachine inputStateMachine;
    private Batch batch;
    private InputHandler inputHandler;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private Player player;
    private CreatureManager creatureManager;
    public static TurnManager turnManager;
    private Batch uiBatch;
    private ConsumableFactory consumableFactory;
    private ShapeRenderer shapeRenderer;
    public static VisualEffectManager visualEffectManager;
    public static TileMap tileMap;
    private Tile hooverTile;

    public MissionScreen(Batch batch, SpriteBatch uiBatch, CombatCreatures combatCreatures) {
        cc = combatCreatures;
        new GameLog();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, orthographicCamera);
        viewport.apply();
        new ResourceManager();
        this.batch = batch;
        this.uiBatch = uiBatch;
        new CreatureFactory();
        consumableFactory = new ConsumableFactory();
        tileMap = TileMapFactory.getTileMap();

        creatureManager = new CreatureManager();
        player = new Player(tileMap.getStartTile(), tileMap, new Stats(1, 1, 1, 1, 10, 1));
        creatureManager.setPlayer(player);
        turnManager = new TurnManager(creatureManager);
        inputStateMachine = new InputStateMachine(player, tileMap);
        inputHandler = new InputHandler(inputStateMachine, player, orthographicCamera);

        visualEffectManager = new VisualEffectManager();

        Gdx.input.setInputProcessor(inputHandler);


        ui = new Ui(player, tileMap);


        shapeRenderer = new ShapeRenderer();


        new Experience();
        box2DWorld = new Box2DWorld(orthographicCamera, tileMap);


        EnemySpawner.spawnEnemies(creatureManager, tileMap, 200);
        orthographicCamera.zoom = 0.25f;


    }

    public static Ui getUi() {
        return ui;
    }

    @Override
    public void show() {
        orthographicCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        orthographicCamera.update();
        viewport.apply();
        Gdx.input.setInputProcessor(inputHandler);


    }

    private void update(float delta) {
        orthographicCamera.position.set(player.getSprite().getX(), player.getSprite().getY(), 0);

        box2DWorld.update(delta);
        orthographicCamera.update();

        player.update(delta);

        creatureManager.update(delta);
        visualEffectManager.update(delta);
        box2DWorld.setConeLightPosition((player.getTile().getX() * TILE_SIZE) + TILE_SIZE / 2, (player.getTile().getY() * TILE_SIZE) + TILE_SIZE / 2);

        inputStateMachine.update(delta);
        ui.update(delta);

        Gdx.graphics.setTitle("FPS: " + Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        tileMap.render(batch);
        creatureManager.render(batch);
        player.render(batch);
        inputStateMachine.render(batch);
        visualEffectManager.render(batch);

        batch.end();

        //box2DWorld.render(batch);

//        batch.begin();
//        player.render(batch);
//        batch.end();

        uiBatch.begin();
        ui.render(uiBatch);
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
