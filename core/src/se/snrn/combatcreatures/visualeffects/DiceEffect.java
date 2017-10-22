package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;

public class DiceEffect implements VisualEffect {
    private final Animation<Texture> animation;
    private int x;
    private int y;
    private Texture frame;
    private boolean done = false;
    private float time = 0;

    public DiceEffect(int x, int y) {
        this.x = x;
        this.y = y;
        animation = new Animation<Texture>(0.125f, ResourceManager.getAnimation("ui/dice/dice", 6));
        frame = animation.getKeyFrame(0);

    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(frame, x, y);

    }

    @Override
    public void update(float delta) {
        time += delta * 4;
        frame = animation.getKeyFrame(time);
        if (time >= 0.5f) {
            done = true;
        }
    }
}
