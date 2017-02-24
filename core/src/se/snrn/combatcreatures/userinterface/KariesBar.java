package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class KariesBar implements Updatable, Renderable{
    private Player player;
    private int x;
    private int y;
    private Sprite sprite;

    public KariesBar(Player player, int x, int y) {
        this.player = player;
        this.x = x;
        this.y = y;
        sprite = ResourceManager.tooth;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        for (int i = 0; i < player.getKaries(); i++) {
            sprite.setPosition(x+(TILE_SIZE*i), y);
            sprite.draw(batch);
        }
    }
}
