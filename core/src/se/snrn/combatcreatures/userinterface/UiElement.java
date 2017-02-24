package se.snrn.combatcreatures.userinterface;

import se.snrn.combatcreatures.interfaces.Renderable;
import se.snrn.combatcreatures.interfaces.Updatable;

public interface UiElement extends Updatable, Renderable {

    void setPosition(int x, int y);


}
