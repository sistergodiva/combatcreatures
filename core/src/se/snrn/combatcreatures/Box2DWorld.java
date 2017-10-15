package se.snrn.combatcreatures;


import box2dLight.Light;
import box2dLight.PointLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;
import shaders.DiffuseShader;
import shaders.Gaussian;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Box2DWorld implements Updatable, Renderable {

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    private RayHandler rayHandler;
    private PointLight pointLight;


    public Box2DWorld(OrthographicCamera camera, TrainStopMap trainStopMap) {
        this.camera = camera;

        world = new World(new Vector2(0, 0), true);
        rayHandler = new RayHandler(world);

        rayHandler.setCombinedMatrix(camera);

        box2DDebugRenderer = new Box2DDebugRenderer();



        pointLight = new PointLight(rayHandler, 100, Color.YELLOW, 100, 0, 0);

        rayHandler.setAmbientLight(0.25f);

        for (Tile[] tile : trainStopMap.getTiles()) {
            for (Tile aTile : tile) {
                if(aTile.getType() == TileType.WALL) {
                    newBox(aTile);
                }
            }
        }

        rayHandler.setBlur(true);
    }

    @Override
    public void update(float delta) {

        world.step(delta, 8, 3);
        rayHandler.setCombinedMatrix(camera);

        rayHandler.update();

    }

    @Override
    public void render(Batch batch) {
        rayHandler.render();
        box2DDebugRenderer.render(world, camera.combined);
    }

    public void setConeLightPosition(float x, float y) {
        pointLight.setPosition(x, y);
        pointLight.update();
    }

    public void newBox(Tile tile) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((tile.getX()*TILE_SIZE) + TILE_SIZE / 2, (tile.getY()*TILE_SIZE) + TILE_SIZE / 2);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(TILE_SIZE / 2, TILE_SIZE / 2);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, 0f);

        shape.dispose();
    }

    public void newPhysicsObject(Tile tile) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((tile.getX()*TILE_SIZE) + TILE_SIZE / 2, (tile.getY()*TILE_SIZE) + TILE_SIZE / 2);

        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(1f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit


        Fixture fixture = body.createFixture(fixtureDef);

        body.applyForceToCenter(1,1,true);

        circle.dispose();
    }

    public World getWorld() {
        return world;
    }

}