package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.inventory.Inventory;

public class Ui implements Updatable, Renderable{

    private Player player;
    private Inventory inventory;
    private HealthBar healthBar;
    private ManaBar manaBar;
    private KariesBar kariesBar;
    private MiniMap miniMap;

    public Ui(Player player) {

        this.player = player;
        inventory = player.getInventory();
        healthBar = new HealthBar(player);
        manaBar = new ManaBar(player);
        kariesBar = new KariesBar(player);
        miniMap = new MiniMap(player);
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
    }
}
