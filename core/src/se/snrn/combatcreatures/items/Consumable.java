package se.snrn.combatcreatures.items;


public class Consumable implements Item {

    private String name;
    private String description;
    private String sprite;

    public Consumable(String name, String description, String sprite) {
        this.name = name;
        this.description = description;
        this.sprite = sprite;
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
