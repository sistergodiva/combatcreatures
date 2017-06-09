package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public class AddKariesConsumable  implements ConsumableEffect{

    private int karies;

    public AddKariesConsumable() {
        this.karies = karies;
    }

    @Override
    public void eat(Player player) {
        player.addKaries(karies);
    }

    @Override
    public void toss(Creature creature) {

    }
}
