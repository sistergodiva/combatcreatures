package se.snrn.combatcreatures.entities;

import se.snrn.combatcreatures.entities.player.Player;

public interface AiCore {

    void act(Creature creature, Player player);

}
