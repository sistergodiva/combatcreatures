package se.snrn.combatcreatures.items.Equipment;


public enum Stat {
    MAT("Magic Attack"), NAT("Normal Attack"), MDE("Magic defence"), NDE("Normal Defence"), HP("Health"), MP("Mana");

    private String name;


    Stat(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}



