package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class MoveEffect implements VisualEffect {

    private boolean done = false;
    private Tile start;
    private Tile end;
    private Vector2 startVector;
    private Vector2 endVector;
    private Player player;
    private Vector2 currentPosition;
    private float time;

    public MoveEffect(Tile start, Tile end, Player player) {
        this.start = start;
        this.end = end;


        startVector = new Vector2(start.getX() * TILE_SIZE, start.getY() * TILE_SIZE);
        endVector = new Vector2(end.getX() * TILE_SIZE, end.getY() * TILE_SIZE);
        this.player = player;
        currentPosition = new Vector2();
    }

    @Override
    public void update(float delta) {

        time += delta*4;

        currentPosition = startVector.interpolate(endVector, time, Interpolation.linear);

        player.getSprite().setPosition(currentPosition.x,currentPosition.y);

        if (time > 1) {
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
