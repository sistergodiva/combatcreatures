package se.snrn.combatcreatures.entities.enemies;

import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;

public interface AiCore {

    void act(Creature creature, Player player);

}
