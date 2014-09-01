package cfvbaibai.cardfantasy.engine.feature;

import cfvbaibai.cardfantasy.GameUI;
import cfvbaibai.cardfantasy.data.Feature;
import cfvbaibai.cardfantasy.engine.CardInfo;
import cfvbaibai.cardfantasy.engine.FeatureEffect;
import cfvbaibai.cardfantasy.engine.FeatureEffectType;
import cfvbaibai.cardfantasy.engine.FeatureInfo;
import cfvbaibai.cardfantasy.engine.FeatureResolver;
import cfvbaibai.cardfantasy.engine.HeroDieSignal;

public final class OverdrawFeature {
    public static void apply(FeatureResolver resolver, FeatureInfo featureInfo, CardInfo attacker) throws HeroDieSignal {
        Feature feature = featureInfo.getFeature();
        int adjAT = feature.getImpact();
        GameUI ui = resolver.getStage().getUI();
        ui.useSkill(attacker, feature, true);
        ui.adjustAT(attacker, attacker, adjAT, feature);
        attacker.addEffect(new FeatureEffect(FeatureEffectType.ATTACK_CHANGE, featureInfo, adjAT, true));
        ui.attackCard(attacker, attacker, feature, adjAT);
        if (resolver.applyDamage(attacker, adjAT).cardDead) {
            resolver.resolveDeathFeature(attacker, attacker, feature);
        }
    }
}
