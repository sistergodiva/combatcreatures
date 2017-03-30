package se.snrn.combatcreatures.userinterface.leveling;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class SkillCursor implements Renderable {


    private SkillSpender skill;

    public SkillCursor(SkillSpender skill) {
        this.skill = skill;
    }

    @Override
    public void render(Batch batch) {
        ResourceManager.cursor.setPosition(skill.getX()-TILE_SIZE, skill.getY()-24);
        ResourceManager.cursor.draw(batch);
    }

    public SkillSpender getSkill() {
        return skill;
    }

    public void setSkill(SkillSpender skill) {
        this.skill = skill;
    }
}
