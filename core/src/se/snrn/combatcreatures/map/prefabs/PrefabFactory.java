package se.snrn.combatcreatures.map.prefabs;


import se.snrn.combatcreatures.RandomNumber;

public class PrefabFactory {

    public static MapComponent getRandomRoom(){
        return new Room(RandomNumber.range(4,7),RandomNumber.range(4,7));
    }
}
