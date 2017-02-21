package se.snrn.combatcreatures.interfaces;

import se.snrn.combatcreatures.entities.AiCore;
import se.snrn.combatcreatures.entities.player.Player;

public interface Ai {
    void setAi(AiCore aiCore);
    AiCore getAiCore();
    void act(Player player);
}
