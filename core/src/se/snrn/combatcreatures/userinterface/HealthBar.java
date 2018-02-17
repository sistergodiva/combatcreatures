package se.snrn.combatcreatures.userinterface;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.items.Equipment.Stat;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class HealthBar implements Updatable, Renderable {
    private Player player;
    private int x;
    private int y;
    private int margin;
    private float maxHp;
    private float currentHp;

    public HealthBar(Player player, int x, int y) {
        margin = 8;
        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.uiNinePatch.draw(batch, x,y-margin*2, 250+margin*2, TILE_SIZE+margin*2);
        maxHp = player.getHp();
        currentHp = player.getHp();
        ResourceManager.health.draw(batch,x+margin,y- margin,currentHp / maxHp * 250,TILE_SIZE);
        ResourceManager.healthBarNinePatch.draw(batch, x+margin, y - margin, 250, TILE_SIZE);
        ResourceManager.font.draw(batch, "HP: "+currentHp+" / "+maxHp,x,y);
    }
}
