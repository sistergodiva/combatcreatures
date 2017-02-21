package se.snrn.combatcreatures;


import se.snrn.combatcreatures.interfaces.Fighter;

public class AttackResolver {

    public static int resolveMagicAttack(Fighter attacker, Fighter defender) {
        return attacker.getStats().getMagicAttack() - defender.getStats().getMagicDefence();
    }

    public static int resolveNormalAttack(Fighter attacker, Fighter defender) {
        return attacker.getStats().getNormalAttack() - defender.getStats().getNormalDefence();
    }

}

