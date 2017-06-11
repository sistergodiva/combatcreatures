package se.snrn.combatcreatures.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import se.snrn.combatcreatures.MissionScreen;
import se.snrn.combatcreatures.entities.player.Player;
import se.snrn.combatcreatures.map.trainstops.TrainStopMap;
import se.snrn.combatcreatures.userinterface.leveling.SkillWindow;

public class LevelingInputState implements InputState {

    private SkillWindow skillWindow;
    private Player player;
    private TrainStopMap trainStopMap;

    public LevelingInputState(Player player, TrainStopMap trainStopMap) {
        this.player = player;
        this.trainStopMap = trainStopMap;
        this.skillWindow = MissionScreen.getUi().getSkillWindow();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {

    }

    @Override
    public void enter() {
        skillWindow.setOpen(true);
    }

    @Override
    public void exit() {
        skillWindow.setOpen(false);
    }

    @Override
    public InputState handleInput(int input) {

        switch (input) {
            case Input.Keys.W: {
                skillWindow.moveCursorDown();
                break;
            }
            case Input.Keys.S: {
                skillWindow.moveCursorUp();
                break;
            }

            case Input.Keys.D: {
                skillWindow.increaseSkill();
                break;
            }
            case Input.Keys.A: {
                skillWindow.decreaseSkill();
                break;
            }
            case Input.Keys.Y: {

                return new DefaultInputState(player, trainStopMap);
            }


            case Input.Keys.ENTER: {
                System.out.println(skillWindow.getStatAtCursor());
                break;
            }
        }

        return null;
    }
}
