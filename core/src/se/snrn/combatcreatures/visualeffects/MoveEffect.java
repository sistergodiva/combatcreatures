package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.DELAY;
import static se.snrn.combatcreatures.CombatCreatures.SPEED_MULTIPLIER;
import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class MoveEffect implements VisualEffect {

    private Sprite sprite;
    private boolean done = false;
    private Tile start;
    private Tile end;

    private float time;
    private float x;
    private float y;
    private float delay;

    public MoveEffect(Tile start, Tile end, Player player) {
        this.start = start;
        this.end = end;

        x = start.getX() * TILE_SIZE;
        y = start.getY() * TILE_SIZE;

        this.sprite = player.getSprite();
        ResourceManager.walk.play();
    }

    public MoveEffect(Tile start, Tile end, Creature creature) {
        this.start = start;
        this.end = end;

        delay = DELAY;
        x = start.getX() * TILE_SIZE;
        y = start.getY() * TILE_SIZE;

        this.sprite = creature.getSprite();
    }

    @Override
    public void update(float delta) {
        delay -= delta;
        if (delay < 0) {
            time += delta * SPEED_MULTIPLIER;


            if (time > 1) {
                time = 1;
                done = true;
            }
        }

        x = Interpolation.linear.apply(start.getX() * TILE_SIZE, end.getX() * TILE_SIZE, time);
        y = Interpolation.linear.apply(start.getY() * TILE_SIZE, end.getY() * TILE_SIZE, time);
        sprite.setPosition(x, y);
    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public boolean isDone() {
        return done;
    }
}
