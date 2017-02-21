package se.snrn.combatcreatures.items;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import se.snrn.combatcreatures.effects.Effect;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumableFactory {

    private JsonValue consumableAppearance;
    ArrayList<Consumable> consumables;
    ArrayList<Effect> effects;

    HashMap<Consumable, Effect> identity;

    public ConsumableFactory() {
        JsonReader jsonReader = new JsonReader();
        consumableAppearance = jsonReader.parse(Gdx.files.internal("json/consumable_appearance.json")).get(0);

        consumables = new ArrayList<>();
        effects = new ArrayList<>();
        identity = new HashMap<>();
    }

    public Consumable getConsumable(int id){
        return new Consumable(consumableAppearance.get(id));
    }

    public Effect getEffect(Consumable consumable){
        return identity.get(consumable);
    }
}
