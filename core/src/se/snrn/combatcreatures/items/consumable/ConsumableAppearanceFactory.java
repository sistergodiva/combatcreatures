package se.snrn.combatcreatures.items.consumable;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.effects.Effect;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumableAppearanceFactory {



    private static JsonReader jsonReader = new JsonReader();
    private static JsonValue consumable = jsonReader.parse(Gdx.files.internal("json/consumable.json")).get(0);

    public ConsumableAppearanceFactory() {

    }

    public static Consumable getConsumableAppearance(int id){
        return new Consumable(consumable.get(id));
    }
}
