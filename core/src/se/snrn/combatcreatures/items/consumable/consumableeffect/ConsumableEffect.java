package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public interface ConsumableEffect {

    void eat(Player player);
    void toss(Creature creature);

}
