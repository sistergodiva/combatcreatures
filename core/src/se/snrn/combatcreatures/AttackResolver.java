package se.snrn.combatcreatures;


import se.snrn.combatcreatures.interfaces.Fighter;
import se.snrn.combatcreatures.interfaces.Mapped;
import se.snrn.combatcreatures.items.Equipment.Stat;
import se.snrn.combatcreatures.visualeffects.RangedAttackAnimation;

public class AttackResolver {

    public static int resolveMagicAttack(Fighter attacker, Fighter defender) {
        return attacker.getStats().getStatFromEnum(Stat.MAT) - defender.getStats().getStatFromEnum(Stat.MDE);
    }

    public static int resolveNormalAttack(Fighter attacker, Fighter defender) {
        return attacker.getStats().getStatFromEnum(Stat.NAT) - defender.getStats().getStatFromEnum(Stat.NDE);
    }

    public static void resolveRangedAttack(Mapped attacker, Mapped defender) {
        MissionScreen.visualEffectManager.addEffect(new RangedAttackAnimation(attacker.getTile(), defender.getTile()));
    }
}

