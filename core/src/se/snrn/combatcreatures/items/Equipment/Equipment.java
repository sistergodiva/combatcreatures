package se.snrn.combatcreatures.items.Equipment;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.effects.PermanentBuffEffect;
import se.snrn.combatcreatures.items.Ability;
import se.snrn.combatcreatures.items.Item;

public class Equipment implements Item {

    private String name;
    private String description;
    private PermanentBuffEffect permanentBuffEffect;
    private Ability ability;
    private EquipmentSlot equipmentSlot;
    private float x;
    private float y;
    private Sprite sprite;


    public Equipment(String name, String description, PermanentBuffEffect permanentBuffEffect, Ability ability, EquipmentSlot equipmentSlot) {
        this.name = name;
        this.description = description;
        this.permanentBuffEffect = permanentBuffEffect;
        this.ability = ability;
        this.equipmentSlot = equipmentSlot;
        sprite = ResourceManager.hatSprite;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSpriteString() {
        return null;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public PermanentBuffEffect getPermanentBuffEffect(){
        return permanentBuffEffect;
    }
    public Ability getAbility() {
        return ability;
    }

    @Override
    public void render(Batch batch) {
        sprite.setPosition(x, y);
        sprite.draw(batch);
    }
}
