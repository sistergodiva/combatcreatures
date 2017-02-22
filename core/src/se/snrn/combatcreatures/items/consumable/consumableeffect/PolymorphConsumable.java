package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public class PolymorphConsumable  implements ConsumableEffect{
    @Override
    public void eat(Player player) {
        System.out.println("POLYMORPH!");
    }

    @Override
    public void toss(Creature creature) {
        System.out.println("POLYMORPH!");
    }
}
