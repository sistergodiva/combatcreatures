package se.snrn.combatcreatures.input;

import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class JumpInputState implements InputState {


    private Mapped user;
    private TileMap tileMap;
    private ArrayList<Tile> allowedTargets;

    public JumpInputState(Mapped user) {
        this.user = user;
        tileMap = user.getMap();

        allowedTargets = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            allowedTargets.add(tileMap.getTile(user.getTile().getX()+(direction.getX()*2),user.getTile().getY()+(direction.getY()*2)));
        }

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        for (Tile allowedTarget : allowedTargets) {
            ResourceManager.player.setPosition(allowedTarget.getX() * TILE_SIZE, allowedTarget.getY() * TILE_SIZE);
            ResourceManager.player.draw(batch);
        }
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(Tile tile) {
        if(allowedTargets.contains(tile)){
            user.changeTile(tile);
        }
        return new DefaultInputState();
    }
}
