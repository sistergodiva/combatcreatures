package se.snrn.combatcreatures;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import se.snrn.combatcreatures.entities.CreatureFactory;
import se.snrn.combatcreatures.entities.CreatureManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.entities.Stats;
import se.snrn.combatcreatures.input.InputHandler;
import se.snrn.combatcreatures.input.InputStateMachine;
import se.snrn.combatcreatures.items.consumable.Consumable;
import se.snrn.combatcreatures.items.consumable.ConsumableFactory;
import se.snrn.combatcreatures.map.MapManager;
import se.snrn.combatcreatures.userinterface.GameLog;
import se.snrn.combatcreatures.userinterface.Ui;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class MissionScreen implements Screen {


    private static final int WORLD_WIDTH = 1280;
    private static final int WORLD_HEIGHT = 720;
    public static boolean debug = true;
    public static Ui ui;
    private InputStateMachine inputStateMachine;
    private Batch batch;
    private InputHandler inputHandler;
    private MapManager mapManager;
    private OrthographicCamera orthographicCamera;
    private Viewport viewport;
    private Player player;
    private CreatureManager creatureManager;
    private Vector3 mouseRaw;
    public static TurnManager turnManager;
    public Batch uiBatch;




    public MissionScreen(Batch batch) {
        new GameLog();
        orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, orthographicCamera);
        viewport.apply();
        new ResourceManager();
        this.batch = batch;
        uiBatch = new SpriteBatch();

        creatureManager = new CreatureManager(player);
        mapManager = new MapManager(creatureManager);
        player = new Player(mapManager.getStartTile(), mapManager, new Stats(1, 1, 1, 1, 1, 1));
        turnManager = new TurnManager(creatureManager);
        inputStateMachine = new InputStateMachine(player);
        inputHandler = new InputHandler(inputStateMachine, player);
        Gdx.input.setInputProcessor(inputHandler);

        mouseRaw = new Vector3();
        new CreatureFactory();



        Consumable consumable = ConsumableFactory.getNewConsumable(0);
        System.out.println(consumable);

        ui = new Ui(player);



    }

    @Override
    public void show() {
        orthographicCamera.position.set(WORLD_WIDTH / 2, WORLD_HEIGHT / 2, 0);
        orthographicCamera.update();
        viewport.apply();
    }

    private void update(float delta) {
        orthographicCamera.position.set(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE, 0);


        orthographicCamera.update();
        player.update(delta);
        creatureManager.update(delta);
        inputStateMachine.update(delta);
        Gdx.graphics.setTitle("FPS: "+Gdx.graphics.getFramesPerSecond());
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        mapManager.render(batch);

        creatureManager.render(batch);
        player.render(batch);
        inputStateMachine.render(batch);
        batch.end();
        uiBatch.begin();
        ui.render(uiBatch);
        uiBatch.end();
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
