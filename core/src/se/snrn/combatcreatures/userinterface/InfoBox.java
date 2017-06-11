package se.snrn.combatcreatures.userinterface;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.TurnManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class InfoBox implements Updatable, Renderable{

    private Player player;
    private float margin = 8;
    private float x;
    private float y;

    public InfoBox(Player player, int x, int y) {

        this.player = player;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.pinkBox.draw(batch, x,(Gdx.graphics.getHeight()-y)-80, 128,80);

        ResourceManager.font.draw(batch, "Level: "+player.getLevel()+" XP: "+player.getXp()+" skillsLeft: "+player.getSkillPoints(), x+margin, (Gdx.graphics.getHeight()-y)-((ResourceManager.glyphLayout.height+margin)));
        ResourceManager.font.draw(batch, " Turn: "+ TurnManager.getTurn(), x+margin, (Gdx.graphics.getHeight()-y)-((ResourceManager.glyphLayout.height+margin*2+TILE_SIZE)));

    }
}
