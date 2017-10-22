package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.map.Direction;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class SlashAnimationEffect implements VisualEffect {

    private float x;
    private float y;
    private float originY;
    private float originX;
    private Direction direction;
    boolean done = false;
    Animation<Texture> animation;
    private float time;
    private Texture frame;
    private float rotation;

    public SlashAnimationEffect(float x, float y, Direction direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;
        //animation = new Animation<Texture>(0.125f, ResourceManager.getAnimation("animations/slash", 4));
        animation = new Animation<Texture>(0.125f, ResourceManager.getAnimation("animations/smash", 4));
        frame = animation.getKeyFrame(0);

        switch (direction) {

            case NORTH:
                rotation = 90;
                break;
            case EAST:
                rotation = 0;
                break;
            case SOUTH:
                rotation = 270;
                break;
            case WEST:
                rotation = 180;
                break;
        }
        this.x += direction.getX()*TILE_SIZE/2;
        this.y += direction.getY()*TILE_SIZE/2;
        ResourceManager.hit.play();
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(frame, x, y, 8, 8, TILE_SIZE, TILE_SIZE, 1.5f, 1.5f, rotation, 0, 0, TILE_SIZE, TILE_SIZE, false, false);
    }

    @Override
    public void update(float delta) {

        time += delta*4;
        frame = animation.getKeyFrame(time);
        if (time >= 0.5f) {
            done = true;
        }
    }
}
