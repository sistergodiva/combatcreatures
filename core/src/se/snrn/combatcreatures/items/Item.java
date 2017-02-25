package se.snrn.combatcreatures.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import se.snrn.combatcreatures.interfaces.Renderable;

public interface Item extends Renderable{

    String getName();

    String getDescription();

    String getSpriteString();

    Sprite getSprite();

    void setPosition(float x, float y);
}
