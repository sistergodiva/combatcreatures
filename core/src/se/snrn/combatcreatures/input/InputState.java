package se.snrn.combatcreatures.input;


import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;
import se.snrn.combatcreatures.map.Tile;

public interface InputState extends Updatable, Renderable{
    void enter();
    void exit();

    InputState handleInput(Tile tile);
}
