package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public class RemoveKariesConsumable implements ConsumableEffect{

    private int karies;

    public RemoveKariesConsumable() {
        this.karies = karies;
    }

    @Override
    public void eat(Player player) {
        player.removeKaries(karies);
    }

    @Override
    public void toss(Creature creature) {

    }
}
