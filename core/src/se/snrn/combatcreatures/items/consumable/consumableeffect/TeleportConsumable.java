package se.snrn.combatcreatures.items.consumable.consumableeffect;

import se.snrn.combatcreatures.RandomNumber;
import se.snrn.combatcreatures.entities.enemies.Creature;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.Tile;

public class TeleportConsumable implements ConsumableEffect {


    @Override
    public void eat(Player player) {
        int numberOfFilledTiles = player.getMap().getOpenTiles().size();
        Tile tile = player.getMap().getOpenTiles().get(RandomNumber.range(0, numberOfFilledTiles));
        player.changeTile(tile);
    }

    @Override
    public void toss(Creature creature) {
        int numberOfFilledTiles = creature.getMap().getOpenTiles().size();
        Tile tile = creature.getMap().getOpenTiles().get(RandomNumber.range(0, numberOfFilledTiles));
        creature.changeTile(tile);
    }
}
