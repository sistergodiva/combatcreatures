package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class AttackEffect implements VisualEffect {

    private boolean done = false;
    private Tile start;
    private Tile end;
    private Vector2 startVector;
    private Vector2 endVector;
    private Player player;
    private Vector2 currentPosition;
    private float time;
    private boolean goingBack = false;

    public AttackEffect(Tile start, Tile end, Player player) {
        this.start = start;
        this.end = end;


        startVector = new Vector2(start.getX() * TILE_SIZE, start.getY() * TILE_SIZE);
        endVector = new Vector2(end.getX() * TILE_SIZE, end.getY() * TILE_SIZE);
        this.player = player;
        currentPosition = new Vector2();
    }

    @Override
    public void update(float delta) {


        currentPosition = startVector.lerp(endVector, time);

        player.getSprite().setPosition(currentPosition.x, currentPosition.y);

        System.out.println(time);
        if (!goingBack) {
            time += delta;
        }

        if (goingBack) {
            time -= delta;
        }

        if (time > 0.5f && !goingBack) {
            goingBack = true;
        }
        if (goingBack && time <= 0) {
            done = true;
        }
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public boolean isDone() {
        return done;
    }
}
