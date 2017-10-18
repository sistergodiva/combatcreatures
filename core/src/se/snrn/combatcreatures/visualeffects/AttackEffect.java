package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Interpolation;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.Direction;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.DELAY;
import static se.snrn.combatcreatures.CombatCreatures.SPEED_MULTIPLIER;
import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class AttackEffect implements VisualEffect {

    private Sprite sprite;
    private boolean done = false;
    private Tile start;
    private Tile end;


    private float time;
    private boolean goingBack = false;
    private float x;
    private float y;
    private float delay;
    private Direction direction;
    SlashAnimation slashAnimation;


    public AttackEffect(Tile start, Tile end, Player player) {
        this.start = start;
        this.end = end;


        x = start.getX() * TILE_SIZE;
        y = start.getY() * TILE_SIZE;
        this.sprite = player.getSprite();
        direction = MissionScreen.tileMap.getDirectionFromTile(start,end);
        slashAnimation = new SlashAnimation(x,y, direction);
    }

    public AttackEffect(Tile start, Tile end, Creature creature) {
        this.start = start;
        this.end = end;


        x = start.getX() * TILE_SIZE;
        y = start.getY() * TILE_SIZE;
        this.sprite = creature.getSprite();
        direction = MissionScreen.tileMap.getDirectionFromTile(start,end);
        delay = DELAY;
        //slashAnimation = new SlashAnimation(x,y, direction);
    }

    @Override
    public void update(float delta) {

        delay -= delta;
        if (delay < 0) {


            if (!goingBack) {
                time += delta * SPEED_MULTIPLIER;
            }

            if (goingBack && !done) {
                time -= delta * SPEED_MULTIPLIER;
            }

            if (time > 0.5f && !goingBack) {
                goingBack = true;
            }
            if (goingBack && time <= 0f) {
                done = true;
            }

        }
        x = Interpolation.linear.apply(start.getX() * TILE_SIZE, end.getX() * TILE_SIZE, time);
        y = Interpolation.linear.apply(start.getY() * TILE_SIZE, end.getY() * TILE_SIZE, time);


        sprite.setPosition(x, y);
        if(slashAnimation != null) {
            slashAnimation.update(delta);
        }
    }

    @Override
    public void render(Batch batch) {
        if(slashAnimation != null) {
            slashAnimation.render(batch);
        }
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
