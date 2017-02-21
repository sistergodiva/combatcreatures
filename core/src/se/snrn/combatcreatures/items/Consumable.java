package se.snrn.combatcreatures.items;


import com.badlogic.gdx.utils.JsonValue;

public class Consumable implements Item {

    private String name;
    private String description;
    private String sprite;

    public Consumable(JsonValue appearance) {
        this.name = appearance.getString(0);
        this.description = appearance.getString(1);
        this.sprite = appearance.getString(2);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
