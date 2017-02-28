package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.inventory.Inventory;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Ui implements Updatable, Renderable{

    private int margin;
    private Player player;
    private Inventory inventory;
    private HealthBar healthBar;
    private ManaBar manaBar;
    private KariesBar kariesBar;
    private MiniMap miniMap;
    private TextLog textLog;

    public Ui(Player player) {
        margin = 8;
        this.player = player;
        inventory = player.getInventory();
        healthBar = new HealthBar(player, 0,Gdx.graphics.getHeight()-TILE_SIZE);
        manaBar = new ManaBar(player, 0, Gdx.graphics.getHeight()-(TILE_SIZE*2)-margin*2);
        kariesBar = new KariesBar(player,0, Gdx.graphics.getHeight()-(TILE_SIZE*3)-margin*4);
        miniMap = new MiniMap(player);
        textLog = new TextLog(0,0);
        inventory.setPosition(Gdx.graphics.getWidth()-200, Gdx.graphics.getHeight()-miniMap.getMiniMapHeight());
    }

    @Override
    public void update(float delta) {
        miniMap.update(delta);
    }

    @Override
    public void render(Batch batch) {
        if(inventory.isOpen()){
            inventory.render(batch);
        }
        healthBar.render(batch);
        manaBar.render(batch);
        kariesBar.render(batch);
        miniMap.render(batch);
        textLog.render(batch);
    }

    public MiniMap getMiniMap() {
        return miniMap;
    }
}
