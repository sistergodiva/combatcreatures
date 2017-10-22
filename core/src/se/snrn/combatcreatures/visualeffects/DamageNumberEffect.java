package se.snrn.combatcreatures.visualeffects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.map.Direction;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class DamageNumberEffect implements VisualEffect {

    private float x;
    private float y;
    private int damage;
    private float originY;
    private float originX;
    boolean done = false;
    private float time;
    BitmapFont font;

    public DamageNumberEffect(float x, float y, int damage) {

        this.x = x;
        this.y = y;
        this.damage = damage;

        font = ResourceManager.font;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public void update(float delta) {
        time += delta * 4;

        y += time;

        if (time >= 4) {
            done = true;
        }
    }

    @Override
    public void render(Batch batch) {
        font.draw(batch,""+damage,x,y);
    }
}
