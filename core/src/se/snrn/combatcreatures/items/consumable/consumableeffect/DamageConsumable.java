package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public class DamageConsumable implements ConsumableEffect {

    private int damage;

    public DamageConsumable() {

    }

    @Override
    public void eat(Player player) {
        player.healDamage(damage);
    }

    @Override
    public void toss(Creature creature) {
        creature.healDamage(damage);
    }
}