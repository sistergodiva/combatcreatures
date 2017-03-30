package se.snrn.combatcreatures.userinterface;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

public class AbilityBar  implements Updatable, Renderable {

    private Player player;
    private float x;
    private float y;

    public AbilityBar(Player player, int x, int y) {

        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.pinkBox.draw(batch, x,y, 128,80);

    }
}
