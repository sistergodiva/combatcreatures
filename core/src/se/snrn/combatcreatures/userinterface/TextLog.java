package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import java.util.List;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class TextLog implements Updatable, Renderable{


    private int i;

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        List<String> messages = GameLog.getLastMessages(4);

        i = 0;
        for (String message : messages) {
            ResourceManager.font.draw(batch, message, 100, 150-(TILE_SIZE*i));
            i++;
        }
    }
}
