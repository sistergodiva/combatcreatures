package se.snrn.combatcreatures.items.consumable;


import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.items.Item;
import se.snrn.combatcreatures.items.consumable.consumableeffect.ConsumableEffect;

public class Consumable implements Item, ConsumableEffect {

    private String name;
    private String description;
    private String spriteString;
    private ConsumableEffect consumableEffect;

    public Consumable(JsonValue appearance) {
        this.name = appearance.getString(1);
        this.description = appearance.getString(2);
        this.spriteString = appearance.getString(3);
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
        return spriteString;
    }

    @Override
    public void eat(Player player) {
        consumableEffect.eat(player);
    }

    @Override
    public void toss(Creature creature) {
        consumableEffect.toss(creature);
    }

    @Override
    public String toString() {
        return "Consumable{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", spriteString='" + spriteString + '\'' +
                ", consumableEffect=" + consumableEffect.getClass().getSimpleName() +
                '}';
    }

    public void setConsumableEffect(ConsumableEffect consumableEffect) {
        this.consumableEffect = consumableEffect;
    }
}
