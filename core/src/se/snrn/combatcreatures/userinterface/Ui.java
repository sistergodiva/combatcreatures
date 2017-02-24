package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.inventory.Inventory;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class Ui implements Updatable, Renderable{

    private Player player;
    private Inventory inventory;
    private HealthBar healthBar;
    private ManaBar manaBar;
    private KariesBar kariesBar;
    private MiniMap miniMap;
    private TextLog textLog;

    public Ui(Player player) {

        this.player = player;
        inventory = player.getInventory();
        healthBar = new HealthBar(player, 0,Gdx.graphics.getHeight()-TILE_SIZE);
        manaBar = new ManaBar(player, 0, Gdx.graphics.getHeight()-TILE_SIZE*2);
        kariesBar = new KariesBar(player,0, Gdx.graphics.getHeight()-TILE_SIZE*3);
        miniMap = new MiniMap(player);
        textLog = new TextLog();
    }

    @Override
    public void update(float delta) {

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
}
