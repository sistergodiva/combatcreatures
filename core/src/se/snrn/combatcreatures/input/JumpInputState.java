package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.TileType;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.MissionScreen.turnManager;


public class JumpInputState implements InputState {



    private ArrayList<Tile> allowedTargets;
    private Player player;
    private TrainStopMap trainStopMap;
    private boolean done;

    public JumpInputState(Player player, TrainStopMap trainStopMap) {
        this.player = player;
        this.trainStopMap = trainStopMap;


        allowedTargets = new ArrayList<>();

        for (Direction direction : Direction.values()) {
            allowedTargets.add(trainStopMap.getTile(player.getTile().getX() + (direction.getX() * 2), player.getTile().getY() + (direction.getY() * 2)));
        }

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        for (Tile allowedTarget : allowedTargets) {
            if(allowedTarget.getType() == TileType.FLOOR) {
                ResourceManager.target.setPosition(allowedTarget.getX() * TILE_SIZE, allowedTarget.getY() * TILE_SIZE);
                ResourceManager.target.draw(batch);
            }
        }
    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {
        switch (input) {
            case Input.Keys.W: {
                if(allowedTargets.get(0).getType() == TileType.FLOOR) {
                    player.changeTile(allowedTargets.get(0));
                    done = true;
                }
                break;
            }
            case Input.Keys.D: {
                if(allowedTargets.get(1).getType() == TileType.FLOOR) {
                    player.changeTile(allowedTargets.get(1));
                    done = true;
                }
                break;
            }
            case Input.Keys.S: {
                if(allowedTargets.get(2).getType() == TileType.FLOOR) {
                    player.changeTile(allowedTargets.get(2));
                    done = true;
                }
                break;
            }
            case Input.Keys.A: {
                if(allowedTargets.get(3).getType() == TileType.FLOOR) {
                    player.changeTile(allowedTargets.get(3));
                    done = true;
                }
                break;
            }
            default:
                return null;
        }
        if(done) {
            turnManager.endPlayerTurn();
            return new DefaultInputState(player, trainStopMap);
        }
        return null;
    }
}
