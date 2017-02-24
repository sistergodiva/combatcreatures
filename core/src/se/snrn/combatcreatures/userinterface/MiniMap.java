package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.TileMap;

public class MiniMap implements Updatable, Renderable {

    private TileMap tileMap;
    private Player player;

    public MiniMap(Player player) {


        this.player = player;

        tileMap = player.getMap();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }
}
