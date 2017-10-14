package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.AttackResolver;
import se.snrn.combatcreatures.ResourceManager;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.interfaces.Fighter;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.map.Tile;
import se.snrn.combatcreatures.map.los.LineOfSight;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

import java.util.ArrayList;

import static se.snrn.combatcreatures.CombatCreatures.TILE_SIZE;
import static se.snrn.combatcreatures.MissionScreen.turnManager;


public class RangedInputState implements InputState {


    private ArrayList<Mapped> allowedTargets;
    private Player player;
    private TrainStopMap trainStopMap;
    private boolean done;
    private Mapped target;
    private int targetNumber;

    public RangedInputState(Player player, TrainStopMap trainStopMap) {
        this.player = player;
        this.trainStopMap = trainStopMap;

        targetNumber = 0;

        allowedTargets = new ArrayList<>();

        for (Tile tile : LineOfSight.getVision()) {
            if (tile.getMapped() instanceof Fighter && !(tile.getMapped() instanceof Player)) {
                if (!allowedTargets.contains(tile.getMapped())) {
                    allowedTargets.add(tile.getMapped());
                }
            }
        }

        if (!allowedTargets.isEmpty()) {
            target = allowedTargets.get(targetNumber);
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        if (!allowedTargets.isEmpty()) {
            ResourceManager.cursor.setPosition(target.getTile().getX() * TILE_SIZE, target.getTile().getY() * TILE_SIZE);

            ResourceManager.cursor.draw(batch);

            for (Mapped allowedTarget : allowedTargets) {

                ResourceManager.target.setPosition(allowedTarget.getTile().getX() * TILE_SIZE, allowedTarget.getTile().getY() * TILE_SIZE);
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
            case Input.Keys.ENTER: {
                AttackResolver.resolveRangedAttack(player, target);
                done = true;
                break;
            }
            case Input.Keys.S: {
                if (targetNumber < allowedTargets.size() - 1) {
                    targetNumber++;
                } else {
                    targetNumber = 0;
                }
                target = allowedTargets.get(targetNumber);
                System.out.println("targetnumber: " + targetNumber + " number of targets: " + allowedTargets.size());
                break;
            }
            case Input.Keys.A: {
                if (targetNumber > 0) {
                    targetNumber--;
                } else {
                    targetNumber = allowedTargets.size() - 1;
                }
                target = allowedTargets.get(targetNumber);
                System.out.println("targetnumber: " + targetNumber + " number of targets: " + allowedTargets.size());
                break;
            }
            default:
                return null;
        }
        if (done) {
            turnManager.endPlayerTurn();
            return new DefaultInputState(player, trainStopMap);
        }
        return null;
    }
}
