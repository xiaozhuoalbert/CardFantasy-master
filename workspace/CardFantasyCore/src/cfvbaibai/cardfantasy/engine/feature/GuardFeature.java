package cfvbaibai.cardfantasy.engine.feature;

import cfvbaibai.cardfantasy.CardFantasyRuntimeException;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.data.FeatureTag;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.EntityInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;

public final class GuardFeature {
    public static int apply(Feature guardFeature, Feature attackFeature, FeatureResolver resolver, EntityInfo attacker, CardInfo guardian,
            int damage) throws HeroDieSignal {
        if (attacker == null) {
            throw new CardFantasyRuntimeException("Attacker cannot be null");
        }
        if (attackFeature != null && attackFeature.getType().containsTag(FeatureTag.抗守护)) {
            return damage;
        }
        resolver.getStage().getUI().useSkill(guardian, attacker, guardFeature, true);
        int remainingDamage = 0;
        if (damage > guardian.getHP()) {
            remainingDamage = damage - guardian.getHP();
            damage = guardian.getHP();
        }
        resolver.getStage().getUI().attackCard(attacker, guardian, guardFeature, damage);
        if (resolver.applyDamage(guardian, damage).cardDead) {
            resolver.resolveDeathFeature(attacker, guardian, guardFeature);
        }
        return remainingDamage;
    }
}
