package se.snrn.combatcreatures.userinterface.leveling;


import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.items.Equipment.Stat;

import java.util.ArrayList;

public class SkillWindow implements Updatable, Renderable{

    private int x;
    private int y;
    private ArrayList<SkillSpender> skillSpenders;
    private Player player;
    private SkillCursor cursor;
    private int cursorPos;
    private boolean open = false;

    public SkillWindow(int x, int y, Player player) {
        this.player = player;
        int i = 0;

        this.x = x;
        this.y = y;
        skillSpenders = new ArrayList<>();

        for (Stat stat: Stat.values()
             ) {
            i++;
            skillSpenders.add(new SkillSpender(x, y+(-24*i)+128, stat, player));

        }

        cursor = new SkillCursor(skillSpenders.get(0));

    }

    public void moveCursorUp() {
        if (cursorPos == skillSpenders.size() - 1) {
            cursorPos = 0;
        } else {
            cursorPos++;
        }
        cursor.setSkill(skillSpenders.get(cursorPos));
    }

    public void moveCursorDown() {
        if (cursorPos == 0) {
            cursorPos = skillSpenders.size() - 1;
        } else {
            cursorPos--;
        }
        cursor.setSkill(skillSpenders.get(cursorPos));
    }

    public void increaseSkill(){
        skillSpenders.get(cursorPos).increase();
    }


    public void decreaseSkill(){
        skillSpenders.get(cursorPos).decrease();
    }


    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        ResourceManager.uiNinePatch.draw(batch, x,y, 128,128);
        for (SkillSpender skillSpender : skillSpenders
                ) {
            skillSpender.render(batch);
        }
        cursor.render(batch);
    }


    public int getStatAtCursor() {
        return skillSpenders.get(cursorPos).getStatValue();
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
