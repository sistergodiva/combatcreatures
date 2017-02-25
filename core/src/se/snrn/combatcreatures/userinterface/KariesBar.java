package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class KariesBar implements Updatable, Renderable{
    private Sprite sprite;
    private Player player;
    private int x;
    private int y;
    private int margin;

    public KariesBar(Player player, int x, int y) {
        margin = 8;
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
        ResourceManager.pinkBox.draw(batch, x,y-margin*2, player.getHealth()*TILE_SIZE+margin*2, TILE_SIZE+margin*2);
        for (int i = 0; i < player.getKaries(); i++) {
            sprite.setPosition(x+(TILE_SIZE*i)+margin, y-margin);
            sprite.draw(batch);
        }
    }
}
