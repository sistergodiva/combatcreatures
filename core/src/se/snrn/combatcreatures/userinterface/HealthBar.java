package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;


public class HealthBar implements Updatable, Renderable{
    private Player player;

    public HealthBar(Player player) {

        this.player = player;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }
}
