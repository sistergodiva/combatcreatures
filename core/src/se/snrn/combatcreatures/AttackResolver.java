package se.snrn.combatcreatures;


import se.snrn.combatcreatures.interfaces.Fighter;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.items.Equipment.Stat;
import se.snrn.combatcreatures.visualeffects.RangedAttackAnimation;

public class AttackResolver {

    public static int resolveMagicAttack(Fighter attacker, Fighter defender) {
        return 1;
    }

    public static int resolveNormalAttack(Fighter attacker, Fighter defender) {
        return 1;

    }

    public static void resolveRangedAttack(Mapped attacker, Mapped defender) {
        MissionScreen.visualEffectManager.addEffect(new RangedAttackAnimation(attacker.getTile(), defender.getTile()));
    }
}

