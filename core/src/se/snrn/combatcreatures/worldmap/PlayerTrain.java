package se.snrn.combatcreatures.worldmap;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;


public class PlayerTrain implements Updatable, Renderable {
    private Tile tile;
    private WorldMap worldMap;
    private Sprite sprite;

    public PlayerTrain(Tile tile, WorldMap worldMap) {
        this.tile = tile;
        this.worldMap = worldMap;
        sprite = ResourceManager.train;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void advance(){
        tile = worldMap.getNextTrackTile(tile);
    }

    @Override
    public void update(float delta) {
        sprite.setPosition(tile.getX() * TILE_SIZE, tile.getY() * TILE_SIZE);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }
}
