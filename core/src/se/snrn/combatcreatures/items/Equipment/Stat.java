package se.snrn.combatcreatures.items.Equipment;


public enum Stat {
    str("Strength"),
    dex("Dexterity"),
    wis("Wisdom"),
    hp("Health"),
    ep("Energy"),
    mp("Mana"),
    fire("Fire"),
    grass("Grass"),
    water("Water");

    private String name;


    Stat(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}



