package se.snrn.combatcreatures.input;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.entities.Direction;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;

public class DefaultInputState implements InputState{
    private Player player;
    private TrainStopMap trainStopMap;

    public DefaultInputState(Player player, TrainStopMap trainStopMap) {
        this.player = player;
        this.trainStopMap = trainStopMap;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void enter() {

    }

    @Override
    public void exit() {

    }

    @Override
    public InputState handleInput(int input) {

        switch (input){
            case Input.Keys.W: {
                player.move(Direction.NORTH);
                break;
            }
            case Input.Keys.D: {
                player.move(Direction.EAST);
                break;
            }
            case Input.Keys.S: {
                player.move(Direction.SOUTH);
                break;
            }
            case Input.Keys.A: {
                player.move(Direction.WEST);
                break;
            }
            case Input.Keys.J: {
                return new JumpInputState(player, trainStopMap);
            }
            case Input.Keys.K: {
                return new DigInputState(player, trainStopMap);
            }

            case Input.Keys.L: {
                System.out.println(player.getStats());
                break;
            }

            case Input.Keys.I: {
                return new InventoryInputState(player, trainStopMap);
            }

            case Input.Keys.Y: {
                return new LevelingInputState(player, trainStopMap);
            }
            case Input.Keys.M: {
                MissionScreen.ui.getMiniMap().setMapScale(8);
                break;
            }
            case Input.Keys.X: {
                player.addXp(100);
                break;
            }

            case  Input.Keys.Q: {
                return new RangedInputState(player, trainStopMap);
            }

            case Input.Keys.Z: {
                player.setSkillPoints(player.getSkillPoints()-1);
                break;
            }
            case Input.Keys.V: {
                return new RangedInputState(player, trainStopMap);
            }
        }
        return null;
    }
}
