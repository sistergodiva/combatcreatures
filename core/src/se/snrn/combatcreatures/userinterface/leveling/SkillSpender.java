package se.snrn.combatcreatures.userinterface.leveling;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.items.Equipment.Stat;

public class SkillSpender implements Updatable, Renderable{

    private int x;
    private int y;
    private Stat stat;
    private Player player;

    public SkillSpender(int x, int y, Stat stat, Player player) {

        this.x = x;
        this.y = y;
        this.stat = stat;
        this.player = player;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.font.draw(batch, stat.getName() +player.getStats().getStatFromEnum(stat), x ,y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStatValue() {
        return player.getStats().getStatFromEnum(stat);
    }

    public void increase() {
        player.getStats().increaseStatFromEnum(stat);

    }

    public void decrease() {
        player.getStats().decreaseStatFromEnum(stat);
    }
}
