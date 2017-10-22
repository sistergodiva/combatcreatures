package se.snrn.combatcreatures.userinterface.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.entities.player.PlayerEquipment;
import se.snrn.combatcreatures.interfaces.Renderable;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;

public class EquipmentWindow implements Renderable {


    private final int x;
    private final int y;
    private final Player player;
    private final int margin;
    private final int inventoryWidth;
    private final int inventoryBottom;
    private final int inventoryHeight;

    private PlayerEquipment playerEquipment;

    public EquipmentWindow(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;

        margin = 8;
        inventoryWidth = 192;
        inventoryBottom = 0;
        inventoryHeight = Gdx.graphics.getHeight() - 500 + margin;
        playerEquipment = player.getPlayerEquipment();
    }

    @Override
    public void render(Batch batch) {
        ResourceManager.uiNinePatch.draw(batch, x, y, inventoryWidth, inventoryHeight - y);


        if (playerEquipment.getHead() != null) {
            playerEquipment.getHead().getSprite().setPosition(x+(inventoryWidth/2), inventoryHeight+y-(margin*4));
            playerEquipment.getHead().getSprite().draw(batch);
        }
        if (playerEquipment.getBack() != null) {
            playerEquipment.getBack().setPosition(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE);
            playerEquipment.getBack().render(batch);
        }
        if (playerEquipment.getChest() != null) {
            playerEquipment.getChest().setPosition(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE);
            playerEquipment.getChest().render(batch);
        }
        if (playerEquipment.getMainHand() != null) {
            playerEquipment.getMainHand().setPosition(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE);
            playerEquipment.getMainHand().render(batch);
        }
        if (playerEquipment.getOffHand() != null) {
            playerEquipment.getOffHand().setPosition(player.getTile().getX() * TILE_SIZE, player.getTile().getY() * TILE_SIZE);
            playerEquipment.getOffHand().render(batch);
        }
    }
}
