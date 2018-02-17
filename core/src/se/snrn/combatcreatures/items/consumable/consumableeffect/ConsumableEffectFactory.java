package se.snrn.combatcreatures.items.consumable.consumableeffect;

public class ConsumableEffectFactory {


    public static ConsumableEffect getConsumableEffect(int id){
        switch (id) {
            case 0: {
                return new HealingConsumable();
            }
            case 1: {
                return new DamageConsumable();
            }
            case 2: {
                return new RandomStatBuff();
            }
            case 3: {
                return new RandomStatBuff();
            }
            case 4: {
                return new RandomStatBuff();
            }
            case 5: {
                return new RandomStatNerf();
            }
            case 6: {
                return new TeleportConsumable();
            }
            case 7: {
                return new PolymorphConsumable();
            }
        }
        return null;
    }
}
