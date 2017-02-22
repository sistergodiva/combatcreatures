package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public class HealingConsumable implements ConsumableEffect {

    private int health;

    public HealingConsumable() {

    }

    @Override
    public void eat(Player player) {
        player.healDamage(health);
    }

    @Override
    public void toss(Creature creature) {
        creature.healDamage(health);
    }
}
