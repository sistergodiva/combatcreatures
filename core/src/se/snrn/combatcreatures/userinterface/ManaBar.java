package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class ManaBar implements Updatable, Renderable{
    private Sprite sprite;
    private Player player;
    private int x;
    private int y;

    public ManaBar(Player player, int x, int y) {

        this.player = player;
        this.x = x;
        this.y = y;
        sprite = ResourceManager.magic;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        for (int i = 0; i < player.getMana(); i++) {
            sprite.setPosition(x+(TILE_SIZE*i), y);
            sprite.draw(batch);
        }
    }
}
