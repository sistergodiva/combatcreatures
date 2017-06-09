package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public interface ConsumableEffect {

    void eat(Player player);
    void toss(Creature creature);

}
