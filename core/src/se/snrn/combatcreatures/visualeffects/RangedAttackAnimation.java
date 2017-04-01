package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class RangedAttackAnimation implements VisualEffect {

    private boolean done = false;
    private Tile start;
    private Tile end;
    Vector2 startVector;
    Vector2 endVector;
    Vector2 currentPosition;
    private float time;

    public RangedAttackAnimation(Tile start, Tile end) {
        this.start = start;
        this.end = end;


        startVector = new Vector2(start.getX() * TILE_SIZE, start.getY() * TILE_SIZE);
        endVector = new Vector2(end.getX() * TILE_SIZE, end.getY() * TILE_SIZE);
        currentPosition = new Vector2();
    }

    @Override
    public void update(float delta) {

        time += delta/10;

        currentPosition = startVector.lerp(endVector, time);

        ResourceManager.bullet.setPosition(currentPosition.x, currentPosition.y);

        if(time > 1){
            System.out.println("Done");
            done = true;
        }

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.bullet.draw(batch);
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
