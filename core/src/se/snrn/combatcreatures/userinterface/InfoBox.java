package se.snrn.combatcreatures.userinterface;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.MapManager;

public class InfoBox implements Updatable, Renderable{

    private MapManager mapManager;
    private Player player;
    private float margin = 8;
    private float x;
    private float y;

    public InfoBox(MapManager mapManager, Player player, int x, int y) {

        this.mapManager = mapManager;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.pinkBox.draw(batch, x,(Gdx.graphics.getHeight()-y)-48, 128,48);

        ResourceManager.font.draw(batch, "Floor: "+player.getFloor()+" Level: "+player.getLevel(), x+margin, (Gdx.graphics.getHeight()-y)-((ResourceManager.glyphLayout.height+margin)));

    }
}
